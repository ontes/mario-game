package tk.ontes.mario;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.graphics.FPSLogger;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

import tk.ontes.mario.screens.PlayScreen;

public class MarioGame extends Game {

	public SpriteBatch batch;
    public static Assets assets;

	@Override
	public void create () {
		batch = new SpriteBatch();
        assets = new Assets();

        setScreen(new PlayScreen(this));
	}

	@Override
	public void render () {
        super.render();
	}
	
	@Override
	public void dispose () {
		batch.dispose();
        assets.dispose();
	}
}
