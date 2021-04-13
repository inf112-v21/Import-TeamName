package inf112.skeleton.app.cards;

import com.badlogic.gdx.maps.tiled.TiledMap;
import com.badlogic.gdx.maps.tiled.TmxMapLoader;
import inf112.skeleton.app.GdxTestRunner;
import inf112.skeleton.app.RoboRally;
import inf112.skeleton.app.assetManager.Assets;
import inf112.skeleton.app.game.DealCardsPhase;
import inf112.skeleton.app.game.MainGame;
import inf112.skeleton.app.objects.Actors.Player;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;

import java.util.ArrayList;

import static org.junit.Assert.*;

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
    @Test
    public void dealCards() {
        CardDeck deck = new CardDeck();
        deck.shuffleCardsInDeck();
        int sum = 0;

        for (int i = 0; i < 10; i++) {
            CardType type = deck.dealACard();
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
        mainGame.getRobots().get(0).getProgramSheet().addDamage(6);
        mainGame.getRobots().get(0).getProgramSheet().dealCards();
        ArrayList<SimpleProgramCard> newCardHand = mainGame.getRobots().get(0).getProgramSheet().getCardHand().getProgramCards();
        assertEquals(newCardHand.size(), 3);

    }


    @Test
    public void lockedRegisterRemainsAfterDealCards() {
        /**
         * Checks register after taking 6 damage. Register should be the same as before
         */
        DealCardsPhase phase = new DealCardsPhase();
        CardDeck deck = new CardDeck();
        deck.shuffleCardsInDeck();
        Player player = mainGame.getRobots().get(0);
        ArrayList<SimpleProgramCard> selectedCards = new ArrayList<>();
        selectedCards.add(new MovementCard(1, CardType.BACK1));
        selectedCards.add(new MovementCard(1, CardType.BACK1));
        selectedCards.add(new MovementCard(1, CardType.BACK1));
        selectedCards.add(new MovementCard(1, CardType.BACK1));
        selectedCards.add(new MovementCard(1, CardType.BACK1));
        player.getProgramSheet().getRegister().setCards(selectedCards);
        ArrayList<SimpleProgramCard> oldRegister = player.getProgramSheet().getRegister().getRegisterCards();
        player.getProgramSheet().addDamage(6);
        phase.run(); // Deal cards
        ArrayList<SimpleProgramCard> newRegister = player.getProgramSheet().getRegister().getRegisterCards();
        assertEquals(oldRegister, newRegister);

    }
    @Test
    public void registerWiped() {
        /**
         * Register is wiped if the player is dealt less than 6 damage.
         */
        DealCardsPhase phase = new DealCardsPhase();
        CardDeck deck = new CardDeck();
        Player player = mainGame.getRobots().get(0);
        ArrayList<SimpleProgramCard> selectedCards = new ArrayList<>();
        selectedCards.add(new MovementCard(1, CardType.BACK1));
        selectedCards.add(new MovementCard(1, CardType.BACK1));
        selectedCards.add(new MovementCard(1, CardType.BACK1));
        selectedCards.add(new MovementCard(1, CardType.BACK1));
        selectedCards.add(new MovementCard(1, CardType.BACK1));
        player.getProgramSheet().getRegister().setCards(selectedCards);
        ArrayList<SimpleProgramCard> oldRegister = player.getProgramSheet().getRegister().getRegisterCards();
        player.getProgramSheet().addDamage(3); // Take 3 damage, should wipe register
        phase.run(); // Deal cards
        ArrayList<SimpleProgramCard> newRegister = player.getProgramSheet().getRegister().getRegisterCards();
        assertEquals(new ArrayList<SimpleProgramCard>(), newRegister);

    }


    @Test
    public void lockedRegisterCardsCannotBeDealtFromDeck() {
        DealCardsPhase phase = new DealCardsPhase();
        CardDeck deck = new CardDeck();
        int oldCardDeckLength = 0;
        for(CardType types : deck.availableCards.keySet()) {
            oldCardDeckLength += deck.availableCards.get(types);
        }

        Player player = mainGame.getRobots().get(0);
        ArrayList<SimpleProgramCard> selectedCards = new ArrayList<>();
        selectedCards.add(new MovementCard(1, CardType.BACK1));
        selectedCards.add(new MovementCard(1, CardType.BACK1));
        selectedCards.add(new MovementCard(1, CardType.BACK1));
        selectedCards.add(new MovementCard(1, CardType.BACK1));
        selectedCards.add(new MovementCard(1, CardType.BACK1));
        player.getProgramSheet().getRegister().setCards(selectedCards);
        player.getProgramSheet().addDamage(6); // Take 6 damage and lock register
        phase.run();
        int newCardDeckLength = 0;
        for(CardType types : deck.availableCards.keySet()) {
            newCardDeckLength += deck.availableCards.get(types);
        }
        System.out.println("back cards" + deck.availableCards.get(CardType.BACK1));
        System.out.println("Old card length" + oldCardDeckLength);
        System.out.println("New cards length " + newCardDeckLength);
        assertTrue((oldCardDeckLength-5) == newCardDeckLength );


    }




}
