package ru.vanilla.ink.desktop;

import com.badlogic.gdx.backends.lwjgl.LwjglApplication;
import com.badlogic.gdx.backends.lwjgl.LwjglApplicationConfiguration;
import ru.vanilla.ink.core.MainGame;
import ru.vanilla.ink.core.Settings;

public class DesktopLauncher {
	public static void main (String[] arg) {
		LwjglApplicationConfiguration config = new LwjglApplicationConfiguration();

		Settings.INSTANCE.init(1920, 1080);

		config.width = Settings.INSTANCE.getWIDTH();
		config.height = Settings.INSTANCE.getHEIGHT();


		new LwjglApplication(new MainGame(), config);
	}
}
