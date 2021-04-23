package inf112.skeleton.app.cards;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.maps.tiled.TiledMap;
import com.badlogic.gdx.maps.tiled.TmxMapLoader;
import com.badlogic.gdx.math.Vector2;
import inf112.skeleton.app.GdxTestRunner;
import inf112.skeleton.app.assetManager.Assets;
import inf112.skeleton.app.enums.Direction;
import inf112.skeleton.app.game.MainGame;
import inf112.skeleton.app.objects.Actors.Robot;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;

import static org.junit.Assert.assertEquals;

/**
 * This test class contains mostly the same tests as robot, but instead calls directly on the cards action() method.
 */

@RunWith(GdxTestRunner.class)
public class CardsTest {

    private TextureRegion[][] textures;

    @Before
    public void initalise() {
        textures = new TextureRegion(new Texture("Images/Robot/robot.png")).split(300, 300);  // Splits player texture into the 3 parts. Live/Dead/Win
        // Make maps
        TiledMap map = new TmxMapLoader().load("Maps/Chess.tmx");       // Get map file
        Assets.load();
        Assets.manager.finishLoading();
        MainGame mainGame = new MainGame();
        mainGame.setup(map);
        mainGame.setNumPlayers(1);
    }

    @Test
    public void moveOneForward() {
        Robot robot = new Robot(new Vector2(2,2), textures, "1");
        MovementCard move1 = new MovementCard(1, CardType.MOVE1 );
        move1.action(robot);
        assertEquals(new Vector2(2,3), robot.getPosition());
    }

    @Test
    public void moveOneBack() {
        Robot robot = new Robot(new Vector2(2,2), textures, "1");
        MovementCard move1 = new MovementCard(1,  CardType.BACK1);
        move1.action(robot);
        assertEquals(new Vector2(2,1), robot.getPosition());
    }

    @Test
    public void rotateWithClock() {
        Robot robot = new Robot(new Vector2(2,2), textures, "1");
        RotationCard rotate1 = new RotationCard(1, CardType.ROTATERIGHT);
        rotate1.action(robot);
        assertEquals(Direction.EAST, robot.getLookDirection());
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




}

