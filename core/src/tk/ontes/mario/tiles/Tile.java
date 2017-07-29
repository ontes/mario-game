package tk.ontes.mario.tiles;

import com.badlogic.gdx.math.Rectangle;

import tk.ontes.mario.Side;
import tk.ontes.mario.World;
import tk.ontes.mario.entities.Entity;

/**
 * Created by ontes on 22.7.17.
 */

public abstract class Tile extends Rectangle {

    public Tile.ID id;
    public boolean exists;

    protected World world;

    public Tile(Rectangle rect, Tile.ID id, World world) {
        set(rect);
        this.id = id;
        exists = true;
        this.world = world;
    }

    public abstract void entityCollision(Entity entity, Side side);

    public enum ID {
        GROUND(true),
        PIPE(true),
        COIN(false),
        BRICKS(true);

        public boolean solid;

        ID (boolean solid) {
            this.solid = solid;
        }
    }
}
