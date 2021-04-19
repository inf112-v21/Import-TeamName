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
import com.badlogic.gdx.maps.tiled.TmxMapLoader;
import com.badlogic.gdx.maps.tiled.renderers.OrthogonalTiledMapRenderer;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.utils.viewport.FitViewport;
import com.esotericsoftware.minlog.Log;
import inf112.skeleton.app.RoboRally;
import inf112.skeleton.app.assetManager.Assets;
import inf112.skeleton.app.game.GameLoopEventHandler;
import inf112.skeleton.app.game.MainGame;
import inf112.skeleton.app.map.Board;
import inf112.skeleton.app.multiplayer.NetworkPackets;
import inf112.skeleton.app.multiplayer.RRClient;
import inf112.skeleton.app.multiplayer.RRServer;
import inf112.skeleton.app.objects.Actors.Player;
import inf112.skeleton.app.objects.TileObjects.DockingBay;
import inf112.skeleton.app.screens.cardsUI.CardUI;

import java.io.IOException;
import java.util.List;

import static com.badlogic.gdx.Gdx.gl;
import static inf112.skeleton.app.game.MainGame.robots;
import static java.lang.Math.round;

public class MultiplayerGameScreen extends InputAdapter implements Screen {

    private OrthogonalTiledMapRenderer mapRenderer;
    private OrthographicCamera gameCamera, uiCamera;
    private CardUI cardui;

    private int viewPortWidth, viewPortHeight;

    private GameLoopEventHandler gameLoopEventHandler;

    private final SpriteBatch batch;
    private final BitmapFont font;

    Stage stage;
    Stage uiStage;
    FitViewport viewPort;

    static Board board;

    private TiledMap map;

    // Layers on the map
    public TiledMapTileLayer tilePlayer;

    int width;
    int height;

    int menuHeight = (int) round(Gdx.graphics.getHeight() * 0.2);
    private final RoboRally switcher;


    private boolean hosting;
    private String ip;
    private RRServer server;
    private RRClient client;
    private String name;

    MainGame mainGame;

    public MultiplayerGameScreen(RoboRally switcher, boolean hosting, String ip, String name) {
        this.switcher = switcher;
        this.hosting = hosting;
        this.name = name;
        //this.map = map;
        if(!ip.isEmpty()) {
            this.ip = ip;
        } else {
            this.ip = "localhost";
        }
        this.stage = switcher.getStage();
        this.viewPort = switcher.getViewPort();

        this.width = Gdx.graphics.getWidth();
        this.height = Gdx.graphics.getHeight();

        this.batch = new SpriteBatch();
        this.font = new BitmapFont();
        this.font.setColor(Color.RED);
    }

    public void startGameNow() {
        this.board = mainGame.gameBoard;
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


    @Override
    public boolean keyUp(int keycode) {
        float xD = robots.get(0).getPosition().x;
        float yD = robots.get(0).getPosition().y;
        robots.get(0).moveRobotWASD(keycode);
        float x = robots.get(0).getPosition().x;
        float y = robots.get(0).getPosition().y;
        NetworkPackets.MovedRobot packet = new NetworkPackets.MovedRobot(1, x, y, xD, yD);
        client.sendPacketUDP(packet);
        return true;
    }

    @Override
    public void show() {
        this.mainGame = new MainGame();

        if (hosting == false) {
            this.map = new TmxMapLoader().load("Maps/Chess.tmx");
            mainGame.setup(map);
        }
        client = new RRClient(name, mainGame);

        //starting server if host, otherwise connect to "ip"
        if(hosting != false) {
            Log.info("Starting server~");
            try {
                server = new RRServer(mainGame);
                client.connect("localhost");   //the host, thus localhost

                this.map = server.getMap();
            } catch (IOException e) {
                Log.info("Failed to start the server. Already hosting one?");
                Gdx.app.exit();
            }
        } else {
            client.connect(ip);  //if you aren't hosting, then you can only be trying to join.
        }

        startGameNow();

        /**
        this.cardui = new CardUI(mainGame);
        cardui.setUp((int) (uiCamera.viewportWidth) / 2, (int) (uiCamera.viewportHeight / 4), this);
        uiStage.addActor(cardui.getTable());
        mainGame.gameLoop(cardui);
*/
  //      Gdx.input.setInputProcessor(this.uiStage);
        Gdx.input.setInputProcessor(this);

    }

    public void testHandler (float delta) {
        int a = (int) mainGame.robots.get(0).getPosition().x;
        int b = (int) mainGame.robots.get(0).getPosition().y;
    }

    @Override
    public synchronized void render(float delta) {
        gl.glClearColor(0.0f, 0.0f, 0.0f, 0.0f);
        Gdx.gl.glClear(GL30.GL_COLOR_BUFFER_BIT);

        // RENDER GAME //
        Gdx.gl.glViewport( 0, menuHeight, Gdx.graphics.getWidth(), Gdx.graphics.getHeight());

        this.gameCamera.update();
        this.uiCamera.update();

        gameLoopEventHandler.run();
        mapRenderer.render();

/**
        Gdx.gl.glViewport( 0,0, Gdx.graphics.getWidth(),  menuHeight); // Set card deck menu height
        this.uiStage.act();
        this.uiStage.draw();
 */
    }

    @Override
    public void resize(int width, int height) {
        this.stage.getViewport().update(width, height, true);
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

    @Override
    public void dispose() {
        client.ceaseClient();
        if (server != null) {
            server.ceaseServer();
        }
    }
    public Stage getUIStage() {
        return this.uiStage;
    }

    public MainGame getMainGame() {
        return mainGame;
    }
}
