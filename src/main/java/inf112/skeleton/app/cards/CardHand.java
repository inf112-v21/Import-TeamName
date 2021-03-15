package inf112.skeleton.app.cards;
import java.util.Random;
import java.util.ArrayList;

/**
 * Class that handles all cards a robot has in a given round
 */
public class CardHand {


    private ArrayList<SimpleProgramCard> cards;
    private ArrayList<CardVisual> cardVisuals;
    private ArrayList<CardType> chosenCardTypes;
    private int numCards;

    public CardHand(int numCards) {
        cards = new ArrayList<>();
        cardVisuals = new ArrayList<>();
        this.numCards = numCards;
        randomizeCards();
        generateCardDeck();

    }


    public void  randomizeCards() {
        Random rand = new Random();
        chosenCardTypes = new ArrayList<>();
        CardType cardTypes[] = CardType.values();
        for (int i = 0; i < numCards; i++) {
            int randomNumber = rand.nextInt(numCards);
            chosenCardTypes.add(cardTypes[randomNumber]);
        }
    }

    public void generateCardDeck() {
        for (CardType type : chosenCardTypes) {
            if (type.equals(CardType.MOVE1)) {
                    cards.add(new MovementCard("Move 1 card", 1, 1));
                    cardVisuals.add(new CardVisual(CardType.MOVE1));
            }
            else if (type.equals(CardType.MOVE1)) {
                cards.add(new MovementCard("Move 2 card", 1, 2));
                cardVisuals.add(new CardVisual(CardType.MOVE2));
            }
            /**
             * Continue
             */
        }
    }



    public ArrayList<SimpleProgramCard> getProgramCards() {return cards;}
    public ArrayList<CardVisual> getVisuals() { return cardVisuals;}

    public void setNumCardsDeck(int num) {this.numCards = num;}
    public int getNumCardsDeck() {return this.numCards;}
    public void subtractNumCardsDeck() {this.numCards--;}


}
