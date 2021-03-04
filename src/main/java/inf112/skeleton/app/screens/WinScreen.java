package inf112.skeleton.app.screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.utils.viewport.StretchViewport;
import inf112.skeleton.app.RoboRally;

import static com.badlogic.gdx.Gdx.gl;

/**
 * Screen displayed when a player wins or loses a game
 */
public class WinScreen implements Screen {

    final RoboRally game;
    private Stage stage;
    StretchViewport viewPort;

    float width;
    float height;

    public WinScreen(final RoboRally game, Stage stage, StretchViewport viewPort) {
        this.game = game;
        this.stage = stage;
        this.viewPort = viewPort;

        width = Gdx.graphics.getWidth();
        height = Gdx.graphics.getHeight();
    }


    @Override
    public void show() {

    }

    @Override
    public void render(float v) {
        gl.glClearColor(0.0f, 0.0f, 0.0f, 0.0f);
        gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
        game.batch.begin();
        game.font.draw(game.batch, "You have won", width*0.34F, height * 0.75F);
        game.batch.end();
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

    }
}
