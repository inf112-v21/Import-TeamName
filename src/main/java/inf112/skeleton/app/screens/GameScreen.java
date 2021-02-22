package inf112.skeleton.app.screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.InputAdapter;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.maps.tiled.TiledMapTileLayer;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.utils.viewport.ScreenViewport;
import inf112.skeleton.app.GameWithScreens;
import inf112.skeleton.app.RoboRally;

public class GameScreen extends InputAdapter implements Screen {
    GameWithScreens game;
    Stage stage;
    RoboRally roboRally;

    public GameScreen(final GameWithScreens game) {

        this.game = game;
        // create the camera and the SpriteBatch


    }
    @Override
    public void show() {
        System.out.println("Gamescren");
        stage = new Stage(new ScreenViewport());
    }

    @Override
    public void render(float v) {
        System.out.println("Switched");
        Gdx.gl.glClearColor(1, 1, 1, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);



        Gdx.input.setInputProcessor(this);

    }
    /**
     * Method that performs action when key pressed is released
     * @param keycode: number id of pressed key
     */
    @Override
    public boolean keyUp(int keycode) {
        if (roboRally.movePlayer) {
            roboRally.player.movePlayer(roboRally.tilePlayer, keycode);
            return true;
        }
        roboRally.moveCamera(keycode);
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
    @Override
    public void resize(int i, int i1) {

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

    @Override
    public void dispose() {
        stage.dispose();
    }
}
