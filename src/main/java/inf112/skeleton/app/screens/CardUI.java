package inf112.skeleton.app.screens;

import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import inf112.skeleton.app.cards.CardHand;
import inf112.skeleton.app.cards.CardVisual;
import inf112.skeleton.app.objects.Actors.SimpleRobot;

import java.util.ArrayList;

public class CardUI extends Actor {

    private Table table;
    private Stage stage;
    CardHand deck;


    public CardUI(GameScreen screen, SimpleRobot robot) {
        stage = screen.getUIStage();
        this.table = new Table();
        deck = robot.getProgramSheet().getCardDeck();

    }


    public void setUpCards(int w, int h) {
        ArrayList<CardVisual> cardVisuals = deck.getVisuals();
        for (CardVisual cardVisual : cardVisuals) {
            /*


             */
        }
    }





}
