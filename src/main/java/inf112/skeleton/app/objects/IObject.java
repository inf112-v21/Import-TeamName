package inf112.skeleton.app.objects;

import com.badlogic.gdx.math.Vector2;
import inf112.skeleton.app.enums.Direction;

public interface IObject {

    /**
     *
     * @return Position of object on board.
     */
    public Vector2 getPosition();

    /**
     *  Sets an objects position on the board.
     * @param position
     */
    public void setPosition(Vector2 position);

}
