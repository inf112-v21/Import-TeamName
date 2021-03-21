package inf112.skeleton.app.objects.actors;

import com.badlogic.gdx.maps.tiled.TiledMap;
import com.badlogic.gdx.maps.tiled.TmxMapLoader;
import com.badlogic.gdx.math.Vector2;
import inf112.skeleton.app.GdxTestRunner;
import inf112.skeleton.app.assetManager.Assets;
import inf112.skeleton.app.game.MainGame;
import inf112.skeleton.app.objects.Actors.ProgramSheet;
import inf112.skeleton.app.objects.TileObjects.Flag;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;

@RunWith(GdxTestRunner.class)
public class ProgramSheetTest {

    //Get map
    private TiledMap map = new TmxMapLoader().load("Maps/MapForJunitTests.tmx");
    private MainGame game;

    @Before
    public void initialise() {
        Assets.load();
        Assets.manager.finishLoading();

        game = new MainGame();
        game.setup(map); //Make game board
    }

    //TODO: Write tests for most methods.


    @Test
    public void maxLifetokensShouldBe3() {
        ProgramSheet pro = new ProgramSheet();
        pro.addLife(100);

        Assert.assertEquals(3,pro.getLife());
    }

    @Test
    public void minLifetokensShouldBe0() {
        ProgramSheet pro = new ProgramSheet();
        pro.addLife(-100);

        Assert.assertEquals(0,pro.getLife());
    }

    @Test
    public void addFlagShouldOnlyWorkInIncreasingOrder() {
        ProgramSheet pro = new ProgramSheet();
        Flag flag1 = new Flag(new Vector2(0,0),  1);
        Flag flag2 = new Flag(new Vector2(1,1),  2);

        //Wrong order, only flag1 should be added
        pro.addFlag(flag2);
        pro.addFlag(flag1);
        Assert.assertEquals(1,pro.getNumberOfFlags());

        //Correct order
        pro.addFlag(flag1);
        pro.addFlag(flag2);
        Assert.assertEquals(2,pro.getNumberOfFlags());
    }

}
