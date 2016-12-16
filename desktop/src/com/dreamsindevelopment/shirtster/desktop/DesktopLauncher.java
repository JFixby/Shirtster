package com.dreamsindevelopment.shirtster.desktop;

import com.badlogic.gdx.backends.lwjgl.LwjglApplication;
import com.badlogic.gdx.backends.lwjgl.LwjglApplicationConfiguration;
import com.dreamsindevelopment.shirtster.ShirtsterGame;

public class DesktopLauncher {
	public static void main (String[] arg) {
		LwjglApplicationConfiguration config = new LwjglApplicationConfiguration();

		config.title = ShirtsterGame.TITLE;
		config.width = ShirtsterGame.V_WIDTH;
		config.height = ShirtsterGame.V_HEIGHT;

		new LwjglApplication(new ShirtsterGame(), config);
	}
}
