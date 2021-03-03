package inf112.skeleton.app.objects.Actors;

import inf112.skeleton.app.enums.Direction;
import inf112.skeleton.app.objects.IObject;

public interface IActor extends IObject {

    /**
     * @return Directions actor is looking.
     */
    Direction getLookDirection();

    /**
     * @return actor's unique program sheet.
     */
    ProgramSheet getProgramSheet();

    /**
     * Change direction actor is looking.
     * @param direction
     */
    void setLookDirection(Direction direction);

    /**
     *
     * @param clockwiseTurns: The amount of 45° clockwise turns the actor will make.
     */
    void rotate(int clockwiseTurns);
}
