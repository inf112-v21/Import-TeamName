package inf112.skeleton.app;

import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.graphics.Texture;

import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.maps.tiled.TiledMap;
import com.badlogic.gdx.maps.tiled.TiledMapTileLayer;
import com.badlogic.gdx.maps.tiled.TmxMapLoader;

import inf112.skeleton.app.assetManager.Assets;
import inf112.skeleton.app.cards.CardType;
import inf112.skeleton.app.cards.MovementCard;
import inf112.skeleton.app.enums.Direction;
import inf112.skeleton.app.game.MainGame;
import inf112.skeleton.app.objects.Actors.Robot;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;

import static org.junit.Assert.assertEquals;

@RunWith(GdxTestRunner.class)
public class RobotTest {

    // Make maps
    private TiledMap map;

    private TextureRegion[][] textures;
    private TiledMapTileLayer tilePlayer;
    private static MainGame game;


    @Before
    public void initialise() {
        textures = new TextureRegion(new Texture("Images/robot.png")).split(300, 300);  // Splits player texture into the 3 parts. Live/Dead/Win
        map = new TmxMapLoader().load("Maps/Chess.tmx");       // Get map file
        tilePlayer = (TiledMapTileLayer) map.getLayers().get("Player");
        Assets.load();
        Assets.manager.finishLoading();
        game = new MainGame();
        game.setup(map);
    }


    @Test
    public void playerMoveUp() {
        Robot robot = new Robot(new Vector2(2,2), textures, "1");
        robot.moveRobotWASD(51); // Keycode 51 -> w Should move up
        assertEquals(new Vector2(2,3), robot.getPosition());
    }

    @Test
    public void rotateWithClock() {
        Robot robot = new Robot(new Vector2(4,4), textures, "1");
        robot.rotate(1);
        assertEquals(Direction.EAST, robot.getLookDirection());
    }

    @Test
    public void rotateAgainstClock() {
        Robot robot = new Robot(new Vector2(4,4), textures, "1");
        robot.setLookDirection(Direction.WEST);
        robot.rotate(3);
        assertEquals(Direction.SOUTH, robot.getLookDirection());
    }

    @Test
    public void makeUTurn() {
        Robot robot = new Robot(new Vector2(4,4), textures, "1");
        robot.setLookDirection(Direction.EAST);
        robot.rotate(2);
        assertEquals(Direction.WEST, robot.getLookDirection());
    }

    @Test
    public void moveOneForward() {
        Robot robot = new Robot(new Vector2(1,1), textures, "1");
        robot.moveRobot(1); //To north
        assertEquals(new Vector2(1,2), robot.getPosition());
    }

    @Test
    public void moveOneBack() {
        Robot robot = new Robot(new Vector2(2,2), textures, "1");
        Texture playerTexture = Assets.manager.get(Assets.texture); // TEST TEXTURE. NO IMPLICATION FOR TEST
        MovementCard backwardsCard = new MovementCard(1, CardType.BACK1);
        backwardsCard.action(robot);
        assertEquals(new Vector2(2, 1), robot.getPosition());
    }



}
