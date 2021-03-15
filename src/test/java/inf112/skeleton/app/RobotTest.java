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
import inf112.skeleton.app.map.Board;
import inf112.skeleton.app.objects.Actors.Player;
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
    private Board board;


    @Before
    public void initialise() {
        textures = new TextureRegion(new Texture("Images/player.png")).split(300, 300);  // Splits player texture into the 3 parts. Live/Dead/Win
        map = new TmxMapLoader().load("Maps/Chess.tmx");       // Get map file
        tilePlayer = (TiledMapTileLayer) map.getLayers().get("Player");
        Assets.load();
        Assets.manager.finishLoading();
        this.board = new Board(map);
    }


    @Test
    public void playerMoveUp() {
        Player player = new Player(new Vector2(2,2), textures, board);
        player.moveRobotWASD(tilePlayer, 51); // Keycode 51 -> w Should move up
        assertEquals(new Vector2(2,3), player.getPosition());
    }

    @Test
    public void rotateWithClock() {
        Player player = new Player(new Vector2(4,4), textures, board);
        player.rotate(1);
        assertEquals(Direction.EAST, player.getLookDirection());
    }

    @Test
    public void rotateAgainstClock() {
        Player player = new Player(new Vector2(4,4), textures, board);
        player.setLookDirection(Direction.WEST);
        player.rotate(3);
        assertEquals(Direction.SOUTH, player.getLookDirection());
    }

    @Test
    public void makeUTurn() {
        Player player = new Player(new Vector2(4,4), textures, board);
        player.setLookDirection(Direction.EAST);
        player.rotate(2);
        assertEquals(Direction.WEST, player.getLookDirection());
    }

    @Test
    public void moveOneForward() {
        Player player = new Player(new Vector2(2,2), textures, board);
        player.moveRobot(tilePlayer, 1); //To north
        assertEquals(new Vector2(2,3), player.getPosition());
    }

    @Test
    public void moveOneBack() {
        Player player = new Player(new Vector2(2,2), textures, board);
        Texture playerTexture = Assets.manager.get(Assets.texture); // TEST TEXTURE. NO IMPLICATION FOR TEST
        MovementCard backwardsCard = new MovementCard(1, CardType.BACK1, -1);
        backwardsCard.action(tilePlayer, player);
        assertEquals(new Vector2(2, 1), player.getPosition());
    }



}
