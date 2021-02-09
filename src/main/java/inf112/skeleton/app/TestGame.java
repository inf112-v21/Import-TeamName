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

public class TestGame extends InputAdapter implements ApplicationListener  {
    private SpriteBatch batch;
    private BitmapFont font;

    private TiledMap map;
    // Layers on the map
    private TiledMapTileLayer tileBoard;
    private TiledMapTileLayer tilePlayer;
    private TiledMapTileLayer tileHole;
    private TiledMapTileLayer tileFlag;

    private OrthogonalTiledMapRenderer mapRenderer;
    private OrthographicCamera camera;

    private TiledMapTileLayer.Cell playerCell;
    private TiledMapTileLayer.Cell playerDead;
    private TiledMapTileLayer.Cell playerWon;
    private Vector2 playerPos;

    private TiledMapTileLayer.Cell emptyCell;

    @Override
    public void create() {
        batch = new SpriteBatch();
        font = new BitmapFont();
        font.setColor(Color.RED);

        camera = new OrthographicCamera();                             // Make camera
        camera.setToOrtho(false, 5,5);  // Set mode
        camera.update();

        map = new TmxMapLoader().load("Maps/tutorialMapEndre.tmx");       // Get map file
        mapRenderer = new OrthogonalTiledMapRenderer(map,(float) 1/300);  // Render map
        mapRenderer.setView(camera); // Attach camera to map

        tileBoard = (TiledMapTileLayer) map.getLayers().get("Board");
        tilePlayer = (TiledMapTileLayer) map.getLayers().get("Player");
        tileHole = (TiledMapTileLayer) map.getLayers().get("Hole");
        tileFlag = (TiledMapTileLayer) map.getLayers().get("Flag");

        Texture playerTexture = new Texture("Images/player.png"); // Texture of player
        TextureRegion[][] tx = new TextureRegion(playerTexture).split(300, 300);  // Splits player texture into the 3 parts. Live/Dead/win?

        playerCell = new TiledMapTileLayer.Cell().setTile(new StaticTiledMapTile(tx[0][0]));
        playerDead = new TiledMapTileLayer.Cell().setTile(new StaticTiledMapTile(tx[0][1]));
        playerWon = new TiledMapTileLayer.Cell().setTile(new StaticTiledMapTile(tx[0][2]));

        playerPos = new Vector2(0,0);

        emptyCell = new TiledMapTileLayer.Cell(); // Empty cell to fill behind the moving peice. Prevents duplicate textures.
        //UserInputManager uim = new UserInputManager();
        Gdx.input.setInputProcessor(this);
    }

    @Override
    public boolean keyUp(int keycode) {
        tilePlayer.setCell((int) playerPos.x,(int) playerPos.y,emptyCell); // Clear previous owl. To prevent duplicate players.
        if (keycode == Input.Keys.LEFT)  playerPos.x -= 1;
        if (keycode == Input.Keys.RIGHT) playerPos.x += 1;
        if (keycode == Input.Keys.UP) playerPos.y += 1;
        if (keycode == Input.Keys.DOWN) playerPos.y -= 1;

        System.out.println("x:" + playerPos.x + " y:" + playerPos.y);
        return true;
    }

    @Override
    public void dispose() {
        batch.dispose();
        font.dispose();
    }

    private Vector2 prevPos;

    @Override
    public void render() {
        Gdx.gl.glClearColor(1, 1, 1, 1);
        Gdx.gl.glClear(GL30.GL_COLOR_BUFFER_BIT);

        //Win/lose condition
        if (tileHole.getCell((int) playerPos.x,(int) playerPos.y) != null) {
            //Lose
            tilePlayer.setCell((int) playerPos.x,(int) playerPos.y,playerDead);
        } else if (tileFlag.getCell((int) playerPos.x,(int) playerPos.y) != null) {
            //Win
            tilePlayer.setCell((int) playerPos.x,(int) playerPos.y,playerWon);
        } else {
            tilePlayer.setCell((int) playerPos.x,(int) playerPos.y,playerCell);
        }

        mapRenderer.render();

        /*
        batch.begin();
        font.draw(batch, "Hello World", 200, 200);
        batch.end();
         */
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