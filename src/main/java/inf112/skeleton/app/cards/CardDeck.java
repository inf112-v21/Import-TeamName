package inf112.skeleton.app.cards;
import java.util.Random;
import java.util.ArrayList;

/**
 * Class that handles all cards a robot has in a given round
 */
public class CardDeck {


    private ArrayList<SimpleProgramCard> cards;
    private ArrayList<CardVisual> cardVisuals;
    private ArrayList<CardType> chosenCardTypes;
    private int numCards;

    public CardDeck(int numCards) {
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
            //if (chosenCardTypes.size() == numCards) break;
            int randomNumber = rand.nextInt(numCards);
            chosenCardTypes.add(cardTypes[randomNumber]);
        }
    }

    public void generateCardDeck() {
        for (int i = 0; i < numCards; i++) {
                // Add cards

        }

    }

    public ArrayList<SimpleProgramCard> getProgramCards() {return cards;}
    public ArrayList<CardVisual> getVisuals() { return cardVisuals;}

    public void setNumCardsDeck(int num) {this.numCards = num;}
    public int getNumCardsDeck() {return this.numCards;}
    public void subtractNumCardsDeck() {this.numCards--;}


}
