package com.dreamsindevelopment.shirtster;

import com.badlogic.gdx.assets.AssetManager;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;

public class Assets {

    public static final AssetManager assetManager = new AssetManager();

    public static final String playerSpritesheet = "sprites/player.atlas";
    //TODO: Load all skins from skin directory and make them changable in-game
    public static final String skinDefault = "skin/default/uiskin.json";
    public static final String skinPixthulhu = "skin/pixthulhu/pixthulhu-ui.json";

    public static TextureAtlas blockTextureAtlas;
    public static Skin defaultSkin, pixthulhuSkin;

    public static void load(){
        assetManager.load(playerSpritesheet, TextureAtlas.class);

        assetManager.load(skinPixthulhu, Skin.class);
        assetManager.load(skinDefault, Skin.class);

        assetManager.finishLoading();
        //while(!assetManager.update()){} TODO: Decide for one method

        blockTextureAtlas = assetManager.get(playerSpritesheet);
        defaultSkin = assetManager.get(skinDefault);
        pixthulhuSkin = assetManager.get(skinPixthulhu);
    }

    public static void dispose(){
        assetManager.dispose();
    }
}
