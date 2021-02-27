package inf112.skeleton.app.map;

import com.badlogic.gdx.math.Vector2;
import inf112.skeleton.app.objects.TileObjects.*;
import org.junit.Assert;
import org.junit.Test;

public class TileManagerTest {

    private TileManager tileManager;
    private Vector2 pos;

    public TileManagerTest() {
        tileManager = new TileManager();
        pos = new Vector2(0,0);
    }


    @Test
    public void getTileShouldReturnWallObject() {
        Assert.assertTrue(tileManager.getTileObject(31, pos) instanceof Wall);
    }

    @Test
    public void getTileShouldReturnLaserObject() {
        Assert.assertTrue(tileManager.getTileObject(37, pos) instanceof Laser);
    }

    @Test
    public void getTileShouldReturnPusherObject() {
        Assert.assertTrue(tileManager.getTileObject(4, pos) instanceof Pusher);
    }

    @Test
    public void getTileShouldReturnPitObject() {
        Assert.assertTrue(tileManager.getTileObject(6, pos) instanceof Pit);
    }

    @Test
    public void getTileShouldReturnFlagObject() {
        Assert.assertTrue(tileManager.getTileObject(55, pos) instanceof Flag);
    }

    @Test
    public void getTileShouldReturnGearObject() {
        Assert.assertTrue(tileManager.getTileObject(53, pos) instanceof Gear);
    }

    @Test
    public void getTileShouldReturnConveyorObject() {
        Assert.assertTrue(tileManager.getTileObject(22, pos) instanceof Conveyor);
    }

    @Test
    public void getTileShouldReturnRepairSiteObject() {
        Assert.assertTrue(tileManager.getTileObject(15, pos) instanceof RepairSite);
    }
}
