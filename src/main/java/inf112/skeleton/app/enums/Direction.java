package inf112.skeleton.app.enums;

/**
 *
 */
public enum Direction {

    NORTH,
    EAST,
    SOUTH,
    WEST;

    /**
     * Returns opposite direction
     * @param dir
     * @return
     */
    public static Direction DirectionOpposite(Direction dir) {
        if (!(dir instanceof Direction)) throw new IllegalArgumentException("Expected enum direction.");

        switch (dir) {
            case NORTH: return Direction.SOUTH;
            case SOUTH: return Direction.NORTH;
            case EAST: return Direction.WEST;
            case WEST: return Direction.EAST;
        }
        return null;
    }


}
