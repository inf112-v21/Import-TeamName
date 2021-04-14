package inf112.skeleton.app.screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.maps.tiled.TiledMap;
import com.badlogic.gdx.maps.tiled.TmxMapLoader;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.*;
import com.badlogic.gdx.scenes.scene2d.utils.ChangeListener;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.badlogic.gdx.utils.viewport.FitViewport;
import com.badlogic.gdx.utils.viewport.StretchViewport;
import inf112.skeleton.app.RoboRally;
import inf112.skeleton.app.game.MainGame;
import inf112.skeleton.app.map.Board;

import static com.badlogic.gdx.Gdx.gl;
import static java.lang.Integer.parseInt;

public class MapSelectScreen implements Screen {

    final RoboRally switcher;

    private Stage stage;
    private Skin skin;

    private TiledMap map;

    float width;
    float height;
    int count;

    String mapPath;
    FitViewport viewPort;
    MainGame mainGame;


    int alignToAxisX = Gdx.graphics.getWidth()/2;

    public MapSelectScreen (RoboRally switcher, FitViewport viewPort) {
        this.switcher = switcher;
        this.viewPort = viewPort;
    }


    @Override
    public void show() {
        mainGame = new MainGame();
        width = Gdx.graphics.getWidth();
        height = Gdx.graphics.getHeight();
        stage = new Stage(new StretchViewport(width, height));
        skin = new Skin(Gdx.files.internal("uiskin.json"));


        //UI elements
        SelectBox<Integer> playerCount = new SelectBox<Integer>(skin);
        playerCount.setItems(1, 2, 3, 4, 5, 6, 7, 8);
        playerCount.setWidth(width*0.04f);
        playerCount.setHeight(height*0.04f);
        playerCount.setX(alignToAxisX - playerCount.getWidth()/2);
        playerCount.setY(height - playerCount.getHeight() - height*0.11f);



        playerCount.addListener(new ChangeListener() {
            @Override
            public void changed(ChangeEvent event, Actor actor) {
                count = playerCount.getSelected();
            }
        });

        final Button chess = new TextButton("Chess", skin);
        chess.setWidth(width*0.40f);
        chess.setHeight(height*0.10f);
        chess.setX(alignToAxisX - chess.getWidth()/2);
        chess.setY(height - chess.getHeight()-height*0.20f);

        final Button dizzy = new TextButton("DizzyHighway", skin);
        dizzy.setWidth(width*0.40f);
        dizzy.setHeight(height*0.10f);
        dizzy.setX(alignToAxisX - dizzy.getWidth()/2);
        dizzy.setY(height - dizzy.getHeight()-height*0.35f);

        final Button exchange = new TextButton("Exchange", skin);
        exchange.setWidth(width*0.40f);
        exchange.setHeight(height*0.10f);
        exchange.setX(alignToAxisX - exchange.getWidth()/2);
        exchange.setY(height - exchange.getHeight()-height*0.50f);

        final Button vault = new TextButton("Vault", skin);
        vault.setWidth(width*0.40f);
        vault.setHeight(height*0.10f);
        vault.setX(alignToAxisX - vault.getWidth()/2);
        vault.setY(height - vault.getHeight()-height*0.65f);

        final Button backButton = new TextButton("Back", skin);
        backButton.setWidth(width*0.40f);
        backButton.setHeight(height*0.08f);
        backButton.setX(alignToAxisX - backButton.getWidth()/2);
        backButton.setY(height - backButton.getHeight()-height*0.85f);

        stage.addActor(playerCount);
        stage.addActor(chess);
        stage.addActor(dizzy);
        stage.addActor(exchange);
        stage.addActor(vault);
        stage.addActor(backButton);


        chess.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y){
                mapPath = "Maps/Chess.tmx";
                startGame();
            }
        });

        dizzy.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y){
                mapPath = "Maps/DizzyHighway.tmx";
                startGame();
            }
        });

        exchange.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y){
                mapPath = "Maps/Exchange.tmx";
                startGame();
            }
        });

        vault.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y){
                mapPath = "Maps/Vault.tmx";
                startGame();
            }
        });

        backButton.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                switcher.setTitleScreen(mainGame);
            }
        });

        Gdx.input.setInputProcessor(stage);
    }

    @Override
    public void render(float delta) {
        gl.glClearColor(0.0f, 0.0f, 0.0f, 0.0f);
        gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

        stage.act(delta);
        stage.draw();

    }

    private void startGame() {
        //Check that legal amount of players are selected.
        if (count < 9 && count > 0) {
            this.map = new TmxMapLoader().load(mapPath);
            Board tempBoard = new Board(map);
            if (tempBoard.getNrDockingBays() < count) return; //Check that selected map supports the number of players.

            mainGame.setup(map);
            mainGame.setNumPlayers(count);
            switcher.getGameScreen().setMap(map);
            switcher.setGameScreen(mainGame);
        }
    }

    @Override
    public void resize(int width, int height) {
        stage.getViewport().update(width,height,true);
        stage.getCamera().viewportHeight = height;
        stage.getCamera().viewportWidth = width;
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
        skin.dispose();
    }
    public void setToMapSelect(MainGame mainGame) {
        this.mainGame = mainGame;
    }
}
