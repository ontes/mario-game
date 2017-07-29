package tk.ontes.mario;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.graphics.Texture;

/**
 * Created by ontes on 23.7.17.
 */

public class Assets {

    // Player
    public Texture texMarioSmall;
    // Enemies
    public Texture texGoobaNormal;
    // Sounds
    public Sound soundMarioDie;
    public Sound soundJumpSmall;
    public Sound soundBreakBlock;
    public Sound soundCoin;
    public Sound soundStomp;
    // Music
    public Music musicOverworld;

    public Assets() {
        texMarioSmall = newTexture("mario/mario_small.png");

        texGoobaNormal = newTexture("goomba/goomba.png");

        soundMarioDie = newSound("mario_die.wav");
        soundJumpSmall = newSound("jump_small.wav");
        soundBreakBlock = newSound("break_block.wav");
        soundCoin = newSound("coin.wav");
        soundStomp = newSound("stomp.wav");

        musicOverworld = newMusic("overworld.mp3");
    }

    public void dispose() {
        texMarioSmall.dispose();
        texGoobaNormal.dispose();
        soundMarioDie.dispose();
        soundJumpSmall.dispose();
        soundBreakBlock.dispose();
        soundCoin.dispose();
        soundStomp.dispose();
        musicOverworld.dispose();
    }

    private Texture newTexture(String filename) {
        return new Texture(Gdx.files.internal("textures/").child(filename));
    }

    private Sound newSound(String filename) {
        return Gdx.audio.newSound(Gdx.files.internal("sounds/").child(filename));
    }

    private Music newMusic(String filename) {
        return Gdx.audio.newMusic(Gdx.files.internal("music/").child(filename));
    }
}
