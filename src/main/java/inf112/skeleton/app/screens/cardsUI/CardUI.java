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


/**
 * Class responsible for vizualising the possible cards in a cardhand.
 * Wi
 */
public class CardUI extends Actor {

    private Table table;
    private  CardHand cardHand;
    private Stage stage;
    private int cardCount;
    public ArrayList<SimpleProgramCard> selectedCards;

    private int w;
    private int h;
    MainGame mainGame;

    /**
     * Constructor
     * @param mainGame: instance of main game
     */
    public CardUI(MainGame mainGame) {
        this.mainGame = mainGame;
        cardCount = 0;
        this.selectedCards = new ArrayList<>();
    }



    /**
     * Set up a Card UI after initialization
     * @param w
     * @param h
     * @param gameScreen
     */
    public void setUp(int w, int h, GameScreen gameScreen) {
        this.w = w;
        this.h = h;
        this.stage = gameScreen.getUIStage();
        this.table = new Table();
        table.setHeight(h-3);
        table.setPosition(w,h-h/4);
    }



    /**
     * Method that displays the possible cards contained in a cardhand in a table.
     * @param w: Starting witdth of UI
     * @param h Starting height of UI
     */

    /** Refaktor
     *
     */
    public void generateCards(Player robot) {
        cardHand = robot.getProgramSheet().getCardHand();
        int possibleNumcards = 5 - robot.getProgramSheet().getNumLockedRegisterCards(); // Subtract locked cards
        ArrayList<SimpleProgramCard> cardHandList = cardHand.getProgramCards();
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
                    else if (cardCount >= possibleNumcards) { System.out.println("Can't add anymore cards"); return; }
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
                sendCards(robot);
            }
            @Override
            public boolean touchDown (InputEvent event, float x, float y, int pointer, int button) {
                return true;
            }});
    }

    /** Returns table used for visualizing table **/
    public Table getTable() { return table; }

    /**
     * Method called after a player has successfully selected five cards and clicked the button
     *  --> 'Announce Done' part of DealCardsPhase.java
     */
    public void sendCards(Player robot) {
        int possibleNumcards = 5 - robot.getProgramSheet().getNumLockedRegisterCards(); // Subtract locked cards
        if (selectedCards.size() < possibleNumcards) {
            System.out.println("You have not selected enough cards. " + cardCount + " selected");
            return;
        }
        System.out.println("Cards sent to register: " + selectedCards);
        robot.getProgramSheet().getRegister().setCards(selectedCards); //Set cards for human player
        robot.getProgramSheet().getRegister().chosenCards = true;
        mainGame.executeCards(robot);
    }


}
