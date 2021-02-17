package inf112.skeleton.app;

import com.badlogic.gdx.ApplicationListener;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.InputAdapter;
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
import com.badlogic.gdx.maps.tiled.tiles.StaticTiledMapTile;
import com.badlogic.gdx.math.Vector2;

public class Game extends InputAdapter implements ApplicationListener  {
    private SpriteBatch batch;
    private BitmapFont font;

    private TiledMap map;
    // Layers on the map
    private TiledMapTileLayer tileBoard, tilePlayer, tileHole, tileFlag1, tileFlag2;

    private OrthogonalTiledMapRenderer mapRenderer;
    private OrthographicCamera camera;

    private TiledMapTileLayer.Cell playerCell, playerDead, playerWon;
    private Vector2 playerPos;

    boolean movePlayer = true;


    /**
     * Constructor method
     */
    @Override
    public void create() {

        batch = new SpriteBatch();
        font = new BitmapFont();
        font.setColor(Color.RED);

        camera = new OrthographicCamera();                             // Make camera
        camera.setToOrtho(false, 12,16);  // Set mode
        camera.update();

        map = new TmxMapLoader().load("Maps/Chess.tmx");       // Get map file
        mapRenderer = new OrthogonalTiledMapRenderer(map,(float) 1/300);  // Render map
        mapRenderer.setView(camera); // Attach camera to map

        tileBoard = (TiledMapTileLayer) map.getLayers().get("Floor");
        tilePlayer = (TiledMapTileLayer) map.getLayers().get("Player");
        tileHole = (TiledMapTileLayer) map.getLayers().get("Pit");
        tileFlag1 = (TiledMapTileLayer) map.getLayers().get("Flag1");
        tileFlag2 = (TiledMapTileLayer) map.getLayers().get("Flag2");


        Texture playerTexture = new Texture("Images/player.png"); // Texture of player
        TextureRegion[][] tx = new TextureRegion(playerTexture).split(300, 300);  // Splits player texture into the 3 parts. Live/Dead/Win

        playerCell = new TiledMapTileLayer.Cell().setTile(new StaticTiledMapTile(tx[0][0]));
        playerDead = new TiledMapTileLayer.Cell().setTile(new StaticTiledMapTile(tx[0][1]));
        playerWon = new TiledMapTileLayer.Cell().setTile(new StaticTiledMapTile(tx[0][2]));

        playerPos = new Vector2(0,0);

        //UserInputManager uim = new UserInputManager();
        Gdx.input.setInputProcessor(this);
    }

    /**
     * Method that performs action when key pressed is released
     * @param keycode: number id of pressed key
     */
    @Override
    public boolean keyUp(int keycode) {
        if (movePlayer) {
            changeDirection(tilePlayer, playerPos, keycode);
            return true;
        }
        moveCamera(keycode);
        return true;
    }

    /**
     * Change direction of an actor, e.g a a place
     * @param actor: the tile containing an actor, e.g a player
     * @param position: a 2d position
     * @param keycode: number id of pressed key
     */
    public void changeDirection(TiledMapTileLayer actor, Vector2 position, int keycode) {

        actor.setCell((int) position.x,(int) position.y, new TiledMapTileLayer.Cell());
        switch(keycode) {
            case Input.Keys.W:
                position.y += 1;
                break;
            case Input.Keys.S:
                position.y -= 1;
                break;
            case Input.Keys.A:
                position.x -= 1;
                break;
            case Input.Keys.D:
                position.x += 1;
                break;
        }
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
    public void render() {
        Gdx.gl.glClearColor(1, 1, 1, 1);
        Gdx.gl.glClear(GL30.GL_COLOR_BUFFER_BIT);

        //Win/lose condition
        if (tileHole.getCell((int) playerPos.x,(int) playerPos.y) != null) {
            //Lose
            tilePlayer.setCell((int) playerPos.x,(int) playerPos.y,playerDead);
        } else if (tileFlag1.getCell((int) playerPos.x,(int) playerPos.y) != null || tileFlag2.getCell((int) playerPos.x,(int) playerPos.y) != null) {
            //Win
            tilePlayer.setCell((int) playerPos.x,(int) playerPos.y,playerWon);
        } else {
            tilePlayer.setCell((int) playerPos.x,(int) playerPos.y,playerCell);
        }
        mapRenderer.render();
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
}