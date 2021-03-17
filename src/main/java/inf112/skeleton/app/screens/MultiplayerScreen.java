package inf112.skeleton.app.screens;

import com.badlogic.gdx.*;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.TextField;
import com.badlogic.gdx.utils.viewport.StretchViewport;
import inf112.skeleton.app.RoboRally;

import static com.badlogic.gdx.Gdx.gl;

public class MultiplayerScreen implements Screen {

    private static final int PLAY_BUTTON_HEIGHT = 150;
    private static final int PLAY_BUTTON_WIDTH = 300;
    private static final int EXIT_BUTTON_HEIGHT = 150;
    private static final int EXIT_BUTTON_WIDTH = 300;
    private TextField assignIP;

    StretchViewport viewPort;

    float width;
    float height;
    RoboRally game;

    Texture exitButtonPressed;
    Texture exitButtonUnpressed;
    Texture playButtonPressed;
    Texture playButtonUnpressed;
    SpriteBatch batch;
    Stage stage;
    Skin skin;

    int alignToAxisX = Gdx.graphics.getWidth() / 2;

    public MultiplayerScreen (RoboRally game) {
        this.game = game;
        playButtonPressed = new Texture("Images/buttons/play_button_pressed.png");
        playButtonUnpressed = new Texture("Images/buttons/play_button_unpressed.png");
        exitButtonPressed = new Texture("Images/buttons/exit_button_pressed.png");
        exitButtonUnpressed = new Texture("Images/buttons/exit_button_unpressed.png");
    }


    @Override
    public void show () {
        width = Gdx.graphics.getWidth();
        height = Gdx.graphics.getHeight();
        batch = new SpriteBatch();
        stage = new Stage(new StretchViewport(width, height));
        skin = new Skin(Gdx.files.internal("uiskin.json"));


        assignIP = new TextField("enter server ip here: ", skin);
        assignIP.setWidth(300);
        assignIP.setHeight(40);
        assignIP.setX(width - assignIP.getWidth());
        assignIP.setY(height - assignIP.getHeight());


        stage.addActor(assignIP);
    }

    @Override
    public void render(float delta) {
        gl.glClearColor(0.0f, 0.0f, 0.0f, 0.0f);
        gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
        game.batch.begin();
        game.font.draw(game.batch, "WELCOME TO ROBORALLY", width*0.34F, height * 0.75F);
        game.batch.draw(exitButtonPressed, alignToAxisX - EXIT_BUTTON_WIDTH / 2 , 100, EXIT_BUTTON_WIDTH,EXIT_BUTTON_HEIGHT);

        game.batch.end();
    }

    @Override
    public void resize(int width, int height) {
    }

    @Override
    public void pause () {
    }

    @Override
    public void resume () {
    }


    @Override
    public void hide() {
    }

    @Override
    public void dispose() {
    }



}
