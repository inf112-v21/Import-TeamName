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
import inf112.skeleton.app.assetManager.Assets;

import static com.badlogic.gdx.Gdx.gl;

public class MultiplayerScreen implements Screen {

    RoboRally game;

    private TextField assignIP;
    private SpriteBatch batch;
    private Stage stage;
    private Skin skin;
    private Texture title;

    float width;
    float height;

    int alignToAxisX = Gdx.graphics.getWidth()/2;

    public MultiplayerScreen (RoboRally game) {
        this.game = game;
    }


    @Override
    public void show () {
        width = Gdx.graphics.getWidth();
        height = Gdx.graphics.getHeight();
        batch = new SpriteBatch();
        title = new Texture("Images/buttons/simpleButton.png");
        stage = new Stage(new StretchViewport(width, height));
        skin = new Skin(Gdx.files.internal("uiskin.json"));


        //Textfield UI element
        assignIP = new TextField("Enter server ip here", skin);
        assignIP.setWidth(width*0.33f);
        assignIP.setHeight(height*0.04f);

                //!!! mostly for for UI element's x-axis centeralization.
        assignIP.setX(alignToAxisX - assignIP.getWidth()/2);

                //!!!height - UI element's height, then subtract some number,
                // this makes it so it always scales from top of screen taking into account button's size.
        assignIP.setY(height - assignIP.getHeight() -height*0.34f);


        //Button UI elements, maybe integrate with the button package later
        final Button joinGame = new TextButton("Join", skin);
        joinGame.setWidth(width*0.40f);
        joinGame.setHeight(height*0.10f);
        joinGame.setX(alignToAxisX - joinGame.getWidth()/2);
        joinGame.setY(height - joinGame.getHeight()-height*0.40f);

        final Button hostGame = new TextButton("Host", skin);
        hostGame.setWidth(width*0.40f);


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
        game.batch.draw(title, alignToAxisX - title.getWidth()/10, height*0.7f,width*0.3f, height*0.3f);
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
