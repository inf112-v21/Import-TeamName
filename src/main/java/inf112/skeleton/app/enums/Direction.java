package inf112.skeleton.app.enums;

import com.badlogic.gdx.math.Vector2;

/**
 *
 */
public enum Direction {

    NORTH,
    EAST,
    SOUTH,
    WEST;

    /**
     * Returns a new vector2 that is 1 tile away from given vector, in the given direction.
     * @param pos
     * @param dir
     * @return
     */
    public static Vector2 goDirection(Vector2 pos, Direction dir) {
        Vector2 newPos = pos.cpy(); // Copy of pos. Needed for encapsulation.

        switch (dir) {
            case NORTH: return newPos.add(0,1);
            case SOUTH: return newPos.add(0,-1);
            case WEST: return newPos.add(-1,0);
            case EAST: return newPos.add(1,0);

            default: throw new IllegalArgumentException("Expected enum cardinal direction. Received: " + dir);
        }
    }



    /**
     * Returns opposite direction
     * @param dir
     * @return
     */
    public static Direction DirectionOpposite(Direction dir) {
        switch (dir) {
            case NORTH: return Direction.SOUTH;
            case SOUTH: return Direction.NORTH;
            case EAST: return Direction.WEST;
            case WEST: return Direction.EAST;

            default: throw new IllegalArgumentException("Expected enum direction. Received: " + dir);
        }
    }

    public static Direction DirectionClockwise(Direction dir) {
        switch (dir) {
            case NORTH: return Direction.EAST;
            case EAST: return Direction.SOUTH;
            case SOUTH: return Direction.WEST;
            case WEST: return Direction.NORTH;

            default: throw new IllegalArgumentException("Expected enum direction. Received: " + dir);
        }
    }


}
