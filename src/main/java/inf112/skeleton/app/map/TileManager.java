package inf112.skeleton.app.map;

import com.badlogic.gdx.math.Vector2;
import inf112.skeleton.app.enums.Direction;
import inf112.skeleton.app.enums.Rotation;
import inf112.skeleton.app.objects.TileObjects.*;
import inf112.skeleton.app.objects.IObject;

import java.util.HashMap;
import java.util.Map;

/**
 *    Creates an instance of all tiles on the board.
 */
public class TileManager {

    private static final Map<Integer, Tiles> tileEnum = new HashMap<Integer,Tiles>();

    public TileManager() {
        //Adds all enums to map. Used to get corresponding name of a given tileID (int).
        for (Tiles tile : Tiles.values()) {
            tileEnum.put(tile.getTileID(), tile);
        }
    }

    /**
     * Returns object corresponding to given tileID.
     * @param tileIDFromMap
     * @param pos
     * @return
     */
    public IObject getTileObject(int tileIDFromMap, Vector2 pos) {
        //System.out.println(tileIDFromMap);
        return makeTile(tileEnum.get(tileIDFromMap), pos);
    }

    /**
     * Creates object based on given Tile enum
     * @param tile enum
     * @param pos
     * @return IObject corresponding to its tileID.
     */
    private IObject makeTile(Tiles tile, Vector2 pos) {
        //System.out.println(tile);
        //if (tile==null) return new SimpleObject(new Vector2(-1,-1));

        switch (tile) {
            case PIT: return new Pit(pos);
            case REPAIR_SITE_SINGLE: return new RepairSite(pos,1);
            case REPAIR_SITE_DOUBLE: return new RepairSite(pos,2);
            case GEAR_ROTATE_LEFT: return new Gear(pos, Rotation.AGAINST_CLOCK);
            case GEAR_ROTATE_RIGHT: return new Gear(pos, Rotation.WITH_CLOCK);
            case FLAG1: return new Flag(pos);
            case FLAG2: return new Flag(pos);
            case FLAG3: return new Flag(pos);
            case FLAG4: return new Flag(pos);

            //Docking bays (Starting points)
            case DOCKING_BAY1:
            case DOCKING_BAY2:
            case DOCKING_BAY3:
            case DOCKING_BAY4:
            case DOCKING_BAY5:
            case DOCKING_BAY6:
            case DOCKING_BAY7:
            case DOCKING_BAY8:
                return new DockingBay(pos);

            //Pushers (Pos) (Push Direction)
            case PUSHER_NORTH_SOUTH_2: return new Pusher(pos,Direction.SOUTH);
            case PUSHER_EAST_WEST_2: return new Pusher(pos,Direction.WEST);
            case PUSHER_SOUTH_NORTH: return new Pusher(pos,Direction.NORTH);
            case PUSHER_WEST_EAST_2: return new Pusher(pos,Direction.EAST);
            case PUSHER_NORTH_SOUTH: return new Pusher(pos,Direction.SOUTH);
            case PUSHER_EAST_WEST: return new Pusher(pos,Direction.WEST);
            case PUSHER_SOUTH_NORTH_2: return new Pusher(pos,Direction.NORTH);
            case PUSHER_WEST_EAST: return new Pusher(pos,Direction.EAST);


            //Walls
            case WALL_RIGHT: return new Wall(pos, Direction.EAST,null);
            case WALL_DOWN:  return new Wall(pos, Direction.SOUTH,null);
            case WALL_LEFT:  return new Wall(pos, Direction.WEST,null);
            case WALL_TOP:   return new Wall(pos, Direction.NORTH,null);
            //Walls corner
            case WALL_LEFT_DOWN_CORNER:     return new Wall(pos, Direction.WEST, Direction.SOUTH);
            case WALL_BOTTOM_RIGHT_CORNER:  return new Wall(pos, Direction.SOUTH,Direction.EAST);
            case WALL_LEFT_TOP_CORNER:      return new Wall(pos, Direction.WEST,Direction.NORTH);
            case WALL_TOP_RIGHT_CORNER:     return new Wall(pos, Direction.NORTH,Direction.EAST);

            //Double conveyor
            case DOUBLE_CONVEYOR_DOWN: return new Conveyor(pos,Direction.SOUTH,2);
            case DOUBLE_CONVEYOR_LEFT: return new Conveyor(pos,Direction.WEST, 2);
            case DOUBLE_CONVEYOR_UP: return new Conveyor(pos, Direction.NORTH, 2);
            case DOUBLE_CONVEYOR_RIGHT: return new Conveyor(pos, Direction.EAST, 2);
            //Double conveyor corner
            case DOUBLE_CONVEYOR_DOWN_RIGHT: return new Conveyor(pos, Direction.EAST,2);
            case DOUBLE_CONVEYOR_RIGHT_UP: return new Conveyor(pos, Direction.NORTH, 2);
            case DOUBLE_CONVEYOR_LEFT_UP: return new Conveyor(pos, Direction.NORTH,2);
            case DOUBLE_CONVEYOR_DOWN_LEFT: return new Conveyor(pos,Direction.WEST, 2);
            case DOUBLE_CONVEYOR_LEFT_DOWN: return new Conveyor(pos, Direction.SOUTH, 2);
            case DOUBLE_CONVEYOR_UP_LEFT: return new Conveyor(pos, Direction.WEST,2);
            case DOUBLE_CONVEYOR_UP_RIGHT: return new Conveyor(pos, Direction.EAST, 2);
            case DOUBLE_CONVEYOR_RIGHT_DOWN: return new Conveyor(pos, Direction.SOUTH, 2);


            case NORMAL_CONVEYOR_LEFT_DOWN: return new Conveyor(pos,Direction.SOUTH,1);
            case NORMAL_CONVEYOR_UP_LEFT: return new Conveyor(pos,Direction.WEST,1);
            case NORMAL_CONVEYOR_UP_RIGHT: return new Conveyor(pos,Direction.EAST,1);
            case NORMAL_CONVEYOR_RIGHT_DOWN: return new Conveyor(pos,Direction.NORTH,1);
            case NORMAL_CONVEYOR_DOWN_RIGHT: return new Conveyor(pos,Direction.EAST,1);
            case NORMAL_CONVEYOR_RIGHT_UP: return new Conveyor(pos,Direction.NORTH,1);
            case NORMAL_CONVEYOR_LEFT_UP: return new Conveyor(pos,Direction.NORTH,1);
            case NORMAL_CONVEYOR_DOWN_LEFT: return new Conveyor(pos,Direction.WEST,1);
            case NORMAL_CONVEYOR_UP: return new Conveyor(pos,Direction.NORTH,1);
            case NORMAL_CONVEYOR_DOWN: return new Conveyor(pos,Direction.SOUTH,1);
            case NORMAL_CONVEYOR_LEFT: return new Conveyor(pos,Direction.WEST,1);
            case NORMAL_CONVEYOR_RIGHT: return new Conveyor(pos,Direction.EAST,1);

            //Laser (Position) (Shooting Direction)
            case LASER_DOWN_UP: return new Laser(pos,Direction.NORTH, 1);
            case LASER_LEFT_RIGHT: return new Laser(pos,Direction.EAST, 1);
            case LASER_TOP_DOWN: return new Laser(pos,Direction.SOUTH, 1);
            case LASER_RIGHT_LEFT: return new Laser(pos,Direction.WEST, 1);

            case LASER_DOUBLE_DOWN: return new Laser(pos,Direction.SOUTH, 2);
            case LASER_DOUBLE_LEFT: return new Laser(pos,Direction.WEST, 2);
            case LASER_DOUBLE_UP: return new Laser(pos,Direction.NORTH, 2);
            case LASER_DOUBLE_RIGHT: return new Laser(pos,Direction.EAST, 2);


            default: throw new IllegalArgumentException("Given tile does not exist: " + tile);
        }
    }

    /**
     * Prints all enum names and their id
     * Used for debugging.
     */
    public void printT() {
        for (Tiles tile : Tiles.values()) {
            System.out.println("Tile id: " + tile.getTileID() + "  Tile name: " + tile);
        }
    }

}
