package tk.ontes.mario.entities;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

import tk.ontes.mario.MarioGame;
import tk.ontes.mario.Side;
import tk.ontes.mario.World;
import tk.ontes.mario.tiles.Tile;
import tk.ontes.mario.tiles.Tiles;

/**
 * Created by ontes on 22.7.17.
 */

public class Player extends GravityEntity {

    private static final int WALKING_SPEED = 6*16;
    private static final int SPRINTING_SPEED = 8*16;
    private static final int JUMP_HEIGHT = 18*16;
    private static final int DIE_JUMP_HEIGHT = 12*16;

    public Action action;
    public boolean direction; // true=right false=left

    private int textureNum;
    private float textureTime;

    public Player(float x, float y, World world) {
        super(x, y, MarioGame.assets.texMarioSmall, ID.PLAYER, world);
        setSize(16, 16);
        direction = true;
        textureNum = -1;
        setTextureNum(0);
    }

    public void update(float delta) {
        handleInput(delta);
        updateGravity(delta);

        // Dying
        if(getY()<0 && exists) die();
        if(getX()<0 && !exists) world.entities.removeValue(this, true);

        // Update texture
        textureTime += delta;

        if (!exists) {
            setTextureNum(6);
            return;
        }

        if (action == Action.STANDING) {
            setTextureNum(0);
        }
        if (action == Action.JUMPING) {
            setTextureNum(5);
        }
        if ((action == Action.WALKING || action == Action.SPRINTING) && (textureNum == 0 || textureNum > 3)) {
            setTextureNum(1);
        }
        if ((action == Action.WALKING && textureTime > 0.2) || (action == Action.SPRINTING && textureTime > 0.1)) {
            if (textureNum == 3) setTextureNum(1);
            else setTextureNum(textureNum+1);
        }

        setFlip(!direction, false);
    }

    private void handleInput(float delta) {
        if(!exists)return;

        // Sprint
        boolean sprinting = Gdx.input.isKeyPressed(Input.Keys.SHIFT_LEFT);

        // Jump
        if(Gdx.input.isKeyJustPressed(Input.Keys.W) && onGround) {
            MarioGame.assets.soundJumpSmall.play();
            action = Action.JUMPING;
            velY = JUMP_HEIGHT;
            onGround = false;
        }

        // Horizontal movement
        if(Gdx.input.isKeyPressed(Input.Keys.A) && !Gdx.input.isKeyPressed(Input.Keys.D)) {
            direction = false;
            if (sprinting) {
                if (onGround) action = Action.SPRINTING;
                moveX(-SPRINTING_SPEED*delta);
            }
            else {
                if (onGround) action = Action.WALKING;
                moveX(-WALKING_SPEED*delta);
            }
        }
        else if(Gdx.input.isKeyPressed(Input.Keys.D) && !Gdx.input.isKeyPressed(Input.Keys.A)) {
            direction = true;
            if (sprinting) {
                if (onGround) action = Action.SPRINTING;
                moveX(SPRINTING_SPEED*delta);
            }
            else {
                if (onGround) action = Action.WALKING;
                moveX(WALKING_SPEED*delta);
            }
        }
        else if(onGround) {
            action = Action.STANDING;
        }

        // In air action
        if(!onGround && action != Action.JUMPING) {
            action = Action.IN_AIR;
        }
    }

    @Override
    public void entityCollision(Entity entity, Side side, boolean doneIt) {

    }

    @Override
    public void tileCollision(Tile tile, Side side) {
        if (tile.id.solid) {
            moveBack(tile, side);
            setOnGround(side);
        }
    }

    @Override
    public void die() {
        exists = false;
        MarioGame.assets.musicOverworld.stop();
        MarioGame.assets.soundMarioDie.play();
        if(getY()>0) velY = DIE_JUMP_HEIGHT;
    }

    private void setTextureNum(int textureNum) {
        if(textureNum == this.textureNum) return;

        textureTime = 0;
        setRegion(textureNum*16, 0, 16, 16);
        this.textureNum = textureNum;
    }

    /*private void setState(PlayerState state) {
        this.state = state;

        String filename;
        switch (state) {
            case SMALL:
                filename = "player/mario_small.png";
                break;
            case BIG:
                filename = "player/mario_big.png";
                break;
            case FIRE:
                filename = "player/fire_big.png";
                break;
            default:
                filename = "player/mario_small.png";
        }
        Texture oldTexture = getTexture();
        setTexture(new Texture(filename));
        oldTexture.dispose();
    }

    public enum PlayerState {
        SMALL, BIG, FIRE
    }*/

    public enum Action {
        STANDING, JUMPING, WALKING, SPRINTING, IN_AIR;
    }
}