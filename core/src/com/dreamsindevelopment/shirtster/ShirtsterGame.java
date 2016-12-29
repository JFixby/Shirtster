package com.dreamsindevelopment.shirtster;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.utils.viewport.FitViewport;
import com.dreamsindevelopment.shirtster.screens.GameScreen;
import com.dreamsindevelopment.shirtster.screens.PlayScreen;
import com.dreamsindevelopment.shirtster.utils.Assets;

import java.util.Stack;


public class ShirtsterGame extends Game {

    public static final String TITLE = "Shirtster";
    public static final int V_WIDTH = 1024;
    public static final int V_HEIGHT = 768;
    public static final float W_WIDTH = 640f;
    public static final float W_HEIGHT = 480f;

    public SpriteBatch spriteBatch;
    public OrthographicCamera camera, hudCamera;

    public Stage stage;
    public Skin skin;

    public Stack<GameScreen> screens;

    @Override
    public void create() {
        com.dreamsindevelopment.shirtster.utils.Assets.load();

        camera = new OrthographicCamera(Gdx.graphics.getWidth(), Gdx.graphics.getHeight());
        hudCamera = new OrthographicCamera(Gdx.graphics.getWidth(), Gdx.graphics.getHeight());
        spriteBatch = new SpriteBatch();

        stage = new Stage(new FitViewport(W_WIDTH, W_HEIGHT, camera));
        skin = (Skin) Assets.get("pixthulhuSkin");

        Gdx.input.setInputProcessor(stage);

        screens = new Stack<GameScreen>();
        screens.push(new PlayScreen(this));

        setScreen(screens.peek());
    }

    @Override
    public void dispose() {
        super.dispose();
    }

    @Override
    public void render() {
        super.render();
    }

    @Override
    public void resize(int width, int height) {
        super.resize(width, height);
    }

    @Override
    public void pause() {
        super.pause();
    }

    @Override
    public void resume() {
        super.resume();
    }
}
