package inf112.skeleton.app.map;

import com.badlogic.gdx.maps.*;
import com.badlogic.gdx.maps.tiled.TiledMap;
import com.badlogic.gdx.maps.tiled.TiledMapTileLayer;
import inf112.skeleton.app.objects.IObject;
import inf112.skeleton.app.objects.IWall;
import inf112.skeleton.app.objects.TileObjects.Laser;
import inf112.skeleton.app.objects.TileObjects.Pusher;
import inf112.skeleton.app.objects.TileObjects.Wall;
import com.badlogic.gdx.math.Vector2;
import java.util.ArrayList;
import java.util.List;

/**
 *  Gathers information of given board.
 */
public class Board {

    private final List<IWall> collidables; // Walls, Lasers, Pushers
    private final List<IObject> otherTiles;  // Everything other than Floor, empty space and collidables.

    private int nrFlags; //Number of flags on board.
    private int nrDockingBays; //Number of starting points.

    public Board(TiledMap map) {
        collidables = new ArrayList<IWall>();
        otherTiles = new ArrayList<IObject>();

        tileTranslator(map);
    }

    /**
     *
     * @param TiledMap map
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
                    } else if (tileInstance instanceof Laser) {
                        collidables.add((IWall) tileInstance);
                    } else if (tileInstance instanceof Pusher) {
                        collidables.add((IWall) tileInstance);
                    } else otherTiles.add(tileInstance);
                }
            }
        }
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
        return nrFlags;
    }

    /**
     * Returns nr of starting points
     * @return
     */
    public int getNrDockingBays() {
        return nrDockingBays;
    }
}
