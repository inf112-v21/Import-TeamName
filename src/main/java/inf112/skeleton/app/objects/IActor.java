package inf112.skeleton.app.objects;

import com.badlogic.gdx.math.Vector2;
import inf112.skeleton.app.enums.Direction;

public interface IActor extends IObject{

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
