package inf112.skeleton.app.screens.cardsUI;

import com.badlogic.gdx.maps.tiled.TiledMapTileLayer;
import com.badlogic.gdx.math.Vector2;
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
    Player robot;
    private int cardCount;
    private ArrayList<SimpleProgramCard> selectedCards;

    public CardUI(GameScreen screen, MainGame game) {
        stage = screen.getUIStage();
        this.table = new Table();
        this.robot = game.getRobots().get(0);
        stage.addActor(table);
        cardHand = robot.getProgramSheet().getCardHand();
        cardCount = 0;
        selectedCards = new ArrayList<>();
    }


    public void setUpCards(int w, int h) {
        ArrayList<SimpleProgramCard> cardHandList = cardHand.getProgramCards();
        for (SimpleProgramCard card : cardHandList) {
            w += 1.7f;
            ImageButton cardButton = card.getCardButton();
            cardButton.setSize(2,5);
            cardButton.setPosition(w, h);
            table.add(cardButton);
            stage.addActor(cardButton);
            cardButton.addListener(new InputListener() {
                @Override
                public void touchUp(InputEvent event, float x, float y, int pointer, int button) {
                    if (cardCount >= 5) { System.out.println("Can't add anymore cards"); return; }
                    else if (selectedCards.contains(card)) {
                        System.out.println("Removed card" + card);
                        selectedCards.remove(card);
                        cardCount--;
                    }
                    else {
                        System.out.println("Added card" + card);
                        selectedCards.add(card);
                        cardCount++;
                    }

                }
                @Override
                public boolean touchDown(InputEvent event, float x, float y, int pointer, int button) {
                    return true;
                }
            });


        }
    }

    public Table getTable() {
        return table;
    }

    public void renderPlayer(TiledMapTileLayer tilePlayer) {
        Vector2 playerPos = robot.getPosition();
        int xPos = (int) playerPos.x;
        int yPos = (int) playerPos.y;
        tilePlayer.setCell(xPos, yPos, robot.getPlayerCell());
    }


    /**
     * Method called after a player has sucessfully selectd five cards and clicked the button
     */
    public void sendCards() {
        if (cardCount < 5) {
            System.out.println("You have not selected enough cards. " + cardCount + " selected");
            return;
        }

        robot.getProgramSheet().getRegister().setCards(selectedCards);
    }



}
