package inf112.skeleton.app.screens;

import com.badlogic.gdx.*;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL30;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.maps.tiled.TiledMap;
import com.badlogic.gdx.maps.tiled.TiledMapTileLayer;
import com.badlogic.gdx.maps.tiled.TmxMapLoader;
import com.badlogic.gdx.maps.tiled.renderers.OrthogonalTiledMapRenderer;
import com.badlogic.gdx.math.Vector2;

import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.InputListener;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.ImageButton;
import com.badlogic.gdx.utils.viewport.FitViewport;
import com.badlogic.gdx.utils.viewport.StretchViewport;
import com.badlogic.gdx.utils.viewport.Viewport;
import inf112.skeleton.app.RoboRally;
import inf112.skeleton.app.assetManager.Assets;

import inf112.skeleton.app.buttons.PlayButton;
import inf112.skeleton.app.cards.CardDeck;
import inf112.skeleton.app.cards.CardType;
import inf112.skeleton.app.cards.CardVisual;
import inf112.skeleton.app.enums.Rotation;
import inf112.skeleton.app.map.Board;
import inf112.skeleton.app.objects.Actors.Player;
import inf112.skeleton.app.objects.Actors.ProgramSheet;

import java.util.ArrayList;

import static com.badlogic.gdx.Gdx.gl;
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

    private OrthogonalTiledMapRenderer mapRenderer;
    private OrthographicCamera gameCamera, uiCamera;


    private int viewPortWidth, viewPortHeight;

    Stage stage;
    Stage uiStage;
    StretchViewport viewPort;
    private Player player;
    private boolean debugMode;


    int width;
    int height;

    int menuHeight = (int) round(Gdx.graphics.getHeight() * 0.2);
    RoboRally game;

    Board board;


    public GameScreen(RoboRally game, Stage stage, StretchViewport viewPort, boolean debugMode) {
        this.game = game;
        this.stage = stage;
        this.viewPort = viewPort;
        this.debugMode = debugMode;
        width = Gdx.graphics.getWidth();
        height = Gdx.graphics.getHeight();

        batch = new SpriteBatch();
        font = new BitmapFont();
        font.setColor(Color.RED);

        // Load map and get board data
        map = new TmxMapLoader().load("Maps/Chess.tmx"); // Get map file
        this.board = new Board(map); // Get map objects
        //Set viewPort dimensions to dimensions of board
        viewPortHeight = (int) board.getBoardDimensions().y;
        viewPortWidth = (int) board.getBoardDimensions().x;

        uiStage = new Stage(new StretchViewport(viewPortWidth, viewPortHeight));

        // Make camera
        gameCamera = new OrthographicCamera();
        uiCamera   = new OrthographicCamera();
        // Set camera to correct view settings, making room for dashboard.
        gameCamera.setToOrtho(false, viewPortWidth, viewPortHeight + 4);  // Set mode. +4, to include room for dashboard.
        uiCamera.setToOrtho(false, viewPortWidth, viewPortHeight);

        int initialCameraY = viewPortHeight - 10;
        // Set camera, but does not scale with the fit viewport
        //gameCamera.position.y = initialCameraY;
        gameCamera.update();
        uiCamera.update();

        uiStage.getViewport().setCamera(uiCamera);
        mapRenderer = new OrthogonalTiledMapRenderer(map,(float) 1/300);  // Render map
        mapRenderer.setView(gameCamera); // Attach camera to map


        //Handling player1
        tilePlayer = (TiledMapTileLayer) map.getLayers().get("Player");
        Texture playerTexture = Assets.manager.get(Assets.texture); // Texture of player
        TextureRegion[][] textures = new TextureRegion(playerTexture).split(300, 300);  // Splits player texture into the 3 parts. Live/Dead/Win
        //Place player on starting point.
        Vector2 startPos = board.getDockingBays().get(0).getPosition();
        player = new Player(startPos, textures, board);
    }

    /**
     * Method that performs action when key pressed is released
     * @param keycode: number id of pressed key
     */
    @Override
    public boolean keyUp(int keycode) {
        player.moveRobotWASD(tilePlayer, keycode);
        return true;
    }

    /**
     * Change camera location based on WASD keystrokes
     */
    public void moveCamera(int keycode) {

        if(keycode == Input.Keys.LEFT)
            gameCamera.translate(-32,0);
        if(keycode == Input.Keys.RIGHT)
            gameCamera.translate(32,0);
        if(keycode == Input.Keys.UP)
            gameCamera.translate(0,32);
        if(keycode == Input.Keys.DOWN)
            gameCamera.translate(0,-32);
        if(keycode == Input.Keys.NUM_1)
            map.getLayers().get(0).setVisible(!map.getLayers().get(0).isVisible());
        if(keycode == Input.Keys.NUM_2)
            map.getLayers().get(1).setVisible(!map.getLayers().get(1).isVisible());
    }

    @Override
    public void dispose() {
        batch.dispose();
        font.dispose();
    }

    @Override
    public void render(float v) {
        gl.glClearColor(0.0f, 0.0f, 0.0f, 0.0f);
        Gdx.gl.glClear(GL30.GL_COLOR_BUFFER_BIT);


        // RENDER GAME //
        Gdx.gl.glViewport( 0, menuHeight, Gdx.graphics.getWidth(), Gdx.graphics.getHeight());

        gameCamera.update();
        uiCamera.update();

        Vector2 playerPos = player.getPosition();
        int xPos = (int) playerPos.x;
        int yPos = (int) playerPos.y;

        //Win condition
        if (player.getProgramSheet().getNumberOfFlags() == board.getNrFlags()) { game.setWinScreen(); } // As of now, player wins when visting all flags.

        //Player is on a flag. Change texture to win texture
        if (board.isPosAFlag(playerPos)) {
            tilePlayer.setCell(xPos,yPos,player.getPlayerCellWon());
        } else if (board.isPosAPit(playerPos)) {
            tilePlayer.setCell(xPos,yPos,player.getPlayerCellDead());
        } else {
            tilePlayer.setCell(xPos, yPos, player.getPlayerCell());
        }
        
        mapRenderer.render();

        // Draw card visuals //
        Gdx.gl.glViewport( 0,0, Gdx.graphics.getWidth(),  menuHeight); // Set card deck menu height
        uiStage.act();
        uiStage.draw();
    }

    @Override
    public void show() {
        // Generate cards
        ImageButton move1Card = new CardVisual(0, 0, CardType.MOVE1).getCard();
        ImageButton rotateRight = new CardVisual(4f, 0, CardType.ROTATERIGHT).getCard();
        ImageButton rotateLeft = new CardVisual(8f, 0, CardType.ROTATELEFT).getCard();

        uiStage.addActor(move1Card);
        uiStage.addActor(rotateRight);
        uiStage.addActor(rotateLeft);

        CardDeck deck = new CardDeck(9);
        ArrayList<CardVisual> cardVisuals = deck.getVisuals();
        /**
         * Do something fantastic with cardeck
         */
        for (CardVisual visual : cardVisuals) {
            //uiStage.addActor(visual.getCard());
            //visual.getCard().addListener(new InputListener()
        }
        if (debugMode) {
            Gdx.input.setInputProcessor(this);
        }
        else {



            move1Card.addListener(new InputListener() {
                @Override
                public void touchUp(InputEvent event, float x, float y, int pointer, int button) {

                    player.moveRobot(tilePlayer, 1);
                }

                @Override
                public boolean touchDown(InputEvent event, float x, float y, int pointer, int button) {
                    return true;
                }
            });
            rotateRight.addListener(new InputListener() {
                @Override
                public void touchUp(InputEvent event, float x, float y, int pointer, int button) {
                    player.rotate(1);
                }

                @Override
                public boolean touchDown(InputEvent event, float x, float y, int pointer, int button) {
                    return true;
                }
            });
            rotateLeft.addListener(new InputListener() {
                @Override
                public void touchUp(InputEvent event, float x, float y, int pointer, int button) {
                    player.rotate(3);
                }

                @Override
                public boolean touchDown(InputEvent event, float x, float y, int pointer, int button) {
                    return true;
                }
            });
            Gdx.input.setInputProcessor(uiStage); // Set input to Card UI
        }
    }

    @Override
    public void resize(int width, int height) {

        stage.getViewport().update(width, height, true);
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

}