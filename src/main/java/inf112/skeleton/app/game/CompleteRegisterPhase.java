package inf112.skeleton.app.game;

import com.badlogic.gdx.math.Vector2;
import inf112.skeleton.app.enums.Direction;
import inf112.skeleton.app.enums.Rotation;
import inf112.skeleton.app.objects.Actors.SimpleRobot;
import inf112.skeleton.app.objects.TileObjects.Conveyor;
import inf112.skeleton.app.objects.TileObjects.Gear;
import inf112.skeleton.app.objects.TileObjects.Laser;
import inf112.skeleton.app.objects.TileObjects.Pusher;
import java.util.List;
import static inf112.skeleton.app.game.Game.robots;
import static inf112.skeleton.app.game.Game.gameBoard;

public class CompleteRegisterPhase implements IPhase {

    @Override
    public void run() {
        revealProgramCards();
        executeProgramCards();
        boardElementsMove();
        lasersFire();
    }

    private void revealProgramCards() {

    }

    private void executeProgramCards() {

    }

    private void boardElementsMove() {
        /*
        TODO: Implement player collision, accounting for player moveing, pushers and conveyors. See Rulebook page 5.
             Player collision
                - Collect all moves in a collection
                - Check if two or more robots wants to go to same tile.
                    - These robots do nothing/Stand still.
                - Execute moves for all robots not moving to same tile as another.
         */
        //Check for express conveyor, and move player
        moveConveyor(true);

        //Check for normal conveyor, and move player
        moveConveyor(false);

        //Pushers push if active
        movePusher();

        //Rotate players
        rotatePlayer();
    }


    private void lasersFire() {
        /*
        TODO: Implement laser fire by both Robots and laserWalls.
            - All lasers fire, lasers are only stopped by walls. and robots if hit?
         */
        List<Laser> lasers = gameBoard.getLasers(); //All lasers on board

    }


    /**
     * Moves players, if on conveyor type tile
     * @param isExpress
     */
    private void moveConveyor(Boolean isExpress) {
        for (SimpleRobot robot : robots) {
            Vector2 robotLocation = robot.getPosition();

            if (gameBoard.isPosAConveyor(robotLocation)) { //If robot is on a conveyor
                Conveyor con = (Conveyor) gameBoard.getNonWallTileOnPos(robotLocation);

                //If express, move only express conveyors
                if (isExpress) {
                    if (con.getSpeed() == 2) robot.setPosition(Direction.goDirection(robotLocation, con.getPushDirection()));
                } else {
                    robot.setPosition(Direction.goDirection(robotLocation, con.getPushDirection()));
                }
            }
        }
    }

    /**
     * Pushes players if on a Pusher and Pushers are activated.
     */
    private void movePusher() {
        if (true) { //If pushers are on, move players
            for (SimpleRobot robot : robots) {
                Vector2 robotLocation = robot.getPosition();

                if (gameBoard.isPosAPusher(robotLocation)) { //If position is a conveyor
                    Pusher con = (Pusher) gameBoard.getNonWallTileOnPos(robotLocation);

                    robot.setPosition(Direction.goDirection(robotLocation, con.getPushDirection()));
                }
            }
        }
    }

    /**
     * Rotates players if on Gear tile
     */
    private void rotatePlayer() {
        for (SimpleRobot robot : robots) {
            Vector2 robotLocation = robot.getPosition();

            if (gameBoard.isPosAGear(robotLocation)) { //If position is a conveyor
                Gear con = (Gear) gameBoard.getNonWallTileOnPos(robotLocation);
                Direction robotLook = robot.getLookDirection();

                //Rotate With clock
                if (con.getTurnDirection().equals(Rotation.WITH_CLOCK)) {
                    robot.setLookDirection(Direction.DirectionClockwise(robotLook));
                } else {
                    //Rotate against clock. Equal to rotating with clock and taking the opposite direction from it.
                    robot.setLookDirection(Direction.DirectionOpposite(Direction.DirectionClockwise(robotLook)));
                }
            }
        }
    }


    /*
    CompleteRegister plan:
        - Reveal Program Cards
            - Each player reveals his or her program card for that register at the same time.
        - Execute Program Cards (Robots Move)
            - Move each robot as its program card indicates.
            - Note card priority
            - Resolve interactions -> Hit Wall?, other Player?, touch Flag? Repair Sites.
        - Board Elements Move
            1. Express conveyor belts move 1 space.
            2. All conveyor belts move 1 space.
            3. Pushers push if active.
            4. Gear rotate 90 degrees.
        - Lasers Fire
        - Touch Checkpoints
     */

}
