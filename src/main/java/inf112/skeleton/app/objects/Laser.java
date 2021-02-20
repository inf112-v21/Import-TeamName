package inf112.skeleton.app.objects;

import com.badlogic.gdx.math.Vector2;
import inf112.skeleton.app.enums.Direction;

public class Laser implements IWall {

    private Vector2 position;
    //Direction of laser is where the laser ends. Laser(SOUTH), has a wall to its NORTH.
    private final Direction direction; // Made final, will direction of laser/wall ever change?

    public Laser(Vector2 position, Direction direction) {
        this.position = position;
        this.direction = direction;
    }

    @Override
    public Vector2 getPosition() {
        return this.position;
    }

    @Override
    public void setPosition(Vector2 pos) {
        this.position = pos;
    }

    public Direction getDirection() {
        return this.direction;
    }

    @Override
    public boolean isPassableFromDirection(Direction dir) {
        return dir != Direction.DirectionOpposite(dir); // Can go if direction is different than the opposite of the facing direction of laser.
    }

}
