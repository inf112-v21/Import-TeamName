package inf112.skeleton.app.screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.utils.viewport.ScreenViewport;
import inf112.skeleton.app.GameWithScreens;
import inf112.skeleton.app.RoboRally;

public class GameScreen implements Screen {
    GameWithScreens game;
    Stage stage;


    public GameScreen(final GameWithScreens game) {

        this.game = game;
        // create the camera and the SpriteBatch


    }
    @Override
    public void show() {
        System.out.println("Gamescren");
        stage = new Stage(new ScreenViewport());
    }

    @Override
    public void render(float v) {
        System.out.println("Switched");
        Gdx.gl.glClearColor(1, 1, 1, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
        RoboRally roboRally = new RoboRally();
        roboRally.create();
        roboRally.render();
        stage.act();
        stage.draw();

    }

    @Override
    public void resize(int i, int i1) {

    }

    @Override
    public void pause() {

    }

    @Override
    public void resume() {

    }

    @Override
    public void hide() {

    }

    @Override
    public void dispose() {
        stage.dispose();
    }
}
