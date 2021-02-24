package inf112.skeleton.app.map;

import com.badlogic.gdx.maps.*;
import com.badlogic.gdx.maps.tiled.TiledMap;
import com.badlogic.gdx.maps.tiled.TiledMapTileLayer;
import inf112.skeleton.app.enums.Direction;
import inf112.skeleton.app.objects.IObject;
import inf112.skeleton.app.objects.IWall;
import inf112.skeleton.app.objects.TileObjects.*;
import com.badlogic.gdx.math.Vector2;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/**
 *  Gathers information of given board.
 */
public class Board {

    private final List<IWall> collidables;   // Walls, Lasers, Pushers
    private final List<IObject> otherTiles;  // Everything other than Floor, empty space and collidables.

    private final HashMap<Vector2,IWall> mapCollidables;   //(Pos on board, wall objects). Contains Walls, Lasers, Pushers
    private final HashMap<Vector2,IObject> mapOtherTiles;  //(Pos on board, tile objects). Contains all other tiles.

    private ArrayList<DockingBay> dockingBays;
    private ArrayList<Flag> flags;

    public Board(TiledMap map) {
        collidables = new ArrayList<IWall>();
        otherTiles = new ArrayList<IObject>();

        dockingBays = new ArrayList<DockingBay>();
        flags = new ArrayList<Flag>();

        mapCollidables = new HashMap<Vector2,IWall>();
        mapOtherTiles = new HashMap<Vector2,IObject>();

        tileTranslator(map);
    }

    /**
     *
     * @param
     */
    private void tileTranslator(TiledMap map) {
        TileManager tileManager = new TileManager();

        for (MapLayer layer2 : map.getLayers()) { // Loop through all layers
            TiledMapTileLayer layer = (TiledMapTileLayer) map.getLayers().get(layer2.getName()); // Get TileMap of current layer.

            //Loops through all tiles in a layer
            for (int x = 0; x < layer.getWidth(); x++) {
                for (int y = 0; y < layer.getHeight(); y++) {
                    TiledMapTileLayer.Cell tile = layer.getCell(x, y); //Gets tile from layer

                    if (tile==null) continue;         // Ignore null. Where is this in the map?
                    int id = tile.getTile().getId();  // Gets id of current tile.
                    if (id==123 || id==124) continue; // Ignore empty space in tileset.
                    if (id==5) continue;              // Ignore Floor

                    //Convert id to TileObject
                    IObject tileInstance = tileManager.getTileObject(id, new Vector2(x,y));

                    if (tileInstance instanceof Wall){
                        collidables.add((IWall) tileInstance);
                        mapCollidables.put(new Vector2(x,y),(IWall) tileInstance);
                    } else if (tileInstance instanceof Laser) {
                        collidables.add((IWall) tileInstance);
                        mapCollidables.put(new Vector2(x,y),(IWall) tileInstance);
                    } else if (tileInstance instanceof Pusher) {
                        collidables.add((IWall) tileInstance);
                        mapCollidables.put(new Vector2(x,y),(IWall) tileInstance);
                    } else {
                        if (tileInstance instanceof Flag) flags.add((Flag) tileInstance);
                        if (tileInstance instanceof DockingBay) dockingBays.add((DockingBay) tileInstance);

                        otherTiles.add(tileInstance);
                        mapOtherTiles.put(new Vector2(x,y),tileInstance);
                    }
                }
            }
        }
    }

    /**
     * Checks if actor can go to tile from given direction.
     * @param pos
     * @param dir
     * @return
     */
    public boolean canGoToTile(Vector2 pos, Direction dir) {
        return canLeaveTile(pos,dir) && canGoOntoTile(pos, dir);
    }

    /**
     * Checks that an actor can move away from a tile in a given direction.
     * @param pos Tile actor is standing on.
     * @param dir Direction actor wants to move.
     * @return True if can move in given direction.
     */
    private boolean canLeaveTile(Vector2 pos, Direction dir) {
        IWall wall = mapCollidables.get(pos); //Tile actor is standing on.
        if (wall == null) return true;        //Nothing in given direction.
        return wall.isPassableFromDirection(dir);
    }

    /**
     * Checks that an actor can move onto a tile from given direction.
     * @param pos Tile actor is standing on.
     * @param dir Direction actor wants to move.
     * @return True if can move onto a tile from given direction.
     */
    private boolean canGoOntoTile(Vector2 pos, Direction dir) {
        Vector2 adjacentPos = Direction.goDirection(pos, dir); // Adjacent tile in given direction.

        IWall wall = mapCollidables.get(adjacentPos);
        if (wall == null) return true; //Nothing in given direction.
        return canLeaveTile(adjacentPos,Direction.DirectionOpposite(dir)); //Entering a tile is equivalent to leaving it in the opposite direction.
    }



    /**
     * List of all collidable tiles on the board. Does not include players!
     * @return
     */
    public List<IWall> getCollidables() {
        return new ArrayList<IWall>(collidables); //Copy of collidables.
    }

    /**
     * List of all tiles, except {Walls,Lasers,Pushers}.
     * @return
     */
    public List<IObject> getOtherTiles() {
        return new ArrayList<IObject>(otherTiles); //Copy of other tiles.
    }

    /**
     * Returns nr of flags in given map.
     * @return
     */
    public int getNrFlags() {
        return flags.size();
    }

    /**
     * Returns nr of starting points
     * @return
     */
    public int getNrDockingBays() {
        return dockingBays.size();
    }
}
