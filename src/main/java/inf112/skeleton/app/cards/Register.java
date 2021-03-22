package inf112.skeleton.app.cards;

import java.util.ArrayList;

/**
 * Keeps order of the selected cards of a robot, e.g its Register
 */
public class Register {

    private ArrayList<SimpleProgramCard> registerCards;

    public Register() {
        registerCards = new ArrayList<>();
    }

    public void addCardToRegister(SimpleProgramCard card) {
        registerCards.add(card);
    }


    public ArrayList<SimpleProgramCard>  getRegister() {return this.registerCards;}


    public void setCards(ArrayList<SimpleProgramCard> cards) {this.registerCards = cards;}

}
