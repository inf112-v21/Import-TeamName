package inf112.skeleton.app.map;

import com.badlogic.gdx.math.Vector2;
import inf112.skeleton.app.enums.Direction;
import inf112.skeleton.app.objects.TileObjects.Conveyor;
import inf112.skeleton.app.objects.TileObjects.DockingBay;
import inf112.skeleton.app.objects.IObject;
import inf112.skeleton.app.objects.TileObjects.Wall;

import java.util.HashMap;
import java.util.Map;

/**
 *    Creates an instance of all tiles on the board.
 */
public class TileManager {

    /*
         Plan:
             - Loop through all tiles on board.
             - Create class instance of tile, with correct direction and other data,
             - Return a list of all tiles? 1D og 2D list? Represent board?

     */
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
        return makeTile(tileEnum.get(tileIDFromMap), pos);
    }

    /**
     * Creates object based on given Tile enum
     * @param tile enum
     * @param pos
     * @return IObject corresponding to its tileID.
     */
    private IObject makeTile(Tiles tile, Vector2 pos) {
        switch (tile) {

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



            default: throw new IllegalArgumentException("Given tile does not exist.");
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
