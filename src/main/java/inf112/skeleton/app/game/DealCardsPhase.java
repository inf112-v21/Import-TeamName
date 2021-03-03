package inf112.skeleton.app.game;

/**
 * Shuffle deck, then deal cards to the players.
 */
public class DealCardsPhase implements IPhase {

    @Override
    public void run() {
        shuffleCards();
        dealCards();
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
