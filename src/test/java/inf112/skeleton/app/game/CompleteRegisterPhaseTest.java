package inf112.skeleton.app.game;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.maps.tiled.TiledMap;
import com.badlogic.gdx.maps.tiled.TmxMapLoader;
import com.badlogic.gdx.math.Vector2;
import inf112.skeleton.app.GdxTestRunner;
import inf112.skeleton.app.assetManager.Assets;
import inf112.skeleton.app.objects.Actors.Player;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;

@RunWith(GdxTestRunner.class)
public class CompleteRegisterPhaseTest {
    //Get map
    private TiledMap map = new TmxMapLoader().load("Maps/MapForJunitTests.tmx");
    //Splits player texture into the 3 parts. Live/Dead/Win
    private TextureRegion[][] textures = new TextureRegion(new Texture("Images/player.png")).split(300, 300);
    private MainGame game;

    @Before
    public void initalise() {

        Assets.load();
        Assets.manager.finishLoading();

        game = new MainGame();
        game.setup(map); //Make game board

    }

    @Test
    public void normalConveyorShouldPushRobotOneTileEast() {
        Player robot = new Player(new Vector2(2,1), textures);
        game.addPlayer(robot);

        CompleteRegisterPhase phase = new CompleteRegisterPhase();
        phase.moveConveyor(false);

        Assert.assertEquals(new Vector2(3,1),robot.getPosition());
    }


    @Test
    //Begin at (3,1) --> (3,0)
    public void expressConveyorShouldPushRobotOneTileSouth() {
        Player robot = new Player(new Vector2(3,1), textures);
        game.addPlayer(robot);

        CompleteRegisterPhase phase = new CompleteRegisterPhase();
        phase.moveConveyor(true);

        Assert.assertEquals(new Vector2(3,0),robot.getPosition());
    }



}
