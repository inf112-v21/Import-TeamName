package inf112.skeleton.app.objects.TileObjects;

import com.badlogic.gdx.math.Vector2;
import inf112.skeleton.app.enums.Direction;
import inf112.skeleton.app.objects.IWall;
import inf112.skeleton.app.objects.SimpleObject;

import java.util.ArrayList;

public class Wall extends SimpleObject implements IWall {

    private final ArrayList<Direction> directions = new ArrayList(); // Direction of wall in a tile? If direction=NORTH, then cannot go north when standing on tile?

    /**
     *  Creates wall object
     * @param position Position of wall
     * @param dir1 Where the wall is in a tile.
     * @param dir2 Where the wall is in a tile, only relevant for corner walls.
     */
    public Wall(Vector2 position, Direction dir1, Direction dir2) {
        super(position);
        this.directions.add(dir1);
        this.directions.add(dir2);
    }

    @Override
    public boolean isPassableFromDirection(Direction dir) {

        for (Direction direction : directions) {
            if (dir == direction) return false;
        }
        return true;

    }

    @Override
    public boolean isPassableToDirection(Direction dir) {
        return false;
    }
}
