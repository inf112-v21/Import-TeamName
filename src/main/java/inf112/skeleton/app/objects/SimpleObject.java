package inf112.skeleton.app.objects;

import com.badlogic.gdx.math.Vector2;

/**
 * Simple object class, represents base properties of all objects/actors in the game.
 */
public class SimpleObject implements IObject{

    private Vector2 position;

    public SimpleObject(Vector2 position) {
        this.position = position;
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
