package tk.ontes.mario.tiles;

import com.badlogic.gdx.maps.tiled.TiledMapTileLayer;
import com.badlogic.gdx.math.Rectangle;

import tk.ontes.mario.MarioGame;
import tk.ontes.mario.Side;
import tk.ontes.mario.World;
import tk.ontes.mario.entities.Entity;

/**
 * Created by ontes on 22.7.17.
 */

public class Bricks extends Tile {

    public Bricks(Rectangle rect, World world) {
        super(rect, ID.BRICKS, world);
    }

    @Override
    public void entityCollision(Entity entity, Side side) {
        if (entity.id == Entity.ID.PLAYER && side == Side.BOTTOM) {
            MarioGame.assets.soundBreakBlock.play();
            world.getCell(this).setTile(null);
            world.tiles.removeValue(this, true);
        }
    }
}
