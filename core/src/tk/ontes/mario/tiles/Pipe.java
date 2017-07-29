package tk.ontes.mario.tiles;

import com.badlogic.gdx.maps.tiled.TiledMapTileLayer;
import com.badlogic.gdx.math.Rectangle;

import tk.ontes.mario.Side;
import tk.ontes.mario.World;
import tk.ontes.mario.entities.Entity;

/**
 * Created by ontes on 22.7.17.
 */

public class Pipe extends Tile {
    public Pipe(Rectangle rect, World world) {
        super(rect, ID.PIPE, world);
    }

    @Override
    public void entityCollision(Entity entity, Side side) {

    }
}
