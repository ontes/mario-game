package tk.ontes.mario.tiles;

import com.badlogic.gdx.maps.tiled.TiledMapTileLayer;
import com.badlogic.gdx.math.Rectangle;

import tk.ontes.mario.Side;
import tk.ontes.mario.World;
import tk.ontes.mario.entities.Entity;

/**
 * Created by ontes on 22.7.17.
 */

public class Ground extends Tile {
    public Ground(Rectangle rect, World world) {
        super(rect, ID.GROUND, world);
    }

    @Override
    public void entityCollision(Entity entity, Side side) {

    }
}
