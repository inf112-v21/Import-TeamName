package inf112.skeleton.app.screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.InputAdapter;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL30;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.maps.tiled.TiledMap;
import com.badlogic.gdx.maps.tiled.TiledMapTileLayer;
import com.badlogic.gdx.maps.tiled.renderers.OrthogonalTiledMapRenderer;
import com.badlogic.gdx.math.Matrix4;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.utils.viewport.FitViewport;
import inf112.skeleton.app.RoboRally;
import inf112.skeleton.app.game.GameLoopEventHandler;
import inf112.skeleton.app.game.MainGame;
import inf112.skeleton.app.map.Board;
import inf112.skeleton.app.screens.utilities.CardUI;


import static com.badlogic.gdx.Gdx.gl;
import static inf112.skeleton.app.game.MainGame.robots;
import static java.lang.Math.round;

/**
 * Creates a game screen to be displayed while playing the game
 */
public class GameScreen extends InputAdapter implements Screen {

    private SpriteBatch batch;
    private BitmapFont font;

    private TiledMap map;

    // Layers on the map
    public TiledMapTileLayer tilePlayer;


    MainGame mainGame;
    private GameLoopEventHandler gameLoopEventHandler; //Handles redrawing of players, win & lose condition.
    private OrthogonalTiledMapRenderer mapRenderer;
    private OrthographicCamera gameCamera, uiCamera;
    private CardUI cardui;

    private int viewPortWidth, viewPortHeight;

    Stage stage;
    Stage uiStage;
    FitViewport viewPort;
    private final boolean debugMode;


    private int width;
    private int height;

    int menuHeight = (int) round(Gdx.graphics.getHeight() * 0.2);
    private final RoboRally switcher;

    static Board board;

    public void setMap (TiledMap map) {
        this.map = map;
        this.board = MainGame.gameBoard;
        this.viewPortHeight = (int) board.getBoardDimensions().y;
        this.viewPortWidth = (int) board.getBoardDimensions().x;

        this.uiStage = new Stage(new FitViewport(viewPortWidth, viewPortHeight-4));

        // Make camera
        this.gameCamera = new OrthographicCamera();
        this.uiCamera   = new OrthographicCamera();
        // Set camera to correct view settings, making room for dashboard.
        this.gameCamera.setToOrtho(false, viewPortWidth, viewPortHeight + 4);  // Set mode. +4, to include room for dashboard.
        this.uiCamera.setToOrtho(false, viewPortWidth*2, viewPortHeight/2);

        // Set camera, but does not scale with the fit viewport
        //gameCamera.position.y = initialCameraY;
        this.gameCamera.update();
        this.uiCamera.update();

        this.uiStage.getViewport().setCamera(uiCamera);
        this.mapRenderer = new OrthogonalTiledMapRenderer(map,(float) 1/300);  // Render map
        this.mapRenderer.setView(gameCamera);

        this.tilePlayer = (TiledMapTileLayer) map.getLayers().get("Player");
        this.gameLoopEventHandler = new GameLoopEventHandler(switcher, tilePlayer);
    }

    public GameScreen(RoboRally switcher, Stage stage, FitViewport viewPort, boolean debugMode) {
        this.switcher = switcher;
        this.stage = stage;
        this.viewPort = viewPort;

        this.debugMode = debugMode;
        this.width = Gdx.graphics.getWidth();
        this.height = Gdx.graphics.getHeight();

        this.batch = new SpriteBatch();
        this.font = new BitmapFont();
        this.font.setColor(Color.BLUE);
    }


    /**
     * Method that performs action when key pressed is released
     * @param keycode: number id of pressed key
     */
    @Override
    public boolean keyUp(int keycode) {
        robots.get(0).moveRobotWASD(keycode);
        return true;
    }



    @Override
    public void dispose() {
        this.batch.dispose();
        this.font.dispose();
    }

    @Override
    public void render(float v) {
        gl.glClearColor(0.0f, 0.0f, 0.0f, 0.0f);
        Gdx.gl.glClear(GL30.GL_COLOR_BUFFER_BIT);

        // RENDER GAME //
        Gdx.gl.glViewport( 0, menuHeight, Gdx.graphics.getWidth(), Gdx.graphics.getHeight());

        this.gameCamera.update();
        this.uiCamera.update();


        gameLoopEventHandler.run(); //Handles re-drawing of players.   | Changes texture if on pit or flag, only for testing

        mapRenderer.render();

        // Draw card visuals //
        Gdx.gl.glViewport( 0,0, Gdx.graphics.getWidth(),  menuHeight); // Set card deck menu height
        this.uiStage.act();
        this.uiStage.draw();
        batch.begin();
        Matrix4 pain = batch.getProjectionMatrix().cpy();
        batch.setProjectionMatrix(pain.cpy().scale(3f, 4.9f, 1));
        font.draw(batch, "Player " + cardui.getRobotName() + "'s turn", width*0.1f, height*0.15f);
        if (cardui.getLifeTokens() == 3) {
            font.draw(batch, "Life Tokens: X X X", width*0.1f, height*0.1f);
        } else if (cardui.getLifeTokens() == 2) {
            font.draw(batch, "Life Tokens: X X  ", width*0.1f, height*0.1f);
        } else if (cardui.getLifeTokens() == 1) {
            font.draw(batch, "Life Tokens: X    ", width*0.1f, height*0.1f);
        }
        batch.setProjectionMatrix(pain);
        batch.end();

    }

    @Override
    public void show() {

        this.cardui = new CardUI(mainGame);
        cardui.setUp((int) (uiCamera.viewportWidth)/2, (int) (uiCamera.viewportHeight / 4));
        uiStage.addActor(cardui.getTable());
        mainGame.startGameRound(cardui); // Game loop start

        if (this.debugMode) {
            Gdx.input.setInputProcessor(this);
        } else {
            Gdx.input.setInputProcessor(this.uiStage); // Set input to Card UI
        }
        
    }


    public void setGame(MainGame mainGame) {
        this.mainGame = mainGame;
    }


    @Override
    public void resize(int width, int height) {
        this.stage.getViewport().update(width, height, true);
    }

    @Override
    public void pause() {}

    @Override
    public void resume() {}

    @Override

    public void hide() {
    }
    public Stage getUIStage() {
        return this.uiStage;
    }
}

