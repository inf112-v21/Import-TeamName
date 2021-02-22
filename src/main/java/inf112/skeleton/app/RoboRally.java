package inf112.skeleton.app;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import inf112.skeleton.app.assetManager.Assets;
import inf112.skeleton.app.screens.GameScreen;
import inf112.skeleton.app.screens.TitleScreen;

public class RoboRally extends Game {

    public SpriteBatch batch;
    public ShapeRenderer shapeRenderer;
    public BitmapFont font;
    TitleScreen titleScreen;
    GameScreen gameScreen;

    @Override
    public void create () {
        batch = new SpriteBatch();
        shapeRenderer = new ShapeRenderer();
        font = new BitmapFont();

        // Load assets from assetsmanager
        Assets.load();
        Assets.manager.finishLoading();

        // Set up screens
        titleScreen = new TitleScreen(this);
        gameScreen = new GameScreen(this);
        this.setScreen(titleScreen);
    }

    @Override
    public void dispose () {
        batch.dispose();
        shapeRenderer.dispose();
        font.dispose();
    }
    public void render() {
        super.render();
    }



}