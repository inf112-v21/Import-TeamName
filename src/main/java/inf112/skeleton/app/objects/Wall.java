package inf112.skeleton.app.objects;

import com.badlogic.gdx.math.Vector2;
import inf112.skeleton.app.enums.Direction;

public class Wall implements IWall{

    private Vector2 position;
    private final Direction direction1; // Direction of wall in a tile? If direction=NORTH, then cannot go north when standing on tile?
    private final Direction direction2; // Direction for corner walls.

    /**
     *  Creates wall object
     * @param position
     * @param dir1 Where the wall is in a tile.
     * @param dir2 Where the wall is in a tile, only relevant for corner walls.
     */
    public Wall(Vector2 position, Direction dir1, Direction dir2) {
        this.position = position;
        this.direction1 = dir1;
        this.direction2 = dir2;
    }

    @Override
    public Vector2 getPosition() {
        return this.position;
    }

    @Override
    public void setPosition(Vector2 position) {
        this.position = position;
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
