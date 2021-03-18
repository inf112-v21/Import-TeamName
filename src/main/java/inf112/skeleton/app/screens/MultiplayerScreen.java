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
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.ui.TextField;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.badlogic.gdx.utils.viewport.StretchViewport;
import com.esotericsoftware.kryonet.Client;
import inf112.skeleton.app.RoboRally;
import inf112.skeleton.app.assetManager.Assets;
import inf112.skeleton.app.multiplayer.NetworkPackets;

import java.net.InetAddress;

import static com.badlogic.gdx.Gdx.gl;

public class MultiplayerScreen implements Screen {

    RoboRally game;

    private TextField assignIP;
    private TextField assignName;
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
        title = new Texture("Images/title.png");
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

        assignName = new TextField("Enter name here", skin);
        assignName.setWidth(width*0.20f);
        assignName.setHeight(height*0.04f);
        assignName.setX(alignToAxisX - assignName.getWidth()/2);
        assignName.setY(height - assignName.getHeight() - height*0.45f);


        //Button UI elements, maybe integrate with the button package later
        final Button joinGame = new TextButton("Join", skin);
        joinGame.setWidth(width*0.40f);
        joinGame.setHeight(height*0.10f);
        joinGame.setX(alignToAxisX - joinGame.getWidth()/2);
        joinGame.setY(height - joinGame.getHeight()-height*0.40f);

        final Button hostGame = new TextButton("Host", skin);
        hostGame.setWidth(width*0.40f);
        hostGame.setHeight(height*0.10f);
        hostGame.setX(alignToAxisX - hostGame.getWidth()/2);
        hostGame.setY(height - hostGame.getHeight()-height*0.55f);


        final Button findGame = new TextButton("Find", skin);
        findGame.setWidth(width*0.10f);
        findGame.setHeight(height*0.04f);
        findGame.setX(assignIP.getX()+assignIP.getWidth()+ width*0.02f);
        findGame.setY(assignIP.getY());

        final Button exitGame = new TextButton("Exit", skin);
        exitGame.setWidth(width*0.40f);
        exitGame.setHeight(height*0.10f);
        exitGame.setX(alignToAxisX - exitGame.getWidth()/2);
        exitGame.setY(height - exitGame.getHeight()-height*0.70f);



        stage.addActor(assignIP);
        stage.addActor(joinGame);
        stage.addActor(hostGame);
        stage.addActor(findGame);
        stage.addActor(exitGame);

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
        exitGame.addListener(new ClickListener() {
           @Override
           public void clicked(InputEvent event, float x, float y) {
               Gdx.app.exit();
           }
        });

        Gdx.input.setInputProcessor(stage);
    }

    @Override
    public void render(float delta) {
        gl.glClearColor(0.0f, 0.0f, 0.0f, 0.0f);
        gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
        game.batch.begin();
        game.batch.draw(title, alignToAxisX - title.getWidth()/3, height*0.7f,width*0.7f, height*0.3f);
        game.batch.end();

        stage.draw();
        stage.act();

    }

    private void join() {
 //       game.setGameScreen(new GameScreen()); //thinking of changing GameScreen's input to needing the input from this page as parameters
    }

    private void host() {
  //      game.setGameScreen(new GameScreen());
    }

    private void find() {
        Client client = new Client();
        client.start();
        InetAddress find = client.discoverHost(NetworkPackets.udpPort, 5000);
        if (find != null) {
            assignIP.setText(find.getHostAddress());
        }
        client.stop();
        client.close();
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
        title.dispose();
        Gdx.input.setInputProcessor(null);
    }

    @Override
    public void dispose() {
    }



}
