package inf112.skeleton.app.map;

import com.badlogic.gdx.maps.tiled.TiledMap;

import java.util.List;

/**
 * Creates a 2D list, with tileObjects, representing each position of a given board.
 */
public class Board {

    /*
     *  Plan:
     *      - get name of map that will be played.
     *      - Loop through all tiles. -> Make object based on tile.
     *      - Add to list/map.
     */

    //Need two lists? One for lasers and walls. One for everything else.
    private List wallsAndLasers;
    private List otherTiles;

    public Board(TiledMap map) {

    }
}
