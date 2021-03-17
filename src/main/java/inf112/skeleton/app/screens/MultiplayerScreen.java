package inf112.skeleton.app.screens;

import com.badlogic.gdx.*;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.ui.Button;
import com.badlogic.gdx.scenes.scene2d.InputListener;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.ui.TextField;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
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


        //Textfield UI element
        assignIP = new TextField("Enter server ip here", skin);
        assignIP.setWidth(400);
        assignIP.setHeight(40);

        //!!! mostly for for UI element's x-axis centeralization.
        assignIP.setX(width/2 - assignIP.getWidth()/2);

        //!!!height - UI element's height, then subtract some number, this makes it so it always scales with top of screen.
        assignIP.setY(height - assignIP.getHeight() - 300);


        //Button UI elements, maybe integrate with the button package later
        final Button joinGame = new TextButton("Join", skin);
        joinGame.setWidth(400);
        joinGame.setHeight(200);
        joinGame.setX(width/2 - joinGame.getWidth()/2);
        joinGame.setY(height - joinGame.getHeight());

        final Button hostGame = new TextButton("Host", skin);

        final Button findGame = new TextButton("Find", skin);





        stage.addActor(assignIP);
        stage.addActor(joinGame);
        stage.addActor(hostGame);
        stage.addActor(findGame);

        assignIP.setTextFieldListener(new TextField.TextFieldListener() {
            public void keyTyped(TextField textField, char c) {
                if (c == '\n') textField.getOnscreenKeyboard().show(false);
            }
        });

        joinGame.addListener(new ClickListener() {
            @Override
           public void clicked(InputEvent event, float x, float y){
               join();
           }
        });

        hostGame.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y){
                host();
            }
        });
        findGame.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y){
                find();
            }
        });

        Gdx.input.setInputProcessor(stage);
    }

    @Override
    public void render(float delta) {
        gl.glClearColor(0.9f, 0.9f, 0.9f, 0.0f);
        gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
        game.batch.begin();
        game.font.draw(game.batch, "WELCOME TO ROBORALLY", width*0.34F, height * 0.75F);
      //  game.batch.draw(exitButtonPressed, alignToAxisX - EXIT_BUTTON_WIDTH / 2 , 100, EXIT_BUTTON_WIDTH,EXIT_BUTTON_HEIGHT);
        game.batch.end();

        stage.draw();
        stage.act();

    }

    private void join() {

    }

    private void host() {

    }

    private void find() {

    }

    @Override
    public void resize(int width, int height) {
        stage.getCamera().viewportHeight = height;
        stage.getCamera().viewportWidth = width;
    }

    @Override
    public void pause () {
    }

    @Override
    public void resume () {
    }


    @Override
    public void hide() {
        stage.dispose();
        skin.dispose();
        batch.dispose();
        Gdx.input.setInputProcessor(null);
    }

    @Override
    public void dispose() {
    }



}
