package inf112.skeleton.app.objects;

import inf112.skeleton.app.enums.Direction;

public interface IWall extends IObject {

    /**
     * Checks if an actor can move away from its own tile in a given direction.
     * @return true if can move.
     */
    boolean isPassableFromDirection(Direction dir);


    /**
     * Checks if actor can move onto another tile from a given direction.
     * Note: This function may not be necessary, as we can call the above function on any tile we want to move to.
     * @param dir
     * @return True if can move.
     */
    boolean isPassableToDirection(Direction dir);

}
