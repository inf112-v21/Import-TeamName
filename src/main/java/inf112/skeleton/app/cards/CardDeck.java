package inf112.skeleton.app.cards;

import inf112.skeleton.app.assetManager.Assets;

import java.util.ArrayList;

/**
 * This class will represent the deck of cards used in the game.
 */
public class CardDeck {

    ArrayList<IProgramCard> deck = new ArrayList<>();

    public CardDeck(int move1, int move2, int move3, int backUp, int rotateRight, int rotateLeft, int uTurn) {

        for (int i = 0; i < move1; i++) {
            deck.add(new MovementCard("Move 1", 100, Assets.manager.get(Assets.Move1Card), 1));
        }

        for (int i = 0; i < move2; i++) {
            deck.add(new MovementCard("Move 2", 100, Assets.manager.get(Assets.Move2Card), 1));
        }

        for (int i = 0; i < move3; i++) {
            deck.add(new MovementCard("Move 3", 100, Assets.manager.get(Assets.Move3Card), 1));
        }

        for (int i = 0; i < backUp; i++) {
            deck.add(new MovementCard("Back Up", 100, Assets.manager.get(Assets.BackUpCard), 1));
        }

        for (int i = 0; i < rotateRight; i++) {
            deck.add(new RotationCard("Rotate Right", 100, Assets.manager.get(Assets.RotateRightCard), 1));
        }

        for (int i = 0; i < rotateLeft; i++) {
            deck.add(new RotationCard("Rotate Left", 100, Assets.manager.get(Assets.RotateLeftCard), 3));
        }

        for (int i = 0; i < uTurn; i++) {
            deck.add(new RotationCard("U-Turn", 100, Assets.manager.get(Assets.UTurnCard), 2));
        }

    }

}
