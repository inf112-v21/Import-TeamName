package inf112.skeleton.app.screens;
import com.badlogic.gdx.*;
import com.badlogic.gdx.assets.AssetDescriptor;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.scenes.scene2d.*;
import com.badlogic.gdx.scenes.scene2d.ui.ImageButton;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.badlogic.gdx.scenes.scene2d.utils.Drawable;
import com.badlogic.gdx.scenes.scene2d.utils.TextureRegionDrawable;
import com.badlogic.gdx.utils.Align;
import com.badlogic.gdx.utils.viewport.ScreenViewport;
import inf112.skeleton.app.GameWithScreens;
import inf112.skeleton.app.RoboRally;

import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.math.Rectangle;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import inf112.skeleton.app.assetManager.Assets;
import inf112.skeleton.app.buttons.SimpleButton;
import org.lwjgl.system.CallbackI;

public class StartScreen implements Screen {

    final GameWithScreens game;
    private Rectangle bucket;
    private OrthographicCamera camera;
    private Stage stage;

    float circleX = 300;
    float circleY = 150;
    float circleRadius = 50;

    float xSpeed = 4;
    float ySpeed = 3;
    private Object ButtonFactory;

    public StartScreen(final GameWithScreens game) {
        this.game = game;
        // create the camera and the SpriteBatch





    }


    @Override
    public void show() {
        float width = Gdx.graphics.getWidth();
        float height = Gdx.graphics.getHeight();

        BitmapFont font = new BitmapFont();


        Texture texture = new Texture("Images/buttons/simpleButton.png");
        Drawable drawable = new TextureRegionDrawable(new TextureRegion(texture));
        ImageButton playButton = new ImageButton(drawable);
        playButton.setPosition(50,50);
        playButton.setSize(20,20);
        //stage = new Stage();
        stage = new Stage(new ScreenViewport());

        SimpleButton button = new SimpleButton(20,20,20, 20);
        stage.addActor(playButton);

        playButton.addListener(new InputListener(){
            @Override
            public void touchUp (InputEvent event, float x, float y, int pointer, int button) {
                game.setScreen(new RoboRally(game));
            }
            @Override
            public boolean touchDown (InputEvent event, float x, float y, int pointer, int button) {
                return true;
            }});

        Gdx.input.setInputProcessor(stage);

    }

    @Override
    public void render(float delta) {
        Gdx.gl.glClearColor(1, 1, 1, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

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
        Gdx.input.setInputProcessor(null);
    }



    @Override
    public void dispose() {
        stage.dispose();

    }
}
