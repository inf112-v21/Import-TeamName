package inf112.skeleton.app.screens.cardsUI;

import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import inf112.skeleton.app.cards.CardHand;
import inf112.skeleton.app.cards.CardVisual;
import inf112.skeleton.app.cards.IProgramCard;
import inf112.skeleton.app.cards.SimpleProgramCard;
import inf112.skeleton.app.objects.Actors.SimpleRobot;
import inf112.skeleton.app.screens.GameScreen;

import java.util.ArrayList;

public class CardUI extends Actor {

    private Table table;
    private Stage stage;
    CardHand deck;
    private int width; // Might be retreived from gdx.graphics.width
    private int height;

    public CardUI(GameScreen screen, SimpleRobot robot) {
        stage = screen.getUIStage();
        this.table = new Table();
        deck = robot.getProgramSheet().getCardDeck();


    }


    public void setUpCards(int w, int h) {
        ArrayList<SimpleProgramCard> potentialCards = deck.getProgramCards();
        for (SimpleProgramCard card : potentialCards) {
            /*


             */

            card.setSize(5,5);
            card.setPosition(width + 7, height);
        }
    }



    public void setPosition(int w, int h) {

    }

}
