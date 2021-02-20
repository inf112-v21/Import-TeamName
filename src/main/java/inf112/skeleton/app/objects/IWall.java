package inf112.skeleton.app.objects;

import inf112.skeleton.app.enums.Direction;

public interface IWall extends IObject {

    /**
     * Checks if object can be passed through from a given direction.
     * @return true if solid, cannot pass through it.
     */
    boolean isPassableFromDirection(Direction dir);

}
