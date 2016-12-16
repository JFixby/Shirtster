package com.dreamsindevelopment.shirtster.entites;

import com.badlogic.gdx.math.Circle;
import com.badlogic.gdx.scenes.scene2d.Actor;

public class Entity extends Actor {

    public String type;
    public Circle boundingCircle, collisionCircle;

    public Entity() {
    }

    public Entity(Circle boundingCircle) {
        this.boundingCircle = boundingCircle;
        this.collisionCircle = new Circle(boundingCircle);
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
