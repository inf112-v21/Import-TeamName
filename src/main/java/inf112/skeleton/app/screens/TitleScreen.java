package inf112.skeleton.app.screens;
import com.badlogic.gdx.*;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.scenes.scene2d.*;
import com.badlogic.gdx.scenes.scene2d.ui.ImageButton;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.utils.viewport.FitViewport;
import com.badlogic.gdx.utils.viewport.StretchViewport;
import inf112.skeleton.app.RoboRally;

import inf112.skeleton.app.buttons.PlayButton;
import inf112.skeleton.app.game.MainGame;
import inf112.skeleton.app.objects.Actors.Player;

import static com.badlogic.gdx.Gdx.gl;

/**
 * Creates a title screen to act as a menu.
 * * The display the player encounters
 */
public class TitleScreen implements Screen {

    final RoboRally switcher;
    private SpriteBatch batch;
    private Stage stage;
    private Skin skin;
    private Texture title;

    FitViewport viewPort;

    float width;
    float height;

    MainGame game;
    private boolean debugMode = true;

    int alignToAxisX = Gdx.graphics.getWidth()/2;
    /**
     * Constructor method

     */
    public TitleScreen(final RoboRally switcher, Stage stage, FitViewport viewPort) {
        this.switcher = switcher;
        this.stage = stage;
        this.viewPort = viewPort;
        this.game = new MainGame();
    }

    @Override
    public void show() {
        this.width = Gdx.graphics.getWidth();
        this.height = Gdx.graphics.getHeight();


        ImageButton playButton = new PlayButton(width * 0.4F,height * 0.7F).getButton();
        this.stage = new Stage(new StretchViewport(width, height));
        this.stage.addActor(playButton);


        game.setNumPlayers(5); //Max is 8 players
        for (Player player : game.getRobots()) {
            System.out.println(player);
        }
        playButton.addListener(new InputListener(){
            @Override
            public void touchUp (InputEvent event, float x, float y, int pointer, int button) {
                switcher.setGameScreen(game);
            }
            @Override
            public boolean touchDown (InputEvent event, float x, float y, int pointer, int button) {
                return true;
            }});

        Gdx.input.setInputProcessor(stage);

    }


    @Override
    public void render(float delta) {
        gl.glClearColor(0.0f, 0.0f, 0.0f, 0.0f);
        gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
        this.switcher.batch.begin();
        this.switcher.font.draw(switcher.batch, "WELCOME TO ROBORALLY", width*0.34F, height * 0.75F);
        this.switcher.batch.end();
        this.stage.act();
        this.stage.draw();

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
