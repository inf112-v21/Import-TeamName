package inf112.skeleton.app.game;

import inf112.skeleton.app.cards.CardDeck;
import inf112.skeleton.app.cards.IProgramCard;

import java.util.ArrayList;

/**
 * Creates a new deck of cards. Shuffle deck, then deal cards to the players.
 */
public class DealCardsPhase implements IPhase {

    @Override
    public void run() {
        //CardDeck deck = new CardDeck();
        shuffleCards();
        dealCards();
    }

    private ArrayList<IProgramCard> createDeck() {
        ArrayList<IProgramCard> deck = new ArrayList();

        //deck.add();

        return deck;
    }

    private void shuffleCards() {

    }

    private void dealCards() {

    }

    /*
    Deal cards plan
        - Shuffle deck.
        - If robot doesn't have a Damage token --> Deal 9 cards.
                - If robot has and damage, give out 9 cards - damage taken.
                        - If more than5 damage tokens --> See "Locking Register".
     */

}
