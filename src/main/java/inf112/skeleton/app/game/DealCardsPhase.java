package inf112.skeleton.app.game;

//import inf112.skeleton.app.cards.CardDeck;

import inf112.skeleton.app.cards.SimpleProgramCard;
import inf112.skeleton.app.objects.Actors.Robot;

import java.util.ArrayList;

import static inf112.skeleton.app.game.MainGame.deck;
import static inf112.skeleton.app.game.MainGame.robots;

/**
 * Creates a new deck of cards. Shuffle deck, then deal cards to the players.
 */
public class DealCardsPhase  {


    /**
     * Deals cards.
     * Called upon every round
     */
    public void run() {
        handleLockedCards();
        dealCardsToPlayers();
    }

    /**
     * Deals a new hand for every card, based on the created deck
     */
    private void dealCardsToPlayers() {
        for (Robot robot : robots) {
            if (robot.getProgramSheet().isDead()) continue;
            robot.getProgramSheet().dealCards(); // Deal cards to each robot
        }

    }

    /**
     * Excludes locked cards from
     */
    private void handleLockedCards() {
        ArrayList<SimpleProgramCard> lockedCards = new ArrayList<>();
        for (Robot robot: robots) {
            if (robot.getProgramSheet().getNumLockedRegisterCards() > 0) lockedCards.addAll(robot.getProgramSheet().getRegister().getLockedCards());
        }
        deck.createDeck(); // Creates new deck
        deck.handleLockedCards(lockedCards); // Calls upon method in carddeck which removes locked cards from deck
    }


}
