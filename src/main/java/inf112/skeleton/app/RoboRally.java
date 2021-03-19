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
import inf112.skeleton.app.screens.GameScreen;
import inf112.skeleton.app.screens.TitleScreen;
import inf112.skeleton.app.screens.WinScreen;

public class RoboRally extends Game {

    public SpriteBatch batch;
    public ShapeRenderer shapeRenderer;
    public BitmapFont font;
    TitleScreen titleScreen;
    GameScreen  gameScreen;
    WinScreen   winScreen;


    FitViewport viewPort;
    Stage stage;

    private boolean debugMode = false;

    @Override
    public void create () {
        batch = new SpriteBatch();
        shapeRenderer = new ShapeRenderer();
        font = new BitmapFont();

        viewPort = new FitViewport(Gdx.graphics.getWidth(), Gdx.graphics.getHeight());
        viewPort.apply();
        stage = new Stage(viewPort);

        // Load assets from assetsmanager
        Assets.load();
        Assets.manager.finishLoading();

        // Set up screens
        titleScreen = new TitleScreen(this, stage, viewPort);
        gameScreen = new GameScreen(this, stage, viewPort, debugMode);
        winScreen  = new WinScreen(this, viewPort);
        Gdx.input.setInputProcessor(stage);
        this.setScreen(titleScreen); // Set screen to title screen
    }

    public void debugModeOn() {debugMode = true; }

    @Override
    public void dispose () {
        batch.dispose();
        shapeRenderer.dispose();
        font.dispose();
    }

    public void setGameScreen() {
        setScreen(gameScreen);
    }

    public void setWinScreen() {
        setScreen(winScreen);
    }

    public void render() {
        super.render();
    }

}