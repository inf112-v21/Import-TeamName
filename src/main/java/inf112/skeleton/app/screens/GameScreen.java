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

import inf112.skeleton.app.RoboRally;
import inf112.skeleton.app.assetManager.Assets;

import inf112.skeleton.app.map.Board;
import inf112.skeleton.app.objects.Actors.Player;
import inf112.skeleton.app.objects.IObject;


/**
 * Creates a game screen to be displayed while playing the game
 */
public class GameScreen extends InputAdapter implements Screen {
    private SpriteBatch batch;
    private BitmapFont font;

    private TiledMap map;

    // Layers on the map
    public TiledMapTileLayer tileBoard, tilePlayer, tileHole, tileFlag1, tileFlag2;

    private OrthogonalTiledMapRenderer mapRenderer;
    private OrthographicCamera camera;

    public Player player;
    public boolean movePlayer = true;

    RoboRally game;

    Board board;


    public GameScreen(RoboRally game) {
        this.game = game;
        batch = new SpriteBatch();
        font = new BitmapFont();
        font.setColor(Color.RED);

        camera = new OrthographicCamera();                               // Make camera
        camera.setToOrtho(false, 12,16);  // Set mode
        camera.update();

        map = new TmxMapLoader().load("Maps/Chess.tmx");                  // Get map file
        mapRenderer = new OrthogonalTiledMapRenderer(map,(float) 1/300);  // Render map
        mapRenderer.setView(camera); // Attach camera to map

        tileBoard = (TiledMapTileLayer) map.getLayers().get("Floor");
        tilePlayer = (TiledMapTileLayer) map.getLayers().get("Player");
        tileHole = (TiledMapTileLayer) map.getLayers().get("Pit");
        tileFlag1 = (TiledMapTileLayer) map.getLayers().get("Flag1");
        tileFlag2 = (TiledMapTileLayer) map.getLayers().get("Flag2");

        Texture playerTexture = Assets.manager.get(Assets.texture); // Texture of player
        TextureRegion[][] textures = new TextureRegion(playerTexture).split(300, 300);  // Splits player texture into the 3 parts. Live/Dead/Win

        this.board = new Board(map); // Get map objects

        player = new Player(0,0, textures, board);
    }

    /**
     * Method that performs action when key pressed is released
     * @param keycode: number id of pressed key
     */
    @Override
    public boolean keyUp(int keycode) {
        if (movePlayer) {
            player.moveRobot(tilePlayer, keycode);
            return true;
        }
        moveCamera(keycode);
        return true;
    }


    /**
     * Change camera location based on WASD keystrokes
     */
    public void moveCamera(int keycode) {

        if(keycode == Input.Keys.LEFT)
            camera.translate(-32,0);
        if(keycode == Input.Keys.RIGHT)
            camera.translate(32,0);
        if(keycode == Input.Keys.UP)
            camera.translate(0,32);
        if(keycode == Input.Keys.DOWN)
            camera.translate(0,-32);
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
        Gdx.gl.glClearColor(1, 1, 1, 1);
        Gdx.gl.glClear(GL30.GL_COLOR_BUFFER_BIT);

        Vector2 playerPos = player.getPosition();
        int xPos = (int) playerPos.x;
        int yPos = (int) playerPos.y;

        //Player is on a flag. Win
        if (board.isPosAFlag(playerPos)) {
            tilePlayer.setCell(xPos,yPos,player.getPlayerCellWon());
        } else if (board.isPosAPit(playerPos)) {
            tilePlayer.setCell(xPos,yPos,player.getPlayerCellDead());
        } else {
            tilePlayer.setCell(xPos,yPos,player.getPlayerCell());
        }

        mapRenderer.render();
    }

    @Override
    public void show() {
        Gdx.input.setInputProcessor(this);
    }


    @Override
    public void resize(int width, int height) {
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