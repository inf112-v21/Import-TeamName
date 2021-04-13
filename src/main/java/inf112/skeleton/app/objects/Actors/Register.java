package inf112.skeleton.app.objects.Actors;

import inf112.skeleton.app.cards.SimpleProgramCard;

import java.util.ArrayList;

/**
 * Keeps order of the selected cards of a robot, e.g its Register
 */
public class Register {


    private int numLockedCards;
    protected ArrayList<SimpleProgramCard> registerCards;

    protected ArrayList<SimpleProgramCard> lockedRegisterCards;

    public void lockRegister(int lockNum) {
        this.numLockedCards = lockNum;
        for (int i = 0; i < lockNum; i++) {
            lockedRegisterCards.add(registerCards.remove(0));
        }


    }

    public void unlockRegister(int unlockNum) {
        if (unlockNum > numLockedCards) { numLockedCards = 0; return; }
        numLockedCards--;
        for (int i = 0; i < unlockNum; i++) {
            lockedRegisterCards.remove(lockedRegisterCards.size());
        }
    }

    public int getNumLockedRegisterCards() {return numLockedCards; }

    public Register() {
        this.numLockedCards = 0;
        registerCards = new ArrayList<>();
        lockedRegisterCards = new ArrayList<>();
    }


    public void addCardsToRegister(ArrayList<SimpleProgramCard> receivedCards) {
        wipeRegister();
        if (lockedRegisterCards.size() > 0) {registerCards.addAll(lockedRegisterCards); }
        registerCards.addAll(receivedCards);
    }


    public ArrayList<SimpleProgramCard> getRegisterCards() {return this.registerCards;}

    public ArrayList<SimpleProgramCard> getLockedCards() {return this.lockedRegisterCards;}

    public void setCards(ArrayList<SimpleProgramCard> cards) {this.registerCards = cards;}


    public void wipeRegister() {this.registerCards = new ArrayList<>();}

}
