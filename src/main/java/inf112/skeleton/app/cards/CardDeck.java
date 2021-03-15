package inf112.skeleton.app.cards;

import java.util.*;

/**
 * A deck of cards:
 * Keeps order of the used and available cards in the game
 */
public class CardDeck {

    private ArrayList<SimpleProgramCard> inUseCards;
    //private ArrayList<SimpleProgramCard> availableCards;
    LinkedHashMap<CardType, Integer> availableCards;

    LinkedList<CardType> list;

    public CardDeck() {
        inUseCards = new ArrayList<>();
        availableCards = new LinkedHashMap<>();


    }

    /**
     *
     * @return a start card deck
     */
    public void startCards() {
        availableCards.put(CardType.MOVE1, 18);
        availableCards.put(CardType.MOVE2, 12);
        availableCards.put(CardType.MOVE3, 6);
        availableCards.put(CardType.ROTATERIGHT, 18);
        availableCards.put(CardType.ROTATELEFT, 18);
        availableCards.put(CardType.UTURN, 18);
    }

    /**
     * Shuffle a deck
     */
    public void shuffleDeck() {
        list = new LinkedList(availableCards.keySet());
        Collections.shuffle(list);
        Map<CardType, Integer> shuffledCards = new LinkedHashMap();
        list.forEach(k -> shuffledCards.put(k, availableCards.get(k)));
        availableCards = (LinkedHashMap<CardType, Integer>) shuffledCards;

    }

    public CardType dealACard() throws Exception {
        CardType type = list.pop();
        int numCards = availableCards.get(type);
        while(numCards <= 0) {
            if (list.size() <= 0) throw new Exception("No cards left");
            type = list.pop();
            numCards = availableCards.get(type);
        }
        availableCards.put(type, availableCards.get(type)-1);
        return (type);
    }

    public int getAvailableCards(CardType type) {
        return availableCards.get(type);
    }

}
