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
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.utils.viewport.FitViewport;
import com.badlogic.gdx.utils.viewport.StretchViewport;
import inf112.skeleton.app.RoboRally;
import inf112.skeleton.app.assetManager.Assets;
import inf112.skeleton.app.cards.CardType;
import inf112.skeleton.app.cards.MovementCard;
import inf112.skeleton.app.cards.RotationCard;
import inf112.skeleton.app.game.CompleteRegisterPhase;
import inf112.skeleton.app.game.MainGame;
import inf112.skeleton.app.map.Board;
import inf112.skeleton.app.multiplayer.RRClient;
import inf112.skeleton.app.multiplayer.RRServer;
import inf112.skeleton.app.objects.Actors.Player;
import inf112.skeleton.app.screens.cardsUI.CardUI;
<<<<<<< HEAD

import com.esotericsoftware.minlog.Log;

import java.io.IOException;

=======
>>>>>>> master
import static com.badlogic.gdx.Gdx.gl;
import static inf112.skeleton.app.game.MainGame.gameBoard;
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


    MainGame game;
    private OrthogonalTiledMapRenderer mapRenderer;
    private OrthographicCamera gameCamera, uiCamera;


    private int viewPortWidth, viewPortHeight;

    Stage stage;
    Stage uiStage;
    FitViewport viewPort;
    private Player player;
    private boolean debugMode;


    int width;
    int height;

    int menuHeight = (int) round(Gdx.graphics.getHeight() * 0.2);
    private RoboRally switcher;

    static Board board;

    private boolean hosting;
    private String ip;
    private RRServer server;
    private RRClient client;
    private String name;



    public GameScreen(RoboRally switcher, Stage stage, FitViewport viewPort, boolean debugMode, boolean hosting, String ip, String name) {
        game = new MainGame();

        this.switcher = switcher;
        this.stage = stage;
        this.viewPort = viewPort;
        this.debugMode = debugMode;
        this.hosting = hosting;
        this.name = name;
        if(!ip.isEmpty()) {
            this.ip = ip;
        } else {
            this.ip = "localhost";
        }

        width = Gdx.graphics.getWidth();
        height = Gdx.graphics.getHeight();

        batch = new SpriteBatch();
        font = new BitmapFont();
        font.setColor(Color.RED);

        // Load map and get board data
        map = new TmxMapLoader().load("Maps/Chess.tmx"); // Get map file
        //this.board = new Board(map); // Get map objects
        game.setup(map);
        this.board = MainGame.gameBoard;
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
        player = new Player(startPos, textures);

        //Debug
        /*
        player.getProgramSheet().getRegister().selectCard(new MovementCard(1, CardType.MOVE2));
        player.getProgramSheet().getRegister().selectCard(new RotationCard(1, CardType.ROTATERIGHT));
        player.getProgramSheet().getRegister().selectCard(new MovementCard(1, CardType.MOVE2));
         */
        //Add player to game
        game.addPlayer(player);

        //MainGame.gameLoop();
    }


    /**
     * Method that performs action when key pressed is released
     * @param keycode: number id of pressed key
     */
    @Override
    public boolean keyUp(int keycode) {
        //Player playerTest = (Player) game.robots.get(0);
        //playerTest.moveRobotWASD(keycode);

        //Debug: Used to trigger a game phase
        if (keycode == Input.Keys.M) {
            TiledMapTileLayer playerTile = (TiledMapTileLayer) gameBoard.getMap().getLayers().get("Player");
            playerTile.setCell((int) player.getPosition().x, (int) player.getPosition().y, new TiledMapTileLayer.Cell()); // Clear previous robot image

            CompleteRegisterPhase phase = new CompleteRegisterPhase();
            phase.run();

            System.out.println("CompleteRegisterPhase is running.");
            System.out.println("Damage tokens: " + player.getProgramSheet().getDamage());
            System.out.println("Flags: " + player.getProgramSheet().getNumberOfFlags());
            System.out.println("Position: " + player.getPosition() + "\n");

            return true;
        }

        player.moveRobotWASD(keycode);
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
        if (player.getProgramSheet().getNumberOfFlags() == board.getNrFlags()) { switcher.setWinScreen(); } // As of now, player wins when visting all flags.

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
        client = new RRClient(name);

        if(hosting) {
            Log.info("starting server");
            try {
                server = new RRServer();
                client.connect("localhost");
            } catch (IOException e) {
                e.printStackTrace();  //no idea what this does, haven't read the documentation on IOException
                Log.info("Unable to start server.");
                Gdx.app.exit();
            }
        } else {
            client.connect(ip);
        }

        CardUI cardui = new CardUI(this, game);
        cardui.setUpCards(0,0, player); // Generate buttons and listeners for actions

        if (debugMode) {
            Gdx.input.setInputProcessor(this);
        }
        else {

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
        client.ceaseClient();
        if (server != null) {
            server.ceaseServer();
        }

    }

    public Stage getUIStage() {return this.uiStage; }
    public Stage getStage()   {return this.stage;   }

}