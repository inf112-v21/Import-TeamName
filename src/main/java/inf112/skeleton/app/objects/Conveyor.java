package inf112.skeleton.app.objects;

import com.badlogic.gdx.math.Vector2;
import inf112.skeleton.app.enums.Direction;

public class Conveyor implements Object {

    private Vector2 position;
    private Direction pushDirection;

    @Override
    public Vector2 getPosition() {
        return this.position;
    }

    @Override
    public void setPosition(Vector2 pos) {
        this.position = pos;
    }

    public Direction getPushDirection() {
        return this.pushDirection;
    }

}
