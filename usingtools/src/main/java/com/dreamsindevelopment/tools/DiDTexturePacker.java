package com.dreamsindevelopment.tools;

import com.badlogic.gdx.tools.texturepacker.TexturePacker;

public class DiDTexturePacker {

    public static void main (String[] args) throws Exception{
        TexturePacker.process("android/assets/sprites", "android/assets/sprites", "player");
    }
}
