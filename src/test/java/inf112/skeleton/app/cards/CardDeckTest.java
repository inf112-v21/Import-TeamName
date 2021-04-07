package inf112.skeleton.app.cards;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.maps.tiled.TiledMap;
import com.badlogic.gdx.maps.tiled.TmxMapLoader;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.utils.viewport.FitViewport;
import inf112.skeleton.app.GdxTestRunner;
import inf112.skeleton.app.RoboRally;
import inf112.skeleton.app.assetManager.Assets;
import inf112.skeleton.app.game.MainGame;
import inf112.skeleton.app.screens.GameScreen;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

@RunWith(GdxTestRunner.class)
public class CardDeckTest {

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
    public void totalNumCardsDeck() {
        CardDeck deck = new CardDeck();
        int sum = 0;
        for(CardType types : deck.availableCards.keySet()) {
            sum += deck.availableCards.get(types);
        }
        assertEquals(84, sum);
    }

    public void dealCards() {
        CardDeck deck = new CardDeck();
        deck.shuffleDeck();
        int sum = 0;

        for (int i = 0; i < 10; i++) {
            CardType type = deck.dealACard();
        }
        for(CardType types : deck.availableCards.keySet()) {
            sum += deck.availableCards.get(types);
        }
        assertEquals(74, sum);
    }

}
