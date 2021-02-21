package inf112.skeleton.app.objects;

import com.badlogic.gdx.math.Vector2;

public class Object implements IObject{

    private Vector2 position;

    public Object(Vector2 position) {
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
