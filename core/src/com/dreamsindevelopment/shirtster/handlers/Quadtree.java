package com.dreamsindevelopment.shirtster.handlers;

import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.utils.Array;
import com.dreamsindevelopment.shirtster.entites.Entity;

import java.util.ArrayList;

public class Quadtree {

    private final int MAX_OBJECTS = 10;
    private final int MAX_LEVELS = 5;

    private int level;
    private ArrayList<Entity> entities;
    private Rectangle bounds;

    private int index;
    private float vCenter, hCenter;
    private boolean topQuad, bottomQuad;

    private Quadtree[] childTrees;
    private final int PARENT = -1;
    private final int NORTH_WEST = 0;
    private final int NORTH_EAST = 1;
    private final int SOUTH_WEST = 2;
    private final int SOUTH_EAST = 3;

    private int childWidth, childHeight;

    public Quadtree(int level, Rectangle bounds) {

        this.level = level;
        entities = new ArrayList<Entity>();
        this.bounds = bounds;
        childTrees = new Quadtree[4];

        childWidth = (int) bounds.width / 2;
        childHeight = (int) bounds.height / 2;
    }

    public void clear(){

        entities.clear();

        for(int i=0; i<childTrees.length; i++){
            if(childTrees[0] != null){
                childTrees[i].clear();
                childTrees[i] = null;
            }
        }
    }

    private void split(){

        //Split tree in for quadrants
        childTrees[NORTH_WEST] = new Quadtree(level+1, new Rectangle(bounds.x + childWidth, bounds.y + childWidth, childWidth, childHeight));
        childTrees[NORTH_EAST] = new Quadtree(level+1, new Rectangle(bounds.x, bounds.y + childHeight, childWidth, childHeight));
        childTrees[SOUTH_WEST] = new Quadtree(level+1, new Rectangle(bounds.x + childWidth, bounds.y, childWidth, childHeight));
        childTrees[SOUTH_EAST] = new Quadtree(level+1, new Rectangle(bounds.x, bounds.y, childWidth, childHeight));
    }

    public int getIndex(Entity entity){

        index = PARENT;
        vCenter = bounds.x + childWidth;
        hCenter = bounds.y + childHeight;

        //Check if entity is completely in the upper or lower half
        topQuad = (entity.boundingCircle.y - entity.boundingCircle.radius > hCenter);
        bottomQuad = (entity.boundingCircle.y + entity.boundingCircle.radius < hCenter);

        //Check if entity is completely in the right or left half
        //Then determine in wich the entity fits completely else its in the parent
        if(entity.boundingCircle.x + entity.boundingCircle.radius > vCenter){
            if(topQuad){
                index = NORTH_EAST;
            }
            else if(bottomQuad){
                index = SOUTH_EAST;
            }
        }
        else if(entity.boundingCircle.x + entity.boundingCircle.radius < vCenter){
            if(topQuad){
                index = NORTH_WEST;
            }
            else if(bottomQuad){
                index = SOUTH_WEST;
            }
        }

        return index;
    }

    public void insert(Entity entity){

        //Check if tree is already split
        if(childTrees[0] != null){
            index = getIndex(entity);

            //Add entity to the child tree
            if(index != PARENT){
                childTrees[index].insert(entity);

                return;
            }
        }

        entities.add(entity);

        //Check if split is possible and necessary
        if(entities.size() > MAX_OBJECTS && level < MAX_LEVELS){
            if(childTrees[0] == null){
                split();
            }

            //If tree gets split, insert entities in new child trees
            int i = 0;
            while(i < entities.size()){
                index = getIndex(entities.get(i));

                if(index != PARENT){
                    childTrees[index].insert(entities.remove(i));
                }
                else {
                    i++;
                }
            }
        }
    }

    public ArrayList<Entity> retrieve(ArrayList<Entity> retrievedEntities, Entity entity){

        //Retrieve all entities from the given entity's tree
        index = getIndex(entity);
        if(index != PARENT && childTrees[0] != null){
            childTrees[index].retrieve(retrievedEntities, entity);
        }

        //If this is the last tree the given entity fits in, retrieve all entities from all trees
        if(index == PARENT && childTrees[0] != null){
            for(int i=0; i<childTrees.length; i++){
                childTrees[i].retrieve(retrievedEntities, entity);
            }
        }

        //Add all entities from this tree
        retrievedEntities.addAll(entities);

        return retrievedEntities;
    }
}
