package inf112.skeleton.app.game;

//import inf112.skeleton.app.cards.CardDeck;

import inf112.skeleton.app.cards.IProgramCard;
import inf112.skeleton.app.objects.Actors.SimpleRobot;

import java.util.ArrayList;

import static inf112.skeleton.app.game.MainGame.deck;
import static inf112.skeleton.app.game.MainGame.robots;

/**
 * Creates a new deck of cards. Shuffle deck, then deal cards to the players.
 */
public class DealCardsPhase implements IPhase {

    @Override
    public void run() {
        //CardDeck deck = new CardDeck();
        deck.shuffleDeck();
        dealCards();
    }

    private ArrayList<IProgramCard> createDeck() {
        /**
         *
         * Create deck for all players?
         */
        ArrayList<IProgramCard> deck = new ArrayList();

        //deck.add();

        return deck;
    }

    /**
     * Deals card
     */
    private void dealCards() {
        for (SimpleRobot robot: robots) {
            robot.getProgramSheet().dealCards(deck);
        }
    }

}
