package inf112.skeleton.app.cards;

import inf112.skeleton.app.objects.Actors.Player;
import inf112.skeleton.app.objects.Actors.ProgramSheet;

import java.util.*;

import static inf112.skeleton.app.game.MainGame.robots;

/**
 * A deck of cards:
 * Keeps order of the used and available cards in the game
 */
public class CardDeck {


    public static LinkedHashMap<CardType, Integer> availableCards;

    LinkedList<CardType> list; // List used for shuffling cardTypes

    public CardDeck() {
        CreateDeck();
    }

    /**
     *
     * @return a start card deck
     */
    public void CreateDeck() {
        availableCards = new LinkedHashMap<>();
        availableCards.put(CardType.MOVE1, 18);
        availableCards.put(CardType.MOVE2, 12);
        availableCards.put(CardType.MOVE3, 6);
        availableCards.put(CardType.BACK1, 6);
        availableCards.put(CardType.ROTATERIGHT, 18);
        availableCards.put(CardType.ROTATELEFT, 18);
        availableCards.put(CardType.UTURN, 6);
    }

    /**
     *
     */
    public void checkLockedRegister() {

        for (Player player: robots) {
            if (player.getProgramSheet().getLockREgister()) {
                ArrayList<SimpleProgramCard> cards = player.getProgramSheet().getRegister();
            }
        }

    }


    /**
     * Shuffle a deck. Called every time a card is dealt
     */
    public void shuffleCardsInDeck() {
        list = new LinkedList(availableCards.keySet());
        Collections.shuffle(list);
        LinkedHashMap<CardType, Integer> shuffledCards = new LinkedHashMap();
        list.forEach(k -> shuffledCards.put(k, availableCards.get(k)));
        availableCards = shuffledCards;

    }

    /**
     * Deals a card from the available cards.
     * Shuffles cardtypes before proceeding
     * @return a single CardType, used to construct a full cardhand
     */
    public CardType dealACard()  {
        shuffleCardsInDeck();
        CardType type = list.pop();
        int numCards = availableCards.get(type);
        while(numCards <= 0) { // If no cards left, proceed to next type of card
            try {
                type = list.pop();
                numCards = availableCards.get(type);
            }
            catch(Exception e) { System.out.println("No cards left"); }
        }
        availableCards.put(type, availableCards.get(type)-1);
        return (type);
    }



    public int getAvailableCards(CardType type) { return availableCards.get(type); }

}