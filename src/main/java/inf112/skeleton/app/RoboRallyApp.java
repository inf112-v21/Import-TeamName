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
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.maps.tiled.TiledMap;
import com.badlogic.gdx.maps.tiled.TiledMapRenderer;
import com.badlogic.gdx.maps.tiled.TiledMapTileLayer;
import com.badlogic.gdx.maps.tiled.TmxMapLoader;
import com.badlogic.gdx.maps.tiled.renderers.OrthogonalTiledMapRenderer;
import com.badlogic.gdx.maps.tiled.tiles.StaticTiledMapTile;
import com.badlogic.gdx.math.Vector2;

public class RoboRallyApp extends ApplicationAdapter implements InputProcessor {

    private SpriteBatch batch;
    private BitmapFont font;

    private TiledMapTileLayer boardTile, holeTile, flagTile, playerTile;
    private TiledMap tiledMap;
    private OrthographicCamera camera;
    private TiledMapRenderer tiledMapRenderer;

    // PLAYER
    private TiledMapTileLayer.Cell playerDied, player, playerWon;
    private Vector2 playerPos;
    private Texture playerTexture;
    private TextureRegion[][] playerTextureRegion;

    boolean movePlayer = true;


    @Override
    public void create() {
        float w = Gdx.graphics.getWidth();
        float h = Gdx.graphics.getHeight();
        camera = new OrthographicCamera();
        camera.setToOrtho(false, w, h);
        camera.update();
        tiledMap = new TmxMapLoader().load("Maps/tutorialMap.tmx");
        tiledMapRenderer = new OrthogonalTiledMapRenderer(tiledMap);
        boardTile = (TiledMapTileLayer) tiledMap.getLayers().get("Board");
        holeTile = (TiledMapTileLayer) tiledMap.getLayers().get("Hole");
        flagTile = (TiledMapTileLayer) tiledMap.getLayers().get("Flag");
        playerTile = (TiledMapTileLayer) tiledMap.getLayers().get("Player");


        //PLAYER
        playerTexture = new Texture("Images/player.png");
        playerTextureRegion = TextureRegion.split(playerTexture, 300, 300); // Split tile
        player = new TiledMapTileLayer.Cell();
        playerDied = new TiledMapTileLayer.Cell();
        playerWon  = new TiledMapTileLayer.Cell();
        player.setTile(new StaticTiledMapTile(playerTextureRegion[0][0]));
        playerDied.setTile(new StaticTiledMapTile(playerTextureRegion[0][1]));
        playerWon.setTile(new StaticTiledMapTile(playerTextureRegion[0][2]));
        playerPos = new Vector2(1,2);

        Gdx.input.setInputProcessor(this); // Set inputprocessor
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
        playerTile.setCell(2,2, playerDied);
        playerTile.setCell(2,3, playerWon);
        winOrLose();
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
        if (movePlayer) {
            changeDirection(playerTile, playerPos, keycode);
            return true;
        }
        moveCamera(keycode);
        return true;
    }

    public void changeDirection(TiledMapTileLayer actor, Vector2 position, int keycode) {
        /**
         * Change direction of an actor, e.g a a place
         */
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
            tiledMap.getLayers().get(0).setVisible(!tiledMap.getLayers().get(0).isVisible());
        if(keycode == Input.Keys.NUM_2)
            tiledMap.getLayers().get(1).setVisible(!tiledMap.getLayers().get(1).isVisible());

    }

    public void winOrLose() {
        /**
         * Has the player won or lost. Render calls function
         */
        if (holeTile.getCell((int) playerPos.x,(int) playerPos.y) != null) playerTile.setCell((int) playerPos.x,(int) playerPos.y, playerDied);
        else if (flagTile.getCell((int) playerPos.x,(int) playerPos.y) != null) playerTile.setCell((int) playerPos.x,(int) playerPos.y,playerWon);
        else playerTile.setCell((int) playerPos.x,(int) playerPos.y,player);

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
