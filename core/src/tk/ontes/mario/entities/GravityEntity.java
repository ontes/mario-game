package tk.ontes.mario.entities;

import com.badlogic.gdx.graphics.Texture;

import tk.ontes.mario.Side;
import tk.ontes.mario.World;
import tk.ontes.mario.tiles.Tiles;

/**
 * Created by ontes on 24.7.17.
 */

public abstract class GravityEntity extends Entity {

    public float velY;
    public boolean onGround;

    public GravityEntity(float x, float y, Texture texture, ID id, World world) {
        super(x, y, texture, id, world);
    }

    protected void updateGravity(float delta) {
        velY += World.GRAVITY * delta;
        onGround = false;
        moveY(velY*delta);
    }

    protected void setOnGround(Side side) {
        if(side == Side.BOTTOM && velY < 0) {
            onGround = true;
            velY = 0;
        }
        else if(side == Side.TOP) {
            velY = 0;
        }
    }
}
