package inf112.skeleton.app;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import inf112.skeleton.app.screens.GameScreen;
import inf112.skeleton.app.screens.StartScreen;

public class GameWithScreens extends Game {

    public SpriteBatch batch;
    public ShapeRenderer shapeRenderer;
    public BitmapFont font;
    StartScreen startScreen;
    GameScreen gameScreen;

    @Override
    public void create () {
        batch = new SpriteBatch();
        shapeRenderer = new ShapeRenderer();
        font = new BitmapFont();

        // Set up screens
        startScreen = new StartScreen(this);
        gameScreen = new GameScreen(this);
        this.setScreen(startScreen);
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