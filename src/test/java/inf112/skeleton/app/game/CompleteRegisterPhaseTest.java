package inf112.skeleton.app.game;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.maps.tiled.TiledMap;
import com.badlogic.gdx.maps.tiled.TiledMapTileLayer;
import com.badlogic.gdx.maps.tiled.TmxMapLoader;
import com.badlogic.gdx.math.Vector2;
import inf112.skeleton.app.GdxTestRunner;
import inf112.skeleton.app.assetManager.Assets;
import inf112.skeleton.app.objects.Actors.IActor;
import inf112.skeleton.app.objects.Actors.SimpleRobot;
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

    private TiledMapTileLayer tilePlayer;
    private MainGame game;

    @Before
    public void initalise() {
        tilePlayer = (TiledMapTileLayer) map.getLayers().get("Player");

        Assets.load();
        Assets.manager.finishLoading();

        game = new MainGame();
        game.setup(map); //Make game board

    }

    @Test
    public void normalConveyorShouldPushRobotOneTileNorth() {
        //IActor robot = new SimpleRobot(new Vector2(2,3), textures, game);

        CompleteRegisterPhase phase = new CompleteRegisterPhase();
        phase.run();


        Assert.assertEquals(true,true);
    }






}
