package inf112.skeleton.app.map;

import com.badlogic.gdx.maps.tiled.TiledMap;
import com.badlogic.gdx.maps.tiled.TmxMapLoader;
import inf112.skeleton.app.objects.TileObjects.DockingBay;
import org.junit.Assert;
import org.junit.Test;

public class BoardTest {

    private Board board;
    
    public BoardTest() {
        TiledMap map = new TmxMapLoader().load("Maps/Chess.tmx");  // Get map file
        board = new Board(map);
    }

    
    //Test wall collition


    @Test
    public void ShouldReturnCorrectNrOfDockingBays() {
        Assert.assertEquals(8, board.getNrDockingBays()); //Should be 8 docking bays in Chess.tmx
    }

    @Test
    public void ShouldReturnCorrectNrOfFlags() {
        Assert.assertEquals(2, board.getNrFlags()); //Should be 2 flags in Chess.tmx
    }

    @Test
    public void ShouldReturnCorrectNrOfWallTilesOnBoard() {
        Assert.assertEquals(47, board.getCollidables().size()); //Should be 47 wall tiles in Chess.tmx
    }

    @Test
    public void ShouldReturnCorrectNrOfNonWallTilesOnBoard() {
        Assert.assertEquals(79, board.getOtherTiles().size()); //Should be 77 non wall tiles in Chess.tmx
    }

    
}
