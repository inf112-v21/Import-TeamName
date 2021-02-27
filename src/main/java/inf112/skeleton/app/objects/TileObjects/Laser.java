package inf112.skeleton.app.objects.TileObjects;

import com.badlogic.gdx.math.Vector2;
import inf112.skeleton.app.enums.Direction;
import inf112.skeleton.app.objects.IWall;
import inf112.skeleton.app.objects.SimpleObject;

public class Laser extends SimpleObject implements IWall {

    //Direction of laser is where the laser ends. Laser(SOUTH), has a wall to its NORTH.
    private final Direction direction; // Made final, will direction of laser/wall ever change?
    private final int strength; // Single or Double laser

    public Laser(Vector2 position, Direction direction, int strength) {
        super(position);
        this.direction = direction;
        this.strength = strength;
    }

    public Direction getDirection() {
        return this.direction;
    }

    public int getStrength() { return strength; }

    @Override
    public boolean isPassableFromDirection(Direction dir) {
        return dir != Direction.DirectionOpposite(direction); // Can go if direction is different than the opposite of the facing direction of laser.
    }
}
