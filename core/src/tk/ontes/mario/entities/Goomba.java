package tk.ontes.mario.entities;

import tk.ontes.mario.MarioGame;
import tk.ontes.mario.Side;
import tk.ontes.mario.World;
import tk.ontes.mario.tiles.Tile;
import tk.ontes.mario.tiles.Tiles;

/**
 * Created by ontes on 23.7.17.
 */

public class Goomba extends GravityEntity{

    private static final int SPEED = 20;
    private static final int KILL_JUMP_HEIGHT = 12*16;

    private boolean direction; // true=right false=left

    private int textureNum;
    private float textureTime;

    public Goomba(float x, float y, World world) {
        super(x, y, MarioGame.assets.texGoobaNormal, ID.GOOMBA, world);
        setRegion(0, 0, 16, 16);
        setSize(16, 16);
        direction = false;
    }

    @Override
    public void update(float delta) {
        // If is outside view return
        if (getX() < world.entities.player.getX()-16*8 || getX() > world.entities.player.getX()+16*20) return;

        // Update movement
        if(exists) {
            updateGravity(delta);
            if (direction) moveX(SPEED * delta);
            else moveX(-SPEED * delta);
        }

        // Update texture
        textureTime+=delta;
        if (textureNum < 2 && textureTime>0.2f) {
            if (textureNum == 1) setCurrTexture(0);
            else setCurrTexture(1);
        }
        if (!exists && textureTime>0.5f) {
            world.entities.removeValue(this, true);
        }
    }

    @Override
    public void entityCollision(Entity entity, Side side, boolean doneIt) {

        if (entity.id == ID.PLAYER) {
            if(side == Side.TOP) {
                ((GravityEntity)entity).velY = KILL_JUMP_HEIGHT;
                ((Player)entity).action = Player.Action.JUMPING;
                die();
            }
            else {
                entity.die();
            }
        }
        else {
            direction = !direction;
            if (doneIt) moveBack(entity.getBoundingRectangle(), side);
        }
    }

    @Override
    public void tileCollision(Tile tile, Side side) {
        if (tile.id.solid) {
            moveBack(tile, side);
            setOnGround(side);
            if (side == Side.LEFT || side == Side.RIGHT) direction = !direction;
        }
    }


    public void die() {
        exists = false;
        MarioGame.assets.soundStomp.play();
        setCurrTexture(2);
    }

    private void setCurrTexture(int textureNum) {
        setRegion(textureNum*16, 0, 16, 16);
        this.textureNum = textureNum;
        textureTime = 0;
    }
}
