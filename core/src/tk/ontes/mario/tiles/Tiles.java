package tk.ontes.mario.tiles;

import com.badlogic.gdx.maps.objects.RectangleMapObject;
import com.badlogic.gdx.utils.Array;

import tk.ontes.mario.World;

/**
 * Created by ontes on 22.7.17.
 */

public class Tiles extends Array<Tile>{

    public World world;

    public void load(World world) {
        this.world = world;
        clear();

        for (RectangleMapObject object: world.getLayer("ground").getObjects().getByType(RectangleMapObject.class)) {
            add(new Ground(object.getRectangle(), world));
        }
        for (RectangleMapObject object: world.getLayer("pipes").getObjects().getByType(RectangleMapObject.class)) {
            add(new Pipe(object.getRectangle(), world));
        }
        for (RectangleMapObject object: world.getLayer("bricks").getObjects().getByType(RectangleMapObject.class)) {
            add(new Bricks(object.getRectangle(), world));
        }
        for (RectangleMapObject object: world.getLayer("qblocks").getObjects().getByType(RectangleMapObject.class)) {
            add(new Bricks(object.getRectangle(), world));
        }
        for (RectangleMapObject object: world.getLayer("coins").getObjects().getByType(RectangleMapObject.class)) {
            add(new Coin(object.getRectangle(), world));
        }
    }

}
