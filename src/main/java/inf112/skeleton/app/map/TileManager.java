package inf112.skeleton.app.map;

import com.badlogic.gdx.math.Vector2;
import inf112.skeleton.app.enums.Direction;
import inf112.skeleton.app.objects.Conveyor;
import inf112.skeleton.app.objects.DockingBay;
import inf112.skeleton.app.objects.IObject;
import inf112.skeleton.app.objects.Wall;

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

    /**
     * Creates object based on given Tile
     * @param tile
     * @param pos
     * @return
     */
    public IObject makeTile(Tiles tile, Vector2 pos) {
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


        }
        return null;
    }
}
