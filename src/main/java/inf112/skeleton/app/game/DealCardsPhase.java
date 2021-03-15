package inf112.skeleton.app.game;

//import inf112.skeleton.app.cards.CardDeck;

import inf112.skeleton.app.cards.IProgramCard;
import inf112.skeleton.app.objects.Actors.SimpleRobot;

import java.awt.*;
import java.util.ArrayList;

import static inf112.skeleton.app.game.Game.robots;

/**
 * Creates a new deck of cards. Shuffle deck, then deal cards to the players.
 */
public class DealCardsPhase implements IPhase {

    @Override
    public void run() {
        //CardDeck deck = new CardDeck();
        //shuffleCards();
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
            robot.getProgramSheet().dealCards();
        }
    }

    /*
    Deal cards plan
        - Shuffle deck.
        - If robot doesn't have a Damage token --> Deal 9 cards.
                - If robot has and damage, give out 9 cards - damage taken.
                        - If more than5 damage tokens --> See "Locking Register".
     */

}
