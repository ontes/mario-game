package tk.ontes.mario.entities;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.utils.Array;

import tk.ontes.mario.Side;
import tk.ontes.mario.World;
import tk.ontes.mario.tiles.Tile;
import tk.ontes.mario.tiles.Tiles;

/**
 * Created by ontes on 23.7.17.
 */

public abstract class Entity extends Sprite {

    private static final float COLLISION_OFFSET = 0.01f;

    public Entity.ID id;
    public boolean exists;

    protected World world;

    public Entity(float x, float y, Texture texture, ID id, World world) {
        super(texture);
        setPosition(x, y);

        this.id = id;
        exists = true;

        this.world = world;
    }

    public abstract void update(float delta);

    public abstract void entityCollision(Entity entity, Side side, boolean doneIt);
    public abstract void tileCollision(Tile tile, Side side);

    public abstract void die();

    protected void moveX(float dist) {
        if(dist == 0) return;

        // Move
        setX(getX()+dist);

        if(!exists) return;

        // Get side and call checkCollisions
        if (dist > 0) checkCollisions(Side.RIGHT);
        else checkCollisions(Side.LEFT);
    }

    protected void moveY(float dist) {
        if(dist == 0) return;

        // Move
        setY(getY()+dist);

        if(!exists) return;

        // Get side and call checkCollisions
        if (dist > 0) checkCollisions(Side.TOP);
        else checkCollisions(Side.BOTTOM);
    }

    private void checkCollisions(Side side) {
        // Check for tile collisions
        Array<Tile> collidedTiles = new Array<Tile>();

        for(Tile tile: world.tiles) {
            if(tile.exists && overlaps(tile)) {
                collidedTiles.add(tile);
            }
        }

        // Check for entity collisions
        Array<Entity> collidedEntities = new Array<Entity>();

        for(Entity entity: world.entities) {
            if (entity == this) continue;

            if(entity.exists && overlaps(entity.getBoundingRectangle())) {
                collidedEntities.add(entity);
            }
        }

        // Inform itself and collided tile/entity
        for(Tile tile: collidedTiles) {
            tileCollision(tile, side);
            tile.entityCollision(this, getOppositeSide(side));
        }

        for(Entity entity: collidedEntities) {
            entityCollision(entity, side, true);
            entity.entityCollision(this, getOppositeSide(side), false);
        }
    }

    private boolean overlaps (Rectangle rect) {
        Rectangle thisRect = getBoundingRectangle();
        return rect.x + COLLISION_OFFSET < thisRect.x + thisRect.width
                && rect.x + rect.width > thisRect.x + COLLISION_OFFSET
                && rect.y + COLLISION_OFFSET < thisRect.y + thisRect.height
                && rect.y + rect.height > thisRect.y + COLLISION_OFFSET;
    }

    protected void moveBack(Rectangle rect, Side side) {
        switch (side) {
            case RIGHT:
                if(rect.x - getWidth() < getX()) setX(rect.x - getWidth());
                break;
            case LEFT:
                if(rect.x + rect.width > getX()) setX(rect.x + rect.width);
                break;
            case TOP:
                if(rect.y - getHeight() < getY()) setY(rect.y - getHeight());
                break;
            case BOTTOM:
                if(rect.y + rect.height > getY()) setY(rect.y + rect.height);
                break;
        }
    }

    private Side getOppositeSide(Side side) {
        switch(side) {
            case TOP:
                return Side.BOTTOM;
            case  BOTTOM:
                return Side.TOP;
            case LEFT:
                return Side.RIGHT;
            case RIGHT:
                return Side.LEFT;
            default:
                return side;
        }
    }

    public enum ID {
        PLAYER,
        GOOMBA
    }
}
