package inf112.skeleton.app.screens.cardsUI;

import com.badlogic.gdx.maps.tiled.TiledMapTileLayer;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.InputListener;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.ImageButton;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import inf112.skeleton.app.cards.CardHand;
import inf112.skeleton.app.cards.SimpleProgramCard;
import inf112.skeleton.app.game.MainGame;
import inf112.skeleton.app.objects.Actors.Player;
import inf112.skeleton.app.objects.Actors.SimpleRobot;
import inf112.skeleton.app.screens.GameScreen;

import java.util.ArrayList;

public class CardUI extends Actor {

    private Table table;
    private Stage stage;
    CardHand cardHand;
    private int width; // Might be retreived from gdx.graphics.width
    private int height;
    SimpleRobot robot;


    public CardUI(GameScreen screen, MainGame game) {
        stage = screen.getUIStage();
        this.table = new Table();
        System.out.println(game.getRobots());
        this.robot = game.getRobots().get(0); // Take first
        stage.addActor(table);
        cardHand = robot.getProgramSheet().getCardHand();


    }


    public void setUpCards(int w, int h, Player player) {
        ArrayList<SimpleProgramCard> cardHandList = cardHand.getProgramCards();
        for (SimpleProgramCard card : cardHandList) {
            w += 1.5f;
            System.out.println(w);
            ImageButton cardButton = card.getCardButton();
            cardButton.setSize(2,5);
            cardButton.setPosition(w, h);
            table.add(cardButton);
            stage.addActor(cardButton);
            cardButton.addListener(new InputListener() {
                @Override
                public void touchUp(InputEvent event, float x, float y, int pointer, int button) {
                    card.action(player);
                }

                @Override
                public boolean touchDown(InputEvent event, float x, float y, int pointer, int button) {
                    return true;
                }
            });


        }
    }



    public void setPosition(int w, int h) {

    }

}
