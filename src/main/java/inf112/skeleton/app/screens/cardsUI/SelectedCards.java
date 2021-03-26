package inf112.skeleton.app.screens.cardsUI;

import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import inf112.skeleton.app.objects.Actors.Player;
import inf112.skeleton.app.screens.GameScreen;

/**
 * Card the player has selected and shall appear on screen as the player's selected cards.
 */
public class SelectedCards {


    // TODO: Get cards from register

    Stage uiStage;
    Table table;

    public SelectedCards(GameScreen gameScreen, Player player) {
        this.uiStage = gameScreen.getUIStage();
        this.table = new Table();
    }

    public void showSelectedCards() {

    }

}
