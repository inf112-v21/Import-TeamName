package inf112.skeleton.app.game;

import com.badlogic.gdx.maps.tiled.TiledMapTileLayer;
import com.badlogic.gdx.math.Vector2;
import inf112.skeleton.app.RoboRally;
import inf112.skeleton.app.objects.Actors.SimpleRobot;

import static inf112.skeleton.app.game.MainGame.gameBoard;
import static inf112.skeleton.app.game.MainGame.robots;

/**
 *  This class is called from render() in Gamescreen.java.
 *  Re-Draws players at their new position.
 *
 *  Currently used for testing.
 */
public class GameLoopEventHandler {

    private TiledMapTileLayer tilePlayer;
    private RoboRally switcher;

    public GameLoopEventHandler(RoboRally switcher, TiledMapTileLayer tilePlayer) {
        this.switcher = switcher;
        this.tilePlayer = tilePlayer;
    }

    public void run() {
        renderPlayersOnBoard();
        checkForFlag();
        checkForDeath();
        checkForWin();
    }

    /**
     * Draws all robots at their new position
     */
    private void renderPlayersOnBoard() {
        //Render all robot on the board, at their new position.
        for (SimpleRobot robot : robots) {
            if (robot.getProgramSheet().isDead()) robot.setPosition(new Vector2(-10,-10)); //Dead players are off the board. TODO: Maybe handle dead players another way -Endre
            tilePlayer.setCell((int) robot.getPosition().x, (int) robot.getPosition().y, robot.getPlayerCell());
        }
    }


    /**
     * If robot on pit, mark as dead.
     */
    private void checkForDeath() {
        for (SimpleRobot robot : robots) {
            Vector2 position = robot.getPosition();

            if (gameBoard.isPosAPit(position)) {
                tilePlayer.setCell((int) robot.getPosition().x, (int) robot.getPosition().y, robot.getPlayerCellDead());
            }
        }
    }

    /**
     * If robot at flag, change to win texture
     */
    private void checkForFlag() {
        for (SimpleRobot robot : robots) {
            Vector2 position = robot.getPosition();

            if (gameBoard.isPosAFlag(position)) {
                tilePlayer.setCell((int) robot.getPosition().x, (int) robot.getPosition().y, robot.getPlayerCellWon());
            }
        }
    }

    /**
     * Testing
     */
    private void checkForWin() {
        for (SimpleRobot robot : robots) {
            int visitedFlags = robot.getProgramSheet().getNumberOfFlags();
            if (visitedFlags == gameBoard.getNrFlags()) {
                switcher.setWinScreen();
            }
        }
    }


}
