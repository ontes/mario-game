package tk.ontes.mario;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.maps.MapLayer;
import com.badlogic.gdx.maps.tiled.TiledMap;
import com.badlogic.gdx.maps.tiled.TiledMapTileLayer;
import com.badlogic.gdx.maps.tiled.TmxMapLoader;
import com.badlogic.gdx.maps.tiled.renderers.OrthogonalTiledMapRenderer;
import com.badlogic.gdx.math.Rectangle;

import tk.ontes.mario.entities.Entities;
import tk.ontes.mario.screens.PlayScreen;
import tk.ontes.mario.tiles.Tiles;

/**
 * Created by ontes on 22.7.17.
 */

public class World {

    public static final int GRAVITY = -32*16;
    private static final Color BACKGROUND_OVERWORLD = new Color(99/255f, 173/255f, 255/255f, 1);

    public Tiles tiles;
    public Entities entities;

    public TiledMap map;
    private TmxMapLoader loader;
    public OrthogonalTiledMapRenderer renderer;
    private TiledMapTileLayer graphicsLayer;

    public int worldNum;
    public Color backgroundColor = new Color(1, 1, 1, 1);

    public World(int worldNum, Tiles tiles, Entities entities) {
        this.tiles = tiles;
        this.entities = entities;

        loader = new TmxMapLoader();

        load(worldNum);
    }

    public void load(int worldNum) {
        this.worldNum = worldNum;

        String file = "worlds/world-" + Integer.toString(worldNum) + ".tmx";
        map = loader.load(file);

        if(renderer == null) renderer = new OrthogonalTiledMapRenderer(map);
        else renderer.setMap(map);

        graphicsLayer = (TiledMapTileLayer)getLayer("graphics");

        backgroundColor.set(BACKGROUND_OVERWORLD);

        tiles.load(this);
        entities.load(this);

        MarioGame.assets.musicOverworld.setLooping(true);
        MarioGame.assets.musicOverworld.play();
    }

    public MapLayer getLayer (String layerName) {
        return map.getLayers().get(layerName);
    }

    public TiledMapTileLayer.Cell getCell (Rectangle rect) {
        int x = (int)rect.x/16;
        int y = (int)rect.y/16;
        return graphicsLayer.getCell(x, y);
    }

    /*public TiledMapTileLayer.Cell getCell (int x, int y) {
        return  graphicsLayer.getCell(x, y);
    }*/
}
