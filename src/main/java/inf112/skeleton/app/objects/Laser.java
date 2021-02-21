package inf112.skeleton.app.objects;

import com.badlogic.gdx.math.Vector2;
import inf112.skeleton.app.enums.Direction;

public class Laser extends Object implements IWall {

    //Direction of laser is where the laser ends. Laser(SOUTH), has a wall to its NORTH.
    private final Direction direction; // Made final, will direction of laser/wall ever change?

    public Laser(Vector2 position, Direction direction) {
        super(position);
        this.direction = direction;
    }

    public Direction getDirection() {
        return this.direction;
    }

    @Override
    public boolean isPassableFromDirection(Direction dir) {
        return dir != Direction.DirectionOpposite(dir); // Can go if direction is different than the opposite of the facing direction of laser.
    }

    @Override
    public boolean isPassableToDirection(Direction dir) {
        return false;
    }

}
