package tk.ontes.mario.desktop;

import com.badlogic.gdx.backends.lwjgl.LwjglApplication;
import com.badlogic.gdx.backends.lwjgl.LwjglApplicationConfiguration;
import tk.ontes.mario.MarioGame;

public class DesktopLauncher {
	public static void main (String[] arg) {
		LwjglApplicationConfiguration config = new LwjglApplicationConfiguration();
		config.title = "Super Mario Bros";
		config.width = 23*16*4;
        config.height = 13*16*4;
		new LwjglApplication(new MarioGame(), config);
	}
}
