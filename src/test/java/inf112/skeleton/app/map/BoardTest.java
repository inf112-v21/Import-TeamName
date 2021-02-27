package inf112.skeleton.app.map;

import com.badlogic.gdx.maps.tiled.TiledMap;
import com.badlogic.gdx.maps.tiled.TmxMapLoader;
import com.badlogic.gdx.math.Vector2;
import inf112.skeleton.app.enums.Direction;
import inf112.skeleton.app.objects.TileObjects.Laser;
import inf112.skeleton.app.objects.TileObjects.Pusher;
import org.junit.Assert;
import org.junit.Test;

public class BoardTest {

    private final Board boardChessMap;
    private final Board boardTestMap;
    
    public BoardTest() {
        TiledMap map = new TmxMapLoader().load("Maps/Chess.tmx");  // Get map file
        this.boardChessMap = new Board(map);

        TiledMap testMap = new TmxMapLoader().load("Maps/MapForJunitTests.tmx");  // Get map file
        this.boardTestMap = new Board(testMap);
    }


    /*
            Tests for JunitTestMap.tmx
     */

    @Test
    public void canGoToTileShouldNotGoThroughPusher() {
        Assert.assertFalse(boardTestMap.canGoToTile(new Vector2(2,3), Direction.EAST));
    }

    @Test
    public void canGoToTileShouldNotGoThroughLaser() {
        Assert.assertFalse(boardTestMap.canGoToTile(new Vector2(3,4), Direction.NORTH));
    }

    @Test
    public void canGoToTileShouldNotGoThroughCornerWall() {
        Assert.assertFalse(boardTestMap.canGoToTile(new Vector2(4,0), Direction.EAST));
        Assert.assertFalse(boardTestMap.canGoToTile(new Vector2(4,0), Direction.SOUTH));
    }

    @Test
    public void getWallTileOnPosShouldReturnPusher() {
        Assert.assertTrue(boardTestMap.getWallTileOnPos(new Vector2(0,4)) instanceof Pusher);
    }

    @Test
    public void getWallTileOnPosShouldReturnLaser() {
        Assert.assertTrue(boardTestMap.getWallTileOnPos(new Vector2(4,4)) instanceof Laser);
    }

    @Test
    public void getNonWallTileOnPosShouldReturnConveyor() {
        Assert.assertTrue(boardTestMap.isPosAConveyor(new Vector2(2,0)));
    }

    @Test
    public void getLasersShouldReturnAllLasers() {
        Assert.assertEquals(2, boardTestMap.getLasers().size());
    }

    @Test
    public void getDockingBaysShouldReturnAllDockingBays() {
        Assert.assertEquals(1, boardTestMap.getDockingBays().size());
    }


    /*
            Extra Tests for Chess.tmx map.
     */
    //Wall collision
    @Test
    public void canGoToTileShouldNotGoThroughWall() {
        //Wall at pos(1,1), should not be able to go there from pos(0,1).
        Assert.assertFalse(boardChessMap.canGoToTile(new Vector2(0,1), Direction.EAST));
    }

    @Test
    public void canGoToTileShouldBeTrue() {
        Assert.assertTrue(boardChessMap.canGoToTile(new Vector2(0,0), Direction.NORTH));
    }

    //Information of map
    @Test
    public void ShouldReturnCorrectNrOfDockingBays() {
        Assert.assertEquals(8, boardChessMap.getNrDockingBays()); //Should be 8 docking bays in Chess.tmx
    }

    @Test
    public void ShouldReturnCorrectNrOfFlags() {
        Assert.assertEquals(2, boardChessMap.getNrFlags()); //Should be 2 flags in Chess.tmx
    }

    @Test
    public void ShouldReturnCorrectNrOfWallTilesOnBoard() {
        Assert.assertEquals(47, boardChessMap.getCollidables().size()); //Should be 47 wall tiles in Chess.tmx
    }

    @Test
    public void ShouldReturnCorrectNrOfNonWallTilesOnBoard() {
        Assert.assertEquals(79, boardChessMap.getOtherTiles().size()); //Should be 77 non wall tiles in Chess.tmx
    }

    
}
