package com.dreamsindevelopment.shirtster.screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.maps.tiled.TiledMap;
import com.badlogic.gdx.maps.tiled.renderers.OrthogonalTiledMapRenderer;
import com.badlogic.gdx.math.Circle;
import com.dreamsindevelopment.shirtster.Assets;
import com.dreamsindevelopment.shirtster.ShirtsterGame;
import com.dreamsindevelopment.shirtster.entites.Player;
import com.dreamsindevelopment.shirtster.handlers.TileManager;

import java.awt.Rectangle;

public class PlayScreen extends GameScreen {

    private TiledMap map;
    private OrthogonalTiledMapRenderer renderer;

    private Player player;

    public PlayScreen(ShirtsterGame game) {
        super(game);
    }

    @Override
    public void show() {
        super.show();

        map = TileManager.generateMap("level_1");
        renderer = new OrthogonalTiledMapRenderer(map, spriteBatch);

        player = new Player(TileManager.findEntity("player"));
        stage.addActor(player);

        Gdx.input.setInputProcessor(player);
    }

    @Override
    public void render(float delta) {
        super.render(delta);

        //TODO: Set coordinates to boundingCircle coordinates
        if(player.getX() > (camera.viewportWidth/2) && player.getX() < (TileManager.mapWidth - camera.viewportWidth/2)){
            camera.position.x = player.getX();//TODO: Is this necessary? (player.getX() - camera.position.x) * leap * delta;
        }
        if(player.getY() >= (camera.viewportHeight/2) && player.getY() <= (TileManager.mapHeight - camera.viewportHeight/2)){
            camera.position.y = player.getY();//TODO: Is this necessary? (player.getY() - camera.position.y) * leap * delta;
        }
        camera.update();

        renderer.setView(camera);
        renderer.render();

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
}
