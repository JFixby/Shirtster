package com.dreamsindevelopment.shirtster.screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.maps.tiled.TiledMap;
import com.badlogic.gdx.maps.tiled.renderers.OrthogonalTiledMapRenderer;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.dreamsindevelopment.shirtster.entites.Entity;
import com.dreamsindevelopment.shirtster.entites.Obstacle;
import com.dreamsindevelopment.shirtster.handlers.Quadtree;
import com.dreamsindevelopment.shirtster.utils.Assets;
import com.dreamsindevelopment.shirtster.ShirtsterGame;
import com.dreamsindevelopment.shirtster.entites.Player;
import com.dreamsindevelopment.shirtster.utils.TileManager;

import java.util.ArrayList;

public class PlayScreen extends GameScreen {

    private TiledMap map;
    private OrthogonalTiledMapRenderer renderer;

    Quadtree quadtree;
    ArrayList<Entity> entityArrayList;

    private Player player;

    public PlayScreen(ShirtsterGame game) {
        super(game);
    }

    @Override
    public void show() {
        super.show();

        map = TileManager.generateMap("level_1");
        renderer = new OrthogonalTiledMapRenderer(map, spriteBatch);

        for(Obstacle obstacle : TileManager.obstacles){
            stage.addActor(obstacle);
        }

        for(Entity entity : TileManager.entities){
            if(!entity.type.equals("player")){
                stage.addActor(entity);
            }
        }

        player = new Player(TileManager.findEntity("player"));
        stage.addActor(player);

        quadtree = new Quadtree(0, new Rectangle(0, 0, Gdx.graphics.getWidth(), Gdx.graphics.getHeight()));
        entityArrayList = new ArrayList<Entity>();
        entityArrayList = quadtree.retrieve(entityArrayList, player);

        Gdx.input.setInputProcessor(player);
    }

    @Override
    public void render(float delta) {
        super.render(delta);

        if(player.boundingCircle.x > (camera.viewportWidth/2) && player.boundingCircle.x < (TileManager.mapPixelWidth - camera.viewportWidth/2)){
            camera.position.x = player.boundingCircle.x;//TODO: Is this necessary? (player.getX() - camera.position.x) * leap * delta;
        }
        if(player.boundingCircle.y >= (camera.viewportHeight/2) && player.boundingCircle.y <= (TileManager.mapPixelHeight - camera.viewportHeight/2)){
            camera.position.y = player.boundingCircle.y;//TODO: Is this necessary? (player.getY() - camera.position.y) * leap * delta;
        }
        camera.update();

        renderer.setView(camera);
        renderer.render();

        for(Actor actor : stage.getActors()){
        }

        quadtree.clear();
        for(Entity entity : TileManager.entities){
            quadtree.insert(entity);
        }

        for(Entity entity : TileManager.entities){
            entityArrayList.clear();
            quadtree.retrieve(entityArrayList, player);

            //TODO: Adjust sizes to only check visible entities
            for(Entity entity1 : entityArrayList){
                Gdx.app.log("No.", "" + entityArrayList.size());
            }
        }

        stage.act(delta);
        stage.draw();
    }

    @Override
    public void pause() {

    }

    @Override
    public void resume() {

    }

    @Override
    public void hide() {

    }

    @Override
    public void dispose() {
        map.dispose();
        renderer.dispose();
        Assets.dispose();
    }

    private boolean checkCollision(){

        return false;
    }
}
