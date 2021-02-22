package inf112.skeleton.app.objects.TileObjects;

import com.badlogic.gdx.math.Vector2;
import inf112.skeleton.app.enums.Direction;
import inf112.skeleton.app.objects.IWall;
import inf112.skeleton.app.objects.Object;

public class Pusher extends Object implements IWall{

    private final Direction pushDirection;

    public Pusher(Vector2 position, Direction pushDirection) {
        super(position);
        this.pushDirection = pushDirection;
    }

    @Override
    public boolean isPassableFromDirection(Direction dir) {
        return dir != Direction.DirectionOpposite(dir); //Wall is opposite to pushDirection.
    }

    @Override
    public boolean isPassableToDirection(Direction dir) {
        return false;
    }
}
