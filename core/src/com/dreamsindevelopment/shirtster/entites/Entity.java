package com.dreamsindevelopment.shirtster.entites;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Circle;
import com.badlogic.gdx.math.Intersector;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.dreamsindevelopment.shirtster.utils.Assets;
import com.dreamsindevelopment.shirtster.utils.TileManager;

import java.util.ArrayList;

public class Entity extends Actor {

    public String type;

    public boolean collided;
    public Circle boundingCircle, collisionCircle;
    public ArrayList<Actor> nearbyActors;

    protected TextureAtlas textureAtlas;
    protected TextureRegion leftTexture, rightTexture, currentTexture;
    public float alpha = 1f;

    protected float baseVelocity = 64f, gravity = -32f;
    protected boolean effectedByGravity = true;
    protected Vector2 velocity;

    public Entity(){

    }

    public Entity(Circle boundingCircle) {
        collided = false;

        this.boundingCircle = boundingCircle;
        this.collisionCircle = new Circle(boundingCircle);

        nearbyActors = new ArrayList<Actor>();

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
        collisionCircle.y += velocity.y * delta;
        if(effectedByGravity) {
            collisionCircle.y += gravity * delta;
        }

        collided = checkCollision();

        if(!collided){
            boundingCircle.x = collisionCircle.x;
            boundingCircle.y = collisionCircle.y;

            collided = false;
        }
        else{
            collisionCircle.x = boundingCircle.x;
            collisionCircle.y = boundingCircle.y;
        }
    }

    @Override
    public void draw(Batch batch, float parentAlpha) {
        super.draw(batch, alpha);

        batch.draw(currentTexture, boundingCircle.x - boundingCircle.radius, boundingCircle.y - boundingCircle.radius);
    }

    public boolean checkCollision(){

        for(Entity entity : TileManager.entities){
            if(collisionCircle.overlaps(entity.boundingCircle)){
                return true;
            }
        }

        for(Obstacle obstacle : TileManager.obstacles){
            if(Intersector.overlaps(collisionCircle, obstacle.boundingBox)){
                return true;
            }
        }

        return false;
    }
}
