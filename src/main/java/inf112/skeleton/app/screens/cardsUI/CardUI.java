package inf112.skeleton.app.screens.cardsUI;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.TextureRegion;

import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.InputListener;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.ImageButton;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.utils.TextureRegionDrawable;
import inf112.skeleton.app.buttons.PlayButton;
import inf112.skeleton.app.cards.CardHand;
import inf112.skeleton.app.cards.SimpleProgramCard;
import inf112.skeleton.app.game.MainGame;
import inf112.skeleton.app.objects.Actors.Player;
import inf112.skeleton.app.screens.GameScreen;
import java.util.ArrayList;


public class CardUI extends Actor {

    private Table table;
    private Stage stage;
    CardHand cardHand;
    Player robot;
    private int cardCount;
    private ArrayList<SimpleProgramCard> selectedCards;
    GameScreen gameScreen;

    public CardUI(GameScreen gameScreen, MainGame game) {
        this.gameScreen = gameScreen;
        stage = gameScreen.getUIStage();
        this.table = new Table();
        this.robot = game.getRobots().get(0);
        stage.addActor(table);
        cardHand = robot.getProgramSheet().getCardHand();
        cardCount = 0;
        selectedCards = new ArrayList<>();
    }


    public void setUpCards(int w, int h) {
        ArrayList<SimpleProgramCard> cardHandList = cardHand.getProgramCards();
        //table.setWidth((stage.getWidth()*0.8f));
        //table.setHeight(stage.getHeight()/3);
        table.setHeight(h-3);
        table.setPosition(w,h-h/4);
        System.out.println(table.getMinHeight());
        for (SimpleProgramCard card : cardHandList) {
            System.out.println(card.getType());
            ImageButton cardButton = card.getCardButton();
            cardButton.setSize(2,2);
            cardButton.setPosition(w, h/10);
            table.add(cardButton).size(2,2);

            cardButton.addListener(new InputListener() {
                @Override
                public void touchUp(InputEvent event, float x, float y, int pointer, int button) {
                    //Remove already selected card
                    if (selectedCards.contains(card)) {
                        selectedCards.remove(card);
                        cardCount--;
                        System.out.println("Removed card" + card);
                    }
                    else if (cardCount >= 5) { System.out.println("Can't add anymore cards"); return; }
                    //Add selected card
                    else {
                        selectedCards.add(card);
                        cardCount++;
                        System.out.println("Added card" + card + " Selected cards: " + selectedCards);
                    }
                }
                @Override
                public boolean touchDown(InputEvent event, float x, float y, int pointer, int button) {
                    //TODO: Change texture in a better way. Can we avoid creating new 'Highlight' textures for all buttons? Maybe create pixel layer on the fly.
                    if (cardCount >= 5) {
                        //Set normal texture
                        cardButton.getStyle().imageChecked = cardButton.getStyle().imageUp;
                    } else {
                        //Set highlight texture
                        cardButton.getStyle().imageChecked =  new TextureRegionDrawable(new TextureRegion(new Texture("Images/cards/Move1Down.png")));

                    }
                    return true;
                }
            });


        }
        ImageButton playButton = new PlayButton(w,h/10, 2, 2).getButton();
        table.add(playButton).size(2,2);
        playButton.addListener(new InputListener(){
            @Override
            public void touchUp (InputEvent event, float x, float y, int pointer, int button) {
                sendCards();
            }
            @Override
            public boolean touchDown (InputEvent event, float x, float y, int pointer, int button) {
                return true;
            }});
    }

    public Table getTable() {
        return table;
    }

    /**
     * Method called after a player has sucessfully selectd five cards and clicked the button
     */
    public void sendCards() {
        if (cardCount < 5) {
            System.out.println("You have not selected enough cards. " + cardCount + " selected");
            return;
        }
        System.out.println("Cards sent to register: " + selectedCards);
        robot.getProgramSheet().getRegister().setCards(selectedCards); //Set cards for human player

        gameScreen.executeCards();
    }


}
