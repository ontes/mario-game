package tk.ontes.mario.entities;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.maps.objects.RectangleMapObject;
import com.badlogic.gdx.utils.Array;

import tk.ontes.mario.Assets;
import tk.ontes.mario.World;
import tk.ontes.mario.tiles.Tiles;

/**
 * Created by ontes on 23.7.17.
 */

public class Entities extends Array<Entity> {

    private World world;
    public Player player;

    public Entities () {
    }

    public void load(World world) {
        this.world = world;
        clear();

        // Add player
        player = new Player(64, 32, world);
        add(player);

        // Add enemies
        for (RectangleMapObject object: world.getLayer("enemies").getObjects().getByType(RectangleMapObject.class)) {
            if (object.getName().equals("goomba")) {
                add(new Goomba(object.getRectangle().x, object.getRectangle().y, world));
            }
        }
    }

    public void render(SpriteBatch batch) {
        for(int i = 0; i < size; i++) {
            get(size-i-1).draw(batch);
        }
    }

    public void update(float delta) {
        for(int i = 0; i < size; i++) {
            get(size-i-1).update(delta);
        }
    }
}
