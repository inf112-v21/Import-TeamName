package inf112.skeleton.app.objects;

import com.badlogic.gdx.math.Vector2;
import inf112.skeleton.app.enums.Direction;

public class Wall implements IObject{

    private Vector2 position;
    private final Direction direction; // Direction of wall in a tile? If direction=NORTH, then cannot go north when standing on tile?

    public Wall(Vector2 position, Direction direction) {
        this.position = position;
        this.direction = direction;
    }

    @Override
    public Vector2 getPosition() {
        return this.position;
    }

    @Override
    public void setPosition(Vector2 position) {
        this.position = position;
    }
}
