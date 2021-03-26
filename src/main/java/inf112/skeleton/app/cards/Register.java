package inf112.skeleton.app.cards;

import java.util.ArrayList;

/**
 * Keeps order of the selected cards of a robot, e.g its Register
 */
public class Register {

    private ArrayList<SimpleProgramCard> registerCards;

    public Register() { registerCards = new ArrayList<>(); }


    public ArrayList<SimpleProgramCard> getRegisterCards() {return this.registerCards;}


    public void setCards(ArrayList<SimpleProgramCard> cards) {this.registerCards = cards;}

}
