package inf112.skeleton.app.objects;

import com.badlogic.gdx.math.Vector2;
import inf112.skeleton.app.enums.Direction;

public interface IActor {

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

    /**
     * @return Directions actor is looking.
     */
    Direction getLookDirection();

    /**
     * Change direction actor is looking.
     * @param direction
     */
    void setLookDirection(Direction direction);

}
