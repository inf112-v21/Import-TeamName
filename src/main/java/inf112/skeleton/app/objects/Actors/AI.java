package inf112.skeleton.app.objects.Actors;

import com.badlogic.gdx.math.Vector2;
import inf112.skeleton.app.enums.Direction;
import inf112.skeleton.app.objects.IActor;

public class AI implements IActor {

    private Vector2 position;
    private Direction lookDirection;

    @Override
    public Vector2 getPosition() {
        return this.position;
    }

    @Override
    public void setPosition(Vector2 position) {

    }

    @Override
    public Direction getLookDirection() {
        return this.lookDirection;
    }

    @Override
    public void setLookDirection(Direction direction) {

    }
}
