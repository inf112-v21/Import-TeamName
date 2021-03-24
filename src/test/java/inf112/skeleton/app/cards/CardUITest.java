package inf112.skeleton.app.cards;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.maps.tiled.TiledMap;
import com.badlogic.gdx.maps.tiled.TmxMapLoader;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.utils.viewport.FitViewport;
import inf112.skeleton.app.GdxTestRunner;
import inf112.skeleton.app.RoboRally;
import inf112.skeleton.app.game.MainGame;
import inf112.skeleton.app.screens.GameScreen;
import inf112.skeleton.app.screens.cardsUI.CardUI;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
@RunWith(GdxTestRunner.class)
public class CardUITest {

    private TiledMap map = new TmxMapLoader().load("Maps/MapForJunitTests.tmx");
    MainGame mainGame;
    GameScreen gameScreen;
    Stage stage;

    @Before
    public void initialize() {
        mainGame = new MainGame();
        mainGame.setup(map);
        mainGame.setNumPlayers(5);
        gameScreen = new GameScreen(new RoboRally(), stage, new FitViewport(Gdx.graphics.getWidth(), Gdx.graphics.getHeight()), false);
    }


    @Test
    public void cantSend1Card() {
        CardUI ui = new CardUI(gameScreen, mainGame);
        ui.selectedCards.add(new MovementCard(1, CardType.MOVE1 ));
        ui.sendCards();
        int numCards =  ui.robot.getProgramSheet().getRegister().getRegisterCards().size();
        assertFalse(numCards > 0);
    }

    @Test
    public void canSend5Cards() {

    }
}
