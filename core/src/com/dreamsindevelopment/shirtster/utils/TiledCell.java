package com.dreamsindevelopment.shirtster.utils;

import com.badlogic.gdx.maps.tiled.TiledMapTileLayer;
import com.badlogic.gdx.math.Rectangle;

public class TiledCell extends TiledMapTileLayer.Cell{

    public Rectangle bounds;
    public TiledMapTileLayer.Cell tiledMapTileLayerCell;

    public TiledCell() {
    }

    public TiledCell(TiledMapTileLayer.Cell tiledMapTileLayerCell) {
        this.tiledMapTileLayerCell = tiledMapTileLayerCell;
    }

    public TiledCell(float x, float y, float width, float height) {
        bounds = new Rectangle(x, y, width, height);
    }

    public TiledCell(Rectangle rectangle) {
        this.bounds = rectangle;
    }

    public Rectangle getBounds() {
        return bounds;
    }

    public void setBounds(float x, float y, float width, float height) {
        this.bounds = new Rectangle(x, y, width, height);
    }
}
