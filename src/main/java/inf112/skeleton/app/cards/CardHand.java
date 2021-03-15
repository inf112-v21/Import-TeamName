package inf112.skeleton.app.cards;
import java.util.ArrayList;

import static inf112.skeleton.app.game.MainGame.deck;

/**
 * Class that handles all cards a robot is dealt
 */
public class CardHand {


    private ArrayList<SimpleProgramCard> cards;
    private ArrayList<CardType> chosenCardTypes;
    private int numCards;

    public CardHand(int numCards, CardDeck deck)  {
        cards = new ArrayList<>();
        this.numCards = numCards;
        getCardsFromDeck();
        generateCardDeck();

    }


    public void getCardsFromDeck() {
        chosenCardTypes = new ArrayList<>();
        for (int i = 0; i < numCards; i++) { chosenCardTypes.add(deck.dealACard()); }
    }

    public void generateCardDeck() {
        for (CardType type : chosenCardTypes) {
            if (type.equals(CardType.MOVE1)) {
                cards.add(new MovementCard(1,CardType.MOVE1, 1));

            } else if (type.equals(CardType.MOVE2)) {
                cards.add(new MovementCard(1, CardType.MOVE2, 2));

            }
            /**
             * Continue
             */

        }
    }





    public ArrayList<SimpleProgramCard> getProgramCards() {return cards;}


    public void setNumCardsDeck(int num) {this.numCards = num;}
    public int getNumCardsDeck() {return this.numCards;}
    public void subtractNumCardsDeck() {this.numCards--;}


}
