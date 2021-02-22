package inf112.skeleton.app.objects.TileObjects;

import com.badlogic.gdx.math.Vector2;
import inf112.skeleton.app.enums.Direction;
import inf112.skeleton.app.objects.SimpleObject;

public class Gear extends SimpleObject {

    private Direction turnDirection;

    public Gear(Vector2 position, Direction turnDirection) {
        super(position);
        this.turnDirection = turnDirection;
    }

    public Direction getTurnDirection() {
        return turnDirection;
    }
}
