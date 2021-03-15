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

    private final HashMap<Vector2,IWall> mapCollidables;   //(Pos on board, wall objects). Contains Walls, Lasers, Pushers
    private final HashMap<Vector2,IObject> mapOtherTiles;  //(Pos on board, tile objects). Contains all other tiles.

    private final ArrayList<DockingBay> dockingBays;
    private final ArrayList<Flag> flags;
    private final ArrayList<Laser> lasers;

    private final Vector2 boardDimensions; // (Width, Height)

    public Board(TiledMap map) {
        dockingBays = new ArrayList<DockingBay>();
        flags = new ArrayList<Flag>();
        lasers = new ArrayList<Laser>();

        mapCollidables = new HashMap<Vector2,IWall>();
        mapOtherTiles = new HashMap<Vector2,IObject>();

        //Dimensions of board
        int width = map.getProperties().get("width",Integer.class);
        int height = map.getProperties().get("height",Integer.class);
        boardDimensions = new Vector2(width,height);

        getBoardInformation(map);
    }

    /**
     * Gets all tiles from board, makes an instance of it and adds it to the correct list.
     *
     *  Loops through all layers and all tiles/positions on current layer.
     *  Then creates an instance of each tile and adds it either to mapCollidables or mapOtherTiles.
     * @param map
     */
    private void getBoardInformation(TiledMap map) {
        TileManager tileManager = new TileManager();

        for (MapLayer layer2 : map.getLayers()) { // Loop through all layers
            TiledMapTileLayer layer = (TiledMapTileLayer) map.getLayers().get(layer2.getName()); // Get TileMap of current layer.

            //Loops through all tiles in a layer
            for (int x = 0; x < layer.getWidth(); x++) {
                for (int y = 0; y < layer.getHeight(); y++) {
                    TiledMapTileLayer.Cell tile = layer.getCell(x, y); //Gets tile from layer

                    if (tile==null) continue;         // Ignore null. Where is this in the map?
                    int id = tile.getTile().getId();  // Gets id of current tile.
                    if (id==5) continue;              // Ignore Floor

                    //Convert id(int) to TileObject(Tile-enum)
                    IObject tileInstance = tileManager.getTileObject(id, new Vector2(x,y));

                    if (tileInstance instanceof Wall){
                        mapCollidables.put(new Vector2(x,y),(IWall) tileInstance);
                    } else if (tileInstance instanceof Laser) {
                        mapCollidables.put(new Vector2(x,y),(IWall) tileInstance);
                        lasers.add((Laser) tileInstance);
                    } else if (tileInstance instanceof Pusher) {
                        mapCollidables.put(new Vector2(x,y),(IWall) tileInstance);
                    } else {
                        if (tileInstance instanceof Flag) flags.add((Flag) tileInstance);
                        if (tileInstance instanceof DockingBay) dockingBays.add((DockingBay) tileInstance);

                        mapOtherTiles.put(new Vector2(x,y),tileInstance);
                    }
                }
            }
        }
    }

    /*
        Wall collision handling
     */

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


    /*
     *  Getters for tile on board
     */

    /**
     * Returns object of tile on given position on board.
     * Does not include Walls, Lasers and Pushers.
     * @param pos
     * @return
     */
    public IObject getNonWallTileOnPos(Vector2 pos) {
        return mapOtherTiles.get(pos);
    }

    /**
     * Returns IWall type object on given pos. May be either Laser, Wall, Pusher or null if empty.
     * @param pos
     * @return
     */
    public IWall getWallTileOnPos(Vector2 pos) {
        return mapCollidables.get(pos);
    }

    /**
     * Checks if a position on board is a Flag.
     * @param pos
     * @return
     */
    public boolean isPosAFlag(Vector2 pos) {
        return getNonWallTileOnPos(pos) instanceof Flag;
    }

    /**
     * Checks if a position on board is a Pit.
     * @param pos
     * @return
     */
    public boolean isPosAPit(Vector2 pos) {
        return getNonWallTileOnPos(pos) instanceof Pit;
    }

    /**
     * Checks if pos is a Conveyor.
     * @param pos
     * @return
     */
    public boolean isPosAConveyor(Vector2 pos) {return getNonWallTileOnPos(pos) instanceof Conveyor; }

    /**
     * Checks if pos os a Pusher
     * @param pos
     * @return
     */
    public boolean isPosAPusher(Vector2 pos) {
        return getWallTileOnPos(pos) instanceof Pusher;
    }

    /**
     * Checks if pos is a Gear
     * @param pos
     * @return
     */
    public boolean isPosAGear(Vector2 pos) {
        return getNonWallTileOnPos(pos) instanceof Gear;
    }

    /**
     * @return List of all collidable tiles on the board. Does not include players!
     */
    public List<IWall> getCollidables() {
        return new ArrayList<IWall>(mapCollidables.values()); //Copy of collidables.
    }

    /**
     * @return List of all tiles, except {Walls,Lasers,Pushers}.
     */
    public List<IObject> getOtherTiles() {
        return new ArrayList<IObject>(mapOtherTiles.values()); //Copy of all non wall tiles.
    }

    /**
     * @return List of all lasers on board.
     */
    public ArrayList<Laser> getLasers() {
        return new ArrayList<Laser>(lasers);
    }

    /**
     * Returns list of all dockingbays on the board.
     * @return List of dockingbays.
     */
    public ArrayList<DockingBay> getDockingBays() {
        return new ArrayList<DockingBay>(dockingBays);
    }

    /**
     * @return Returns list of all flags.
     */
    public ArrayList<Flag> getFlags() {
        return new ArrayList<Flag>(flags);
    }

    /**
     * @return Returns nr of flags in given map.
     */
    public int getNrFlags() {
        return flags.size();
    }

    /**
     * @return Returns nr of starting points.
     */
    public int getNrDockingBays() {
        return dockingBays.size();
    }

    /**
     * @return Vector2 with dimensions of board.
     */
    public Vector2 getBoardDimensions() {
        return boardDimensions;
    }

    /**
     * Checks if a position is out of bounds or not.
     * @param  posPlayer
     * @return If on board or not
     */
    public boolean isOnBoard(Vector2 posPlayer) {
        int playerX = (int) posPlayer.x;
        int playerY = (int) posPlayer.y;
        return (playerX < 0 || playerX > getBoardDimensions().x || playerY < 0 || playerY > getBoardDimensions().y);

    }
}
