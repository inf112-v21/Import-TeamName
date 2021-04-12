package inf112.skeleton.app.game;

//import inf112.skeleton.app.cards.CardDeck;

import inf112.skeleton.app.cards.SimpleProgramCard;
import inf112.skeleton.app.objects.Actors.SimpleRobot;

import java.util.ArrayList;

import static inf112.skeleton.app.game.MainGame.deck;
import static inf112.skeleton.app.game.MainGame.robots;

/**
 * Creates a new deck of cards. Shuffle deck, then deal cards to the players.
 */
public class DealCardsPhase implements IPhase {

    private int playercount;
    @Override
    public void run(MainGame mainGame) {
        handleLockedCards();
        createNewDeck();

    }

    /**
     *
     */
    private void createNewDeck() {
        for (SimpleRobot robot : robots) {
            robot.getProgramSheet().dealCards(deck);
        }

    }

    /**
     * Deals card
     */
    private void handleLockedCards() {
        ArrayList<SimpleProgramCard> lockedCards = new ArrayList<>();
        for (SimpleRobot robot: robots) {
            //System.out.println("Registercards" + robot.getProgramSheet().getRegister().getRegisterCards());
            //System.out.println("Lockred? " +robot.getProgramSheet().getLockedRegister());
            if (robot.getProgramSheet().getLockedRegister()) lockedCards.addAll(robot.getProgramSheet().getRegister().getRegisterCards());
            else robot.getProgramSheet().getRegister().wipeRegister();  // Wipe register
        }
        deck.createDeck(); // Creates new deck
        deck.handleLockedCards(lockedCards); // Calls upon method in carddeck which removes locked cards from deck
    }


}
