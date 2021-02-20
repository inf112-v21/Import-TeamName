package inf112.skeleton.app.objects;

import inf112.skeleton.app.enums.Direction;

public interface IWall extends IObject {

    /**
     * Checks if an actor can move away from its own tile in a given direction.
     * @return true if solid, cannot pass through it.
     */
    boolean isPassableFromDirection(Direction dir);


    /**
     * Checks if actor can move onto another tile from a given direction.
     * Note: This function may not be necessary.
     * @param dir
     * @return
     */
    boolean isPassableToDirection(Direction dir);

}
