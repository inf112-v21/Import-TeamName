package inf112.skeleton.app.enums;

/**
 *
 */
public enum Direction {

    NORTH,
    EAST,
    SOUTH,
    WEST,

    WITH_CLOCK,
    AGAINST_CLOCK;

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
            default: throw new IllegalArgumentException("Expected enum cardinal direction.");
        }
    }


}
