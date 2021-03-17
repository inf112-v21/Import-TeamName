package inf112.skeleton.app.cards;
import java.util.ArrayList;
import java.util.Random;

import static inf112.skeleton.app.game.MainGame.deck;


/**
 * Class that handles all cards a robot is dealt
 */
public class CardHand {

    private final int range = 10; // Range of priority numbers

    private ArrayList<SimpleProgramCard> cards;
    private ArrayList<CardType> chosenCardTypes;
    private int numCards;

    public CardHand(int numCards)  {
        cards = new ArrayList<>();
        this.numCards = numCards;
        getCardsFromDeck();
        generateCardDeck();

    }

    public void getCardsFromDeck() {
        chosenCardTypes = new ArrayList<>();
        for (int i = 0; i < numCards; i++) { chosenCardTypes.add(deck.dealACard()); }
    }


    public int getPriority()  {
        Random rand = new Random();
        return rand.nextInt(range);
    }
    public void generateCardDeck() {
        for (CardType type : chosenCardTypes) {
            switch(type) {
                case MOVE1:       cards.add(new MovementCard(1,CardType.MOVE1)); break;
                case MOVE2:       cards.add(new MovementCard(1, CardType.MOVE2)); break;
                case MOVE3:       cards.add(new MovementCard(1, CardType.MOVE3)); break;
                case ROTATERIGHT: cards.add(new RotationCard(1, CardType.ROTATERIGHT)); break;
                case ROTATELEFT:  cards.add(new RotationCard(1, CardType.ROTATELEFT)); break;
                case UTURN:       cards.add(new RotationCard(getPriority(), CardType.UTURN)); break;
                default:          throw new IllegalArgumentException("Not valid cardtype, got" + type);
            }
        }
    }

    public ArrayList<SimpleProgramCard> getProgramCards() {return cards;}


    public void setNumCardsDeck(int num) {this.numCards = num;}
    public int getNumCardsDeck() {return this.numCards;}
    public void subtractNumCardsDeck() {this.numCards--;}


}
