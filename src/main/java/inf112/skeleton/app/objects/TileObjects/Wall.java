package inf112.skeleton.app.objects.TileObjects;

import com.badlogic.gdx.math.Vector2;
import inf112.skeleton.app.enums.Direction;
import inf112.skeleton.app.objects.IWall;
import inf112.skeleton.app.objects.SimpleObject;

public class Wall extends SimpleObject implements IWall {

    private final Direction direction1; // Direction of wall in a tile? If direction=NORTH, then cannot go north when standing on tile?
    private final Direction direction2; // Direction for corner walls.

    /**
     *  Creates wall object
     * @param position Position of wall
     * @param dir1 Where the wall is in a tile.
     * @param dir2 Where the wall is in a tile, only relevant for corner walls.
     */
    public Wall(Vector2 position, Direction dir1, Direction dir2) {
        super(position);
        this.direction1 = dir1;
        this.direction2 = dir2;
    }

    @Override
    public boolean isPassableFromDirection(Direction dir) {
        return dir != this.direction1 && dir != this.direction2;
    }

    @Override
    public boolean isPassableToDirection(Direction dir) {
        return false;
    }
}
