package inf112.skeleton.app.objects.Actors;

import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.maps.tiled.TiledMapTileLayer;
import com.badlogic.gdx.math.Vector2;

import static inf112.skeleton.app.enums.Direction.*;
import static inf112.skeleton.app.game.MainGame.gameBoard;

public class Player extends SimpleRobot {

    public Player(Vector2 pos, TextureRegion[][] texture, String playerName) {
        super(pos, texture, "1");
    }

    /**
     * Move forward using WASD keys
     *
     * METHOD MEANT FOR TESTING
     * @param keycode
     */
    public void moveRobotWASD(int keycode) {
        Vector2 pos = getPosition();
        TiledMapTileLayer playerTile = (TiledMapTileLayer) gameBoard.getMap().getLayers().get("Player");
        playerTile.setCell((int) pos.x, (int) pos.y, new TiledMapTileLayer.Cell()); // Clear previous robot image

        if (keycode == Input.Keys.W) {
            setLookDirection(NORTH);
            moveRobot(1);
        }
        if (keycode == Input.Keys.A) {
            setLookDirection(WEST);
            moveRobot(1);
        }
        if (keycode == Input.Keys.S) {
            setLookDirection(SOUTH);
            moveRobot(1);
        }
        if (keycode == Input.Keys.D) {
            setLookDirection(EAST);
            moveRobot(1);
        }
        checkPosition(this);
    }

}
