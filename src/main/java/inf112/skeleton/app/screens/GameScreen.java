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

import inf112.skeleton.app.objects.Actors.Player;



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

    /**
     * Constructor method
     */

    public GameScreen(RoboRally game) {
        this.game = game;
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

        Texture playerTexture = Assets.manager.get(Assets.texture); // Texture of player
        TextureRegion[][] textures = new TextureRegion(playerTexture).split(300, 300);  // Splits player texture into the 3 parts. Live/Dead/Win

        player = new Player(0,0, textures);


    }

    /**
     * Method that performs action when key pressed is released
     * @param keycode: number id of pressed key
     */
    @Override
    public boolean keyUp(int keycode) {
        if (movePlayer) {
            player.movePlayer(tilePlayer, keycode);
            return true;
        }
        moveCamera(keycode);
        return true;
    }

    /**
     * Change direction of an actor, e.g a a place
     * @param playerTile: the tile containing an actor, e.g a player
     * @param position: a 2d position
     * @param keycode: number id of pressed key
     */
    public void changeDirection(TiledMapTileLayer playerTile, Vector2 position, int keycode) {

        playerTile.setCell((int) position.x,(int) position.y, new TiledMapTileLayer.Cell());
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
    public void render(float v) {
        Gdx.gl.glClearColor(1, 1, 1, 1);
        Gdx.gl.glClear(GL30.GL_COLOR_BUFFER_BIT);

        //Win/lose condition
        if (tileHole.getCell((int) player.getPosition().x,(int) player.getPosition().y) != null) {
            //Lose
            tilePlayer.setCell((int) player.getPosition().x,(int) player.getPosition().y,player.getPlayerCellDead());
        } else if (tileFlag1.getCell((int) player.getPosition().x,(int) player.getPosition().y) != null || tileFlag2.getCell((int) player.getPosition().x,(int) player.getPosition().y) != null) {
            //Win
            tilePlayer.setCell((int) player.getPosition().x,(int) player.getPosition().y,player.getPlayerCellWon());
        } else {
            tilePlayer.setCell((int) player.getPosition().x,(int) player.getPosition().y,player.getPlayerCell());
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