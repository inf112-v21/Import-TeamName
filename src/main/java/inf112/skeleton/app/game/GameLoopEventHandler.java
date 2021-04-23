package inf112.skeleton.app.game;

import com.badlogic.gdx.maps.tiled.TiledMapTileLayer;
import com.badlogic.gdx.math.Vector2;
import inf112.skeleton.app.RoboRally;
import inf112.skeleton.app.objects.Actors.Robot;

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
        for (Robot robot : robots) {
            if (robot.getProgramSheet().isDead()) robot.setPosition(new Vector2(-10,-10)); //Dead players are off the board.
            tilePlayer.setCell((int) robot.getPosition().x, (int) robot.getPosition().y, robot.getPlayerCell());
        }
    }


    /**
     * If robot on pit, mark as dead.
     */
    private void checkForDeath() {
        for (Robot robot : robots) {
            Vector2 position = robot.getPosition();

            if (gameBoard.isOnBoard(position) || gameBoard.isPosAPit(position)) {
                //robot.robotLoseLife(robot);
                tilePlayer.setCell((int) robot.getPosition().x, (int) robot.getPosition().y, robot.getPlayerCellDead());

            }

            if (robot.getProgramSheet().getLife()>1 && gameBoard.isOnBoard(position)){
                robot.robotLoseLife(robot);
            }
        }
    }

    /**
     * If robot at flag, change to win texture
     */
    private void checkForFlag() {
        for (Robot robot : robots) {
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
        // If a robot has collected all flags in correct order.
        for (Robot robot : robots) {
            int visitedFlags = robot.getProgramSheet().getNumberOfFlags();
            if (visitedFlags == gameBoard.getNrFlags()) {
                switcher.setWinScreen();
            }
        }
    }


}
