package inf112.skeleton.app.objects;

import inf112.skeleton.app.enums.Direction;

public interface IWall extends IObject {

    /**
     * Checks if an actor can move away from its own tile in a given direction.
     * @return true if can move.
     */
    boolean isPassableFromDirection(Direction dir);

}
