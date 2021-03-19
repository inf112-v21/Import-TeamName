package inf112.skeleton.app.screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.utils.viewport.FitViewport;
import com.badlogic.gdx.utils.viewport.StretchViewport;
import inf112.skeleton.app.RoboRally;
import inf112.skeleton.app.assetManager.Assets;



import static com.badlogic.gdx.Gdx.gl;

/**
 * Screen displayed when a player wins or loses a game
 */
public class WinScreen implements Screen {

    final RoboRally game;
    private Stage stage;
    FitViewport viewPort;
    private Sprite victorySprite;
    private Image victory;
    float width;
    float height;

    public WinScreen(final RoboRally game, FitViewport viewPort) {
        this.game = game;
        this.viewPort = viewPort;
    }


    @Override
    public void show() {
        width = Gdx.graphics.getWidth();
        height = Gdx.graphics.getHeight();
        this.victorySprite = new Sprite(Assets.manager.get(Assets.VictoryImage));
        this.victory = new Image(victorySprite);
        victory.setPosition(width * 0.4F,height * 0.7F);
        victory.setSize(200,200);
        stage = new Stage(new StretchViewport(width, height));
        stage.addActor(victory);
    }

    @Override
    public void render(float v) {
        gl.glClearColor(0.0f, 0.0f, 0.0f, 0.0f);
        gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
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
