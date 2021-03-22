package inf112.skeleton.app.screens.cardsUI;

import com.badlogic.gdx.scenes.scene2d.Stage;
import inf112.skeleton.app.objects.Actors.Player;
import inf112.skeleton.app.screens.GameScreen;

/**
 *
 * Card the player has selected and shall appear on screen as the player's selected cards.
 */
public class SelectedCards {


    // Get cards from register

    Stage uiStage;
    public SelectedCards(GameScreen gameScreen, Player player) {
        this.uiStage = gameScreen.getUIStage();
        uiStage.clear(); // Clears the previous screens


    }

    public void showSelectedCards() {

    }



}
