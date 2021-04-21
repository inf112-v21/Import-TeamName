package inf112.skeleton.app.cards;

import com.badlogic.gdx.maps.tiled.TiledMap;
import com.badlogic.gdx.maps.tiled.TmxMapLoader;
import inf112.skeleton.app.GdxTestRunner;
import inf112.skeleton.app.RoboRally;
import inf112.skeleton.app.assetManager.Assets;
import inf112.skeleton.app.game.DealCardsPhase;
import inf112.skeleton.app.game.MainGame;
import inf112.skeleton.app.objects.Actors.Robot;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;

import java.util.ArrayList;

import static org.junit.Assert.*;

@RunWith(GdxTestRunner.class)
public class CardDeckTest {

    private TiledMap map = new TmxMapLoader().load("Maps/MapForJunitTests.tmx");
    MainGame mainGame;
    ArrayList<SimpleProgramCard> exampleSelection;
    RoboRally switcher;
    @Before
    public void initialize() {
        switcher = new RoboRally();
        Assets.load();
        Assets.manager.finishLoading();
        mainGame = new MainGame();
        mainGame.setup(map);
        exampleSelection = new ArrayList<>();
        for(int i = 0; i < 5; i++) { exampleSelection.add(new MovementCard(1, CardType.BACK1)); }
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
    @Test
    public void dealCards() {
        CardDeck deck = new CardDeck();
        deck.shuffleCardsInDeck();
        int sum = 0;

        for (int i = 0; i < 10; i++) {
            deck.dealACard();
        }
        for(CardType types : deck.availableCards.keySet()) {
            sum += deck.availableCards.get(types);
        }
        assertEquals(74, sum);
    }

    @Test
    public void cardHandReductionAfterDamage() {
        /**
         * Checks cardhand after taking 6 damage. Cardhand should be empty
         */
        CardDeck deck = new CardDeck();
        deck.shuffleCardsInDeck();
        Robot robot = mainGame.getRobots().get(0);
        robot.getProgramSheet().getRegister().setCards(exampleSelection);
        robot.getProgramSheet().addDamage(6);
        robot.getProgramSheet().dealCards();
        ArrayList<SimpleProgramCard> newCardHand = mainGame.getRobots().get(0).getProgramSheet().getCardHand().getProgramCards();
        assertEquals(3, newCardHand.size());

    }


    @Test
    public void lockedRegisterRemainsAfterDealCards() {
        /**
         * Checks register after taking 6 damage. Register should be the same as before
         */
        DealCardsPhase phase = new DealCardsPhase();
        CardDeck deck = new CardDeck();
        deck.shuffleCardsInDeck();
        Robot robot = mainGame.getRobots().get(0);
        robot.getProgramSheet().getRegister().setCards(exampleSelection);

        robot.getProgramSheet().addDamage(5);
        ArrayList<SimpleProgramCard> oldRegister = robot.getProgramSheet().getRegister().getLockedCards();
        phase.run(); // Deal cards
        ArrayList<SimpleProgramCard> newRegister = robot.getProgramSheet().getRegister().getLockedCards();
        assertEquals(oldRegister, newRegister);

    }





}
