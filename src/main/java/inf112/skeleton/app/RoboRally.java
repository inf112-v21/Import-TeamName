package inf112.skeleton.app;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.utils.viewport.FitViewport;
import com.badlogic.gdx.utils.viewport.StretchViewport;
import inf112.skeleton.app.assetManager.Assets;
import inf112.skeleton.app.game.MainGame;
import inf112.skeleton.app.screens.GameScreen;
import inf112.skeleton.app.screens.MultiplayerScreen;
import inf112.skeleton.app.screens.TitleScreen;
import inf112.skeleton.app.screens.WinScreen;

public class RoboRally extends Game {

    public SpriteBatch batch;
    public ShapeRenderer shapeRenderer;
    public BitmapFont font;
    GameScreen  gameScreen;
    WinScreen   winScreen;
    MultiplayerScreen multiplayerScreen;
    TitleScreen titleScreen;

    String name;
    FitViewport viewPort;
    Stage stage;

    private boolean debugMode = false;

    @Override
    public void create() {
        batch = new SpriteBatch();
        shapeRenderer = new ShapeRenderer();
        font = new BitmapFont();
        name = "ice";

        viewPort = new FitViewport(Gdx.graphics.getWidth(), Gdx.graphics.getHeight());
        viewPort.apply();
        stage = new Stage(viewPort);

        // Load assets from assetsmanager
        Assets.load();
        Assets.manager.finishLoading();

        // Set up screens

        gameScreen = new GameScreen(this, stage, viewPort, debugMode, false, name, name);
        winScreen  = new WinScreen(this, viewPort);
        titleScreen = new TitleScreen(this, stage, viewPort);
        //multiplayerScreen = new MultiplayerScreen(this); <-- Not in use? Another is made in TitleScreen.java  -Endre

        Gdx.input.setInputProcessor(stage);
        this.setScreen(titleScreen); // Set screen to title screen
    }

    public void debugModeOn() {
        debugMode = true;
    }

    @Override
    public void dispose() {
        batch.dispose();
        shapeRenderer.dispose();
        font.dispose();
    }


    public void setGameScreen(MainGame mainGame) {
        gameScreen.setGame(mainGame);
        setScreen(gameScreen);
    }

    public void setWinScreen() {
        setScreen(winScreen);
    }

    @Override
    public void render() {
        super.render();
    }

}