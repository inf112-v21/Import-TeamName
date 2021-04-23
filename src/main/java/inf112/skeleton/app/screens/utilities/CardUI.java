package inf112.skeleton.app.screens.utilities;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.InputListener;
import com.badlogic.gdx.scenes.scene2d.ui.ImageButton;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.utils.TextureRegionDrawable;
import inf112.skeleton.app.buttons.PlayButton;
import inf112.skeleton.app.cards.CardHand;
import inf112.skeleton.app.cards.SimpleProgramCard;
import inf112.skeleton.app.game.MainGame;
import inf112.skeleton.app.objects.Actors.Robot;

import java.util.ArrayList;


/**
 * Class responsible for vizualising the possible cards in a cardhand.
 * Wi
 */
public class CardUI extends Actor {

    private Table table;
    private  CardHand cardHand;
    private int cardCount;
    public ArrayList<SimpleProgramCard> selectedCards;

    private int w;
    private int h;
    MainGame mainGame;

    private Robot robot;

    public String playerName;
    public int lifeTokens;
    /**
     *
     * Constructor
     * @param mainGame: instance of main game
     */
    public CardUI(MainGame mainGame) {
        this.mainGame = mainGame;
        this.selectedCards = new ArrayList<>();

    }



    /**
     * Set up a Card UI after initialization
     * @param w: table position width
     * @param h: table position height
     */
    public void setUp(int w, int h) {
        this.w = w;
        this.h = h;
        this.table = new Table();
        table.setSize(w-w, h-h-1 );
        table.setPosition(w,h-h/4);
        table.setRound(false);
    }

    /**
     * Method that displays the possible cards contained in a cardhand in a table.
     *  Called on by ChooseCardsphase for every robot. Retrives the cards dealt to the player and
     *  generates buttons that allows the robot to select its register cards-
     *  Populates the list selectedCards
     */
    public void generateCards(Robot robot) {
        this.robot = robot;
        cardCount  = 0;
        cardHand = robot.getProgramSheet().getCardHand();
        int possibleNumcards = 5 - robot.getProgramSheet().getNumLockedRegisterCards(); // Subtract locked cards
        ArrayList<SimpleProgramCard> cardHandList = cardHand.getProgramCards();
        for (SimpleProgramCard card : cardHandList) {
            ImageButton cardButton = card.getCardButton();
            table.add(cardButton).size(2, 3);
            cardButton.addListener(new InputListener() {
                @Override
                public void touchUp(InputEvent event, float x, float y, int pointer, int button) {
                    //Remove already selected card
                    if (selectedCards.contains(card)) {
                        selectedCards.remove(card);
                        cardCount--;
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
                    if (cardCount >= 5) {
                        //Set normal texture
                        cardButton.getStyle().imageChecked = cardButton.getStyle().imageUp;
                    } else {
                        //Set highlight texture
                        cardButton.getStyle().imageChecked =  new TextureRegionDrawable(card.getToggledTexture());

                    }
                    return true;
                }
            });

        }
        ImageButton playButton = new PlayButton(w,h/10, 2, 2).getButton();
        table.add(playButton).size(2,3);
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


    public Robot getCurrentRobot(){
        return this.robot;
    }

    /** Returns table used for visualizing table **/
    public Table getTable() { return table; }

    /**
     * Method called after a player has successfully selected five cards and clicked the button
     *  --> 'Announce Done' part of DealCardsPhase.java
     */
    public void sendCards(Robot robot) {
        int possibleNumcards = 5 - robot.getProgramSheet().getNumLockedRegisterCards(); // Subtract locked cards
        if (selectedCards.size() < possibleNumcards) {
            System.out.println("You have not selected enough cards. " + cardCount + " selected");
            return;
        }
        System.out.println("Cards sent to register: " + selectedCards);
        robot.getProgramSheet().getRegister().setCards(selectedCards); //Set cards for human player
        robot.getProgramSheet().getRegister().chosenCards = true;

        // Reset list and count of selected cards
        selectedCards = new ArrayList<>();
        cardCount = 0;

        table.clearChildren();
        mainGame.chooseCardsPhase.run(mainGame, this);

    }

    public String getRobotName () {
        return this.playerName;
    }

    public int getLifeTokens() {
        return this.lifeTokens;
    }

}
