package inf112.skeleton.app.objects.Actors;

import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.maps.tiled.TiledMapTileLayer;
import com.badlogic.gdx.math.Vector2;
import inf112.skeleton.app.enums.Direction;

import static inf112.skeleton.app.enums.Direction.*;
import static inf112.skeleton.app.game.MainGame.gameBoard;

public class Player extends SimpleRobot {

    public Player(Vector2 pos, TextureRegion [][] texture) {
        super(pos, texture);
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
            if (gameBoard.canGoToTile(pos, NORTH)) {
                //pos.y += 1;
                setPosition(Direction.goDirection(pos, NORTH));
                setLookDirection(NORTH);
                System.out.println(pos);
            }
        }
        if (keycode == Input.Keys.A) {
            if (gameBoard.canGoToTile(pos, Direction.WEST)) {
                //pos.x -= 1;
                setPosition(Direction.goDirection(pos, Direction.WEST));
                setLookDirection(Direction.WEST);
                System.out.println(pos);
            }
        }
        if (keycode == Input.Keys.S) {
            if (gameBoard.canGoToTile(pos, Direction.SOUTH)) {
                //pos.y -= 1;
                setPosition(Direction.goDirection(pos, Direction.SOUTH));
                setLookDirection(SOUTH);
                System.out.println(pos);
            }
        }
        if (keycode == Input.Keys.D) {
            if (gameBoard.canGoToTile(pos, Direction.EAST)) {
                //pos.x += 1;
                setPosition(Direction.goDirection(pos, Direction.EAST));
                setLookDirection(EAST);
                System.out.println(pos);
            }
        }
        checkPosition(this);
    }

}
