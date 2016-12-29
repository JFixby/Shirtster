package com.dreamsindevelopment.shirtster.entites;

import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Circle;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.dreamsindevelopment.shirtster.utils.Assets;

public class Entity extends Actor {

    public String type;
    public Circle boundingCircle, collisionCircle;

    protected TextureAtlas textureAtlas;
    protected TextureRegion leftTexture, rightTexture, currentTexture;
    public float alpha = 1f;

    protected float baseVelocity = 64f, gravity = 96f;
    protected boolean effectedByGravity = true;
    protected Vector2 velocity;

    public Entity(Circle boundingCircle) {
        this.boundingCircle = boundingCircle;
        this.collisionCircle = new Circle(boundingCircle);

        velocity = new Vector2(0f, 0f);

        textureAtlas = (TextureAtlas) Assets.get("playerTextureAtlas");
        leftTexture = textureAtlas.findRegion("player_left");
        rightTexture = textureAtlas.findRegion("player_right");
        currentTexture = rightTexture;
    }

    @Override
    public void act(float delta) {
        super.act(delta);

        collisionCircle.x += velocity.x * delta;
        if(effectedByGravity) {
            collisionCircle.y += gravity * delta;
        }

        boundingCircle.x += velocity.x * delta;
    }

    @Override
    public void draw(Batch batch, float parentAlpha) {
        super.draw(batch, alpha);

        batch.draw(currentTexture, boundingCircle.x, boundingCircle.y);
    }

    public Circle getBoundingCircle() {
        return boundingCircle;
    }

    public void setBoundingCircle(Circle boundingCircle) {
        this.boundingCircle = boundingCircle;
    }

    public Circle getCollisionCircle() {
        return collisionCircle;
    }

    public void setCollisionCircle(Circle collisionCircle) {
        this.collisionCircle = collisionCircle;
    }
}
