package inf112.skeleton.app;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.InputProcessor;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.maps.tiled.TiledMap;
import com.badlogic.gdx.maps.tiled.TiledMapRenderer;
import com.badlogic.gdx.maps.tiled.TiledMapTileLayer;
import com.badlogic.gdx.maps.tiled.TmxMapLoader;
import com.badlogic.gdx.maps.tiled.renderers.OrthogonalTiledMapRenderer;
import com.badlogic.gdx.maps.tiled.tiles.StaticTiledMapTile;
import com.badlogic.gdx.math.Vector2;

public class HelloWorld extends ApplicationAdapter implements InputProcessor {

    private SpriteBatch batch;
    private BitmapFont font;
    private Sprite sprite;
    private TiledMapTileLayer boardTile, holeTile, flagTile, playerTile;
    private TiledMap tiledMap;
    private OrthographicCamera camera;
    private TiledMapRenderer tiledMapRenderer;

    // PLAYER
    private TiledMapTileLayer.Cell playerDiedCell, player, playerWon;
    private Vector2 playerPos;
    private Texture playerTexture;
    private TextureRegion[][] playerTextureRegion;


    @Override
    public void create() {
        float w = Gdx.graphics.getWidth();
        float h = Gdx.graphics.getHeight();
        camera = new OrthographicCamera();
        camera.setToOrtho(false, w, h);
        camera.update();
        tiledMap = new TmxMapLoader().load("map.tmx");
        tiledMapRenderer = new OrthogonalTiledMapRenderer(tiledMap);
        boardTile = (TiledMapTileLayer) tiledMap.getLayers().get("Board");
        holeTile = (TiledMapTileLayer) tiledMap.getLayers().get("Hole");
        flagTile = (TiledMapTileLayer) tiledMap.getLayers().get("Flag");
        playerTile = (TiledMapTileLayer) tiledMap.getLayers().get("Player");
        Gdx.input.setInputProcessor(this);

        //PLAYER
        playerTexture = new Texture("player.png");
        playerTextureRegion = TextureRegion.split(playerTexture, 300, 300); // Split tile
        player = new TiledMapTileLayer.Cell();
        playerDiedCell = new TiledMapTileLayer.Cell();
        playerWon  = new TiledMapTileLayer.Cell();
        player.setTile(new StaticTiledMapTile(playerTextureRegion[0][0])); // Set textures
        playerDiedCell.setTile(new StaticTiledMapTile(playerTextureRegion[0][1])); // Set textures
        playerWon.setTile(new StaticTiledMapTile(playerTextureRegion[0][2])); // Set textures
        playerPos = new Vector2(1,2); // Set position
    }

    @Override
    public void render() {

        batch = new SpriteBatch();
        font = new BitmapFont();
        font.setColor(Color.BLACK);
        Gdx.gl.glClearColor(1, 0, 0, 1);
        Gdx.gl.glBlendFunc(GL20.GL_SRC_ALPHA, GL20.GL_ONE_MINUS_SRC_ALPHA);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
        camera.update();
        tiledMapRenderer.setView(camera);
        playerTile.setCell((int) playerPos.x, (int) playerPos.y, player);
        playerTile.setCell(2,2, playerDiedCell);
        tiledMapRenderer.render();

    }

    @Override
    public void dispose() {
        batch.dispose();
        font.dispose();
    }


    @Override
    public boolean keyDown(int i) {
        return false;
    }


    @Override
    public boolean keyUp(int keycode) {
        if(keycode == Input.Keys.LEFT)
            camera.translate(-32,0);
        if(keycode == Input.Keys.RIGHT)
            camera.translate(32,0);
        if(keycode == Input.Keys.UP)
            camera.translate(0,32);
        if(keycode == Input.Keys.DOWN)
            camera.translate(0,-32);
        if(keycode == Input.Keys.NUM_1)
            tiledMap.getLayers().get(0).setVisible(!tiledMap.getLayers().get(0).isVisible());
        if(keycode == Input.Keys.NUM_2)
            tiledMap.getLayers().get(1).setVisible(!tiledMap.getLayers().get(1).isVisible());
        return false;
    }

    @Override
    public boolean keyTyped(char c) {
        return false;
    }

    @Override
    public boolean touchDown(int i, int i1, int i2, int i3) {
        return false;
    }

    @Override
    public boolean touchUp(int i, int i1, int i2, int i3) {
        return false;
    }

    @Override
    public boolean touchDragged(int i, int i1, int i2) {
        return false;
    }

    @Override
    public boolean mouseMoved(int i, int i1) {
        return false;
    }

    @Override
    public boolean scrolled(int i) {
        return false;
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