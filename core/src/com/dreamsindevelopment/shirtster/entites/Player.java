package com.dreamsindevelopment.shirtster.entites;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.InputProcessor;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.dreamsindevelopment.shirtster.utils.Assets;

public class Player extends Entity implements InputProcessor {

    private boolean jumped, jump;

    public Player(Entity entity) {
        super(entity.boundingCircle);

        textureAtlas = (TextureAtlas) Assets.get("playerTextureAtlas");
        leftTexture = textureAtlas.findRegion("player_left");
        rightTexture = textureAtlas.findRegion("player_right");
        currentTexture = rightTexture;

        Gdx.input.setInputProcessor(this);
    }

    @Override
    public void act(float delta){
        super.act(delta);
    }

    @Override
    public void draw(Batch batch, float parentAlpha) {
        super.draw(batch, parentAlpha);
    }

    @Override
    public boolean keyDown(int keycode) {
        switch (keycode){
            case 51:// W
                velocity.y = baseVelocity;
                jump = true;
                break;

            case 29:// A
                velocity.x = -baseVelocity;
                currentTexture = leftTexture;
                break;

            // TODO: Maybe for climbing down things (e.g. ladders)
            case 47:// S
                velocity.y = -baseVelocity;
                break;

            case 32:// D
                velocity.x = baseVelocity;
                currentTexture = rightTexture;
                break;

            default: break;
        }

        return false;
    }

    @Override
    public boolean keyUp(int keycode) {

        switch (keycode){
            case 51:// W
                velocity.y = 0f;
                jump = false;
                break;

            case 29:// A
                velocity.x = 0f;
                break;

            case 47:// S
                velocity.y = 0f;
                break;

            case 32:// D
                velocity.x = 0f;
                break;

            default: break;
        }

        return false;
    }

    @Override
    public boolean keyTyped(char character) {
        return false;
    }

    @Override
    public boolean touchDown(int screenX, int screenY, int pointer, int button) {
        return false;
    }

    @Override
    public boolean touchUp(int screenX, int screenY, int pointer, int button) {
        return false;
    }

    @Override
    public boolean touchDragged(int screenX, int screenY, int pointer) {
        return false;
    }

    @Override
    public boolean mouseMoved(int screenX, int screenY) {
        return false;
    }

    @Override
    public boolean scrolled(int amount) {
        return false;
    }
}
