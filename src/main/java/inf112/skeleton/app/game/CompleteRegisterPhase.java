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
import static inf112.skeleton.app.game.MainGame.robots;
import static inf112.skeleton.app.game.MainGame.gameBoard;

public class CompleteRegisterPhase implements IPhase {

    @Override
    public void run() {
        revealProgramCards();
        executeProgramCards();
        boardElementsMove();
        lasersFire();
    }

    private void revealProgramCards() {
        //TODO: Is this 'nice to have'? Maybe just print to console.
    }

    private void executeProgramCards() {

    }

    /**
     * Moves all conveyors, pushers if activated and rotates robots on Gear tiles.
     */
    private void boardElementsMove() {
        /*
        TODO: Implement player collision, accounting for player moving, pushers and conveyors. See Rulebook page 5.
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


    /**
     * The phase activating all lasers. Wall mounted and robots.
     */
    private void lasersFire() {
        List<Laser> lasers = gameBoard.getLasers(); //All lasers on board

        //Fire lasers for all wall mounted lasers.
        for (Laser laser : lasers) {
            fireLaser(laser.getPosition(), laser.getDirection(), laser.getStrength());
        }

        //Fire laser for robots in their lookDirection
        for (SimpleRobot robot : robots) {
            Vector2 nextTile = Direction.goDirection(robot.getPosition(),robot.getLookDirection()); //Fire from next tile. Prevents robot hitting itself.
            fireLaser(nextTile, robot.getLookDirection(), 1);
        }

    }

    /**
     * Recursively simulates the laser moving in firing direction, stopping only if hitting wall, robot, or is out of bounds.
     * @param currentPosOfLaser
     * @param fireDirection
     * @param nrOfLasers, differentiate single and double lasers.
     */
    private void fireLaser(Vector2 currentPosOfLaser, Direction fireDirection, int nrOfLasers) {
        //If out of bounds.
        if (!gameBoard.isOnBoard(currentPosOfLaser)) return; //Laser is outside board.

        //Check if robot is on laser. If so -> Damage robot.
        for (SimpleRobot robot : robots) {
            if (robot.getPosition().equals(currentPosOfLaser)) {
                //Robot is hit by laser. Take damage
                robot.getProgramSheet().addDamage(nrOfLasers); //TODO: Implement how much damage robot should take based on rules.
                return; //Robot is hit, stop lasers.
            }
        }

        //Wall collision. If cannot leave current tile, or cannot go to next tile, return.
        if (!gameBoard.canGoToTile(currentPosOfLaser, fireDirection)) return;
        fireLaser(Direction.goDirection(currentPosOfLaser, fireDirection), fireDirection, nrOfLasers); //Go to next tile
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
