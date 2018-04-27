package com.shogi.game.desktop;

import com.badlogic.gdx.backends.lwjgl.LwjglApplication;
import com.badlogic.gdx.backends.lwjgl.LwjglApplicationConfiguration;
import com.shogi.game.Constants;
import com.shogi.game.Game;
import com.shogi.game.Main;

public class DesktopLauncher {
	public static void main (String[] arg) {
		LwjglApplicationConfiguration config = new LwjglApplicationConfiguration();
		config.height = Constants.WINDOW_HEIGHT;
		config.width = Constants.WINDOW_WIDTH;
		new LwjglApplication(new Main(), config);
	}
}
