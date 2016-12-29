package com.dreamsindevelopment.shirtster.entites;

import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.scenes.scene2d.Actor;

public class Obstacle extends Actor {

    public Rectangle boundingBox;
    public float alpha = 1f;

    public Obstacle() {
        super();
    }

    public Obstacle(Rectangle boundingBox) {
        super();
        this.boundingBox = boundingBox;
    }

    @Override
    public void draw(Batch batch, float parentAlpha) {
        super.draw(batch, parentAlpha);
    }

    @Override
    public boolean remove() {
        return super.remove();
    }

    public Rectangle getBoundingBox() {
        return boundingBox;
    }

    public void setBoundingBox(Rectangle boundingBox) {
        this.boundingBox = boundingBox;
    }
}
