package inf112.skeleton.app.cards;

import com.badlogic.gdx.maps.tiled.TiledMap;
import com.badlogic.gdx.maps.tiled.TmxMapLoader;
import inf112.skeleton.app.GdxTestRunner;
import inf112.skeleton.app.RoboRally;
import inf112.skeleton.app.assetManager.Assets;
import inf112.skeleton.app.game.MainGame;
import inf112.skeleton.app.objects.Actors.Robot;
import inf112.skeleton.app.screens.cardsUI.CardUI;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;

import static org.junit.Assert.assertFalse;
@RunWith(GdxTestRunner.class)
public class CardUITest {

    private TiledMap map = new TmxMapLoader().load("Maps/MapForJunitTests.tmx");
    MainGame mainGame;

    RoboRally switcher;
    @Before
    public void initialize() {
        switcher = new RoboRally();
        Assets.load();
        Assets.manager.finishLoading();
        mainGame = new MainGame();
        mainGame.setup(map);
        mainGame.setNumPlayers(1);
    }


    @Test
    public void cantSend1Card() {
        CardUI ui = new CardUI(mainGame);
        ui.selectedCards.add(new MovementCard(1, CardType.MOVE1 ));
        Robot robot = mainGame.robots.get(0);
        ui.sendCards(robot);
        int numCards =  robot.getProgramSheet().getRegister().getRegisterCards().size();
        assertFalse(numCards > 0);
    }

    /**
    @Test
    public void canSend5Cards() {
        CardUI ui = new CardUI(mainGame);
        for (int i = 0; i < 5; i++) { ui.selectedCards.add(new MovementCard(1, CardType.MOVE1 ));}
        Player robot = mainGame.robots.get(0);
        ui.sendCards(robot);
        int numCards =  robot.getProgramSheet().getRegister().getRegisterCards().size();
        assertTrue(numCards == 5);
    }
    **/
}
