package com.dreamsindevelopment.shirtster.utils;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.maps.tiled.TiledMap;
import com.badlogic.gdx.maps.tiled.TiledMapTileLayer;
import com.badlogic.gdx.maps.tiled.TmxMapLoader;
import com.badlogic.gdx.math.Circle;
import com.badlogic.gdx.math.Rectangle;
import com.dreamsindevelopment.shirtster.entites.Entity;
import com.dreamsindevelopment.shirtster.entites.Obstacle;

import java.util.ArrayList;

public class TileManager {

    //Tiled map data
    public static TiledMap map;
    public static TiledMapTileLayer[] tiledMapTileLayers;

    private static ArrayList<TiledCell> cellList;
    private static TiledCell cell;

    private static Obstacle obstacle;
    public static ArrayList<Obstacle> obstacles;

    private static Entity entity;
    public static ArrayList<Entity> entities;

    // Tiled map sizes
    public static int tileWidth, tileHeight;
    public static int mapWidth, mapHeight, mapPixelWidth, mapPixelHeight;

    public static TiledMap generateMap(String mapName){

        //Load map
        map = new TmxMapLoader().load("levels/" + mapName + ".tmx");
        mapWidth = map.getProperties().get("width", Integer.class);
        mapHeight = map.getProperties().get("height", Integer.class);
        tileWidth = map.getProperties().get("tilewidth", Integer.class);
        tileHeight = map.getProperties().get("tileheight", Integer.class);
        mapPixelWidth = mapWidth * tileWidth;
        mapPixelHeight = mapHeight * tileHeight;

        //Create layers
        tiledMapTileLayers = new TiledMapTileLayer[map.getLayers().getCount()];
        for(int i=0; i<tiledMapTileLayers.length-1; i++){
            tiledMapTileLayers[i] = (TiledMapTileLayer) map.getLayers().get(i);
        }

        cellList = new ArrayList<TiledCell>();

        for(int i=0; i<tiledMapTileLayers.length; i++) {
            switch (i){
                //Background - No interaction
                case 0: break;

                //Enviroment
                case 1:
                    filterCells(i);

                    obstacles = new ArrayList<Obstacle>();
                    for(int j=0; j<cellList.size(); j++){
                        obstacle = new Obstacle();
                        obstacle.setBoundingBox(new Rectangle(
                                        cellList.get(j).getBounds().x,
                                        cellList.get(j).getBounds().y,
                                        cellList.get(j).getBounds().width,
                                        cellList.get(j).getBounds().height
                                )
                        );
                        obstacles.add(obstacle);
                    }

                    break;

                //Entities
                case 2:
                    filterCells(i);

                    entities = new ArrayList<Entity>();
                    for(int j=0; j<cellList.size(); j++){
                        entity = new Entity(new Circle(
                                cellList.get(j).getBounds().x,
                                cellList.get(j).getBounds().y,
                                cellList.get(j).getBounds().width / 2
                                )
                        );
                        entity.type = cellList.get(j).tiledMapTileLayerCell.getTile().getProperties().get("type") + "";
                        entities.add(entity);

                        //Removes tile from map
                        cellList.get(j).tiledMapTileLayerCell.setTile(null);
                    }
                    break;

                //Decoration - No interaction
                case 3: break;

                //Layer out of bounce
                default:
                    Gdx.app.log("Map error", "Map index is out of bounds: " + i + ", Your .tmx file probably has more layers than programmed");
                    break;
            }
        }

        return map;
    }

    private static void filterCells(int i){

        cellList.clear();

        for(int y=0; y<tiledMapTileLayers[i].getHeight(); y++) {
            for (int x = 0; x < tiledMapTileLayers[i].getWidth(); x++) {

                // Get Cell and check if its a block
                cell = new TiledCell(tiledMapTileLayers[i].getCell(x, y));
                if (cell.tiledMapTileLayerCell == null || cell.tiledMapTileLayerCell.getTile() == null) {
                    continue;
                }
                else{
                    cell.setBounds(
                            x * tileWidth,
                            y * tileHeight,
                            tileWidth,
                            tileHeight
                    );
                    cellList.add(cell);
                }
            }
        }
    }

    public static Entity findEntity(String type){
        for(Entity entity : entities){
            if(entity.type.equals(type)){
                return entity;
            }
        }
        return null;
    }
}
