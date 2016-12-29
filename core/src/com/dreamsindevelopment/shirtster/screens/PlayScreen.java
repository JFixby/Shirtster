package com.dreamsindevelopment.shirtster.screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.maps.tiled.TiledMap;
import com.badlogic.gdx.maps.tiled.renderers.OrthogonalTiledMapRenderer;
import com.dreamsindevelopment.shirtster.entites.Entity;
import com.dreamsindevelopment.shirtster.entites.Obstacle;
import com.dreamsindevelopment.shirtster.utils.Assets;
import com.dreamsindevelopment.shirtster.ShirtsterGame;
import com.dreamsindevelopment.shirtster.entites.Player;
import com.dreamsindevelopment.shirtster.utils.TileManager;

import java.util.ArrayList;

public class PlayScreen extends GameScreen {

    private TiledMap map;
    private OrthogonalTiledMapRenderer renderer;

    private ArrayList<Obstacle> obstacles;
    private Player player;

    public PlayScreen(ShirtsterGame game) {
        super(game);
    }

    @Override
    public void show() {
        super.show();

        map = TileManager.generateMap("level_1");
        renderer = new OrthogonalTiledMapRenderer(map, spriteBatch);

        obstacles = TileManager.obstacles;

        for(Entity entity : TileManager.entities){
            if(!entity.type.equals("player")){
                stage.addActor(entity);
            }
        }

        player = new Player(TileManager.findEntity("player"));
        stage.addActor(player);

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

        spriteBatch.begin();
        for(Obstacle obstacle : obstacles){
            if(obstacle.boundingBox.x < player.boundingCircle.x + Gdx.graphics.getWidth() && obstacle.boundingBox.x > player.boundingCircle.x - Gdx.graphics.getWidth()){
                obstacle.draw(spriteBatch, obstacle.alpha);
            }
        }
        spriteBatch.end();

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
