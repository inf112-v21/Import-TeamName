package inf112.skeleton.app.cards;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.maps.tiled.TiledMap;
import com.badlogic.gdx.maps.tiled.TiledMapTileLayer;
import com.badlogic.gdx.maps.tiled.TmxMapLoader;
import com.badlogic.gdx.math.Vector2;
import inf112.skeleton.app.GdxTestRunner;
import inf112.skeleton.app.assetManager.Assets;
import inf112.skeleton.app.enums.Direction;
import inf112.skeleton.app.game.MainGame;
import inf112.skeleton.app.objects.Actors.Player;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;

import java.util.ArrayList;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

/**
 * This test class contains mostly the same tests as robot, but instead calls directly on the cards action() method.
 */

@RunWith(GdxTestRunner.class)
public class CardsTest {

    // Make maps
    private TiledMap map;

    private TextureRegion[][] textures;
    private TiledMapTileLayer tilePlayer;
    private MainGame mainGame;

    @Before
    public void initalise() {
        textures = new TextureRegion(new Texture("Images/robot.png")).split(300, 300);  // Splits player texture into the 3 parts. Live/Dead/Win
        map = new TmxMapLoader().load("Maps/Chess.tmx");       // Get map file
        tilePlayer = (TiledMapTileLayer) map.getLayers().get("Player");
        Assets.load();
        Assets.manager.finishLoading();
        mainGame = new MainGame();
        mainGame.setup(map);
        mainGame.setNumPlayers(1);
    }

    @Test
    public void moveOneForward() {
        Player player = new Player(new Vector2(2,2), textures);
        MovementCard move1 = new MovementCard(1, CardType.MOVE1 );
        move1.action(player);
        assertEquals(new Vector2(2,3), player.getPosition());
    }

    @Test
    public void moveOneBack() {
        Player player = new Player(new Vector2(2,2), textures);
        MovementCard move1 = new MovementCard(1,  CardType.BACK1);
        move1.action(player);
        assertEquals(new Vector2(2,1), player.getPosition());
    }

    @Test
    public void rotateWithClock() {
        Player player = new Player(new Vector2(2,2), textures);
        RotationCard rotate1 = new RotationCard(1, CardType.ROTATERIGHT);
        rotate1.action(player);
        assertEquals(Direction.EAST, player.getLookDirection());
    }

    //TODO: Write tests that account for walls.


    @Test
    public void cardDeckSubtractNumCards() {
        CardHand deck = new CardHand(9);
        deck.subtractNumCardsDeck();
        assertEquals(8, deck.getNumCardsDeck());

    }

    @Test
    public void generateCardDeck() {
        CardDeck deck = new CardDeck();
        assertEquals(18, deck.getAvailableCards(CardType.MOVE1));
    }


    @Test
    public void testLockedRegister() {
        /**
         * Checks that register has sucessfully been locked after taking 6 damage
         * **/
        Player player =  mainGame.getRobots().get(0);
        player.getProgramSheet().addDamage(6); // should be locked
        assertTrue(player.getProgramSheet().getLockedRegister());

    }

}

