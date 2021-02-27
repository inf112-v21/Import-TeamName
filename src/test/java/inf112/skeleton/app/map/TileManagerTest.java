package inf112.skeleton.app.map;

import com.badlogic.gdx.math.Vector2;
import inf112.skeleton.app.objects.TileObjects.Laser;
import inf112.skeleton.app.objects.TileObjects.Pit;
import inf112.skeleton.app.objects.TileObjects.Pusher;
import inf112.skeleton.app.objects.TileObjects.Wall;
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
}
