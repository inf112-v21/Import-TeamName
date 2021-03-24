package inf112.skeleton.app.cards;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.utils.viewport.FitViewport;
import inf112.skeleton.app.RoboRally;
import inf112.skeleton.app.game.MainGame;
import inf112.skeleton.app.screens.GameScreen;
import org.junit.Before;

public class CardDeckTest {

    MainGame mainGame;
    GameScreen gameScreen;
    Stage stage;

    @Before
    public void initialize() {
        mainGame = new MainGame();

        gameScreen = new GameScreen(new RoboRally(), stage, new FitViewport(Gdx.graphics.getWidth(), Gdx.graphics.getHeight()), false);
    }


}
