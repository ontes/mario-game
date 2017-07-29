package tk.ontes.mario.screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.math.Vector3;

import tk.ontes.mario.Assets;
import tk.ontes.mario.MarioGame;
import tk.ontes.mario.World;
import tk.ontes.mario.entities.Entities;
import tk.ontes.mario.entities.Goomba;
import tk.ontes.mario.tiles.Tiles;
import tk.ontes.mario.entities.Player;

/**
 * Created by ontes on 21.7.17.
 */

public class PlayScreen implements Screen {

    private static final int WORLD_HEIGHT = 14*16;
    private static final int VIEW_HEIGHT = WORLD_HEIGHT-16;
    private static final int MAX_VIEW_WIDTH = 25*16;
    private static final int MIN_VIEW_WIDTH = 16*16;

    public MarioGame game;
    public OrthographicCamera cam;

    public Tiles tiles;
    public Entities entities;
    public World world;

    public PlayScreen(MarioGame game) {
        this.game = game;

        cam = new OrthographicCamera();

        tiles = new Tiles();
        entities = new Entities();
        world = new World(1, tiles, entities);
    }

    @Override
    public void render(float delta) {
        update(delta);

        // Update camera
        cam.update();
        world.renderer.setView(cam);
        game.batch.setProjectionMatrix(cam.combined);

        // Clear screen
        Gdx.gl.glClearColor(world.backgroundColor.r, world.backgroundColor.g, world.backgroundColor.b, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

        // Render
        world.renderer.render();
        game.batch.begin();
        entities.render(game.batch);
        game.batch.end();
    }

    private void update(float delta) {
        entities.update(delta);
        cam.position.set(entities.player.getX()+cam.viewportWidth/4, WORLD_HEIGHT/2, 0);
    }

    @Override
    public void resize(int width, int height) {
        cam.viewportWidth = VIEW_HEIGHT * width / height;
        cam.viewportHeight = VIEW_HEIGHT;

        if(cam.viewportWidth > MAX_VIEW_WIDTH) {
            cam.viewportWidth = MAX_VIEW_WIDTH;
            cam.viewportHeight = MAX_VIEW_WIDTH * height / width;
        }
        else if(cam.viewportWidth < MIN_VIEW_WIDTH) {
            cam.viewportWidth = MIN_VIEW_WIDTH;
            cam.viewportHeight = MIN_VIEW_WIDTH * height / width;
        }
        cam.update();
    }

    @Override
    public void pause() {

    }

    @Override
    public void resume() {

    }

    @Override
    public void hide() {

    }

    @Override
    public void show() {

    }

    @Override
    public void dispose() {
    }
}
