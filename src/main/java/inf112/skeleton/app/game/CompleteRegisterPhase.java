package inf112.skeleton.app.game;

import com.badlogic.gdx.math.Vector2;
import inf112.skeleton.app.cards.SimpleProgramCard;
import inf112.skeleton.app.enums.Direction;
import inf112.skeleton.app.enums.Rotation;
import inf112.skeleton.app.objects.Actors.Player;
import inf112.skeleton.app.objects.Actors.SimpleRobot;
import inf112.skeleton.app.objects.TileObjects.*;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import static inf112.skeleton.app.game.MainGame.*;

public class CompleteRegisterPhase implements IPhase {

    @Override
    public void run() {
        revealProgramCards();
        executeProgramCards();
        boardElementsMove();
        lasersFire();

        updateCheckPoints(); //Checks if robot is on flag
    }

    protected void revealProgramCards() {
        //TODO: Is this 'nice to have'? Maybe just print to console.
    }

    /**
     * Executes all programcards the robots have in their register.
     * Accounts for card priority. This has not been tested! -Endre
     *
     * Robots MUST HAVE 5 cards in their register! This will not work without.
     *
     * A *very* simple implementation
     *      - Does not account for player collision.
     */
    protected void executeProgramCards() {
        System.out.println("executeProgramCards is running \n");

        for (int i = 0; i < 5; i++) { //Loop through all 5 cards in register.
            List<SimpleProgramCard> moves = new ArrayList<>(); //List with one from each player

            //Get 1 card from each player/robot
            for (SimpleRobot robot : robots) {
                if (robot.getProgramSheet().getRegister().getRegisterCards().size() < 5) throw new IllegalArgumentException("Robot had less than 5 cards in their register! When calling CompleteRegisterPhase they must have 5 or more!");

                SimpleProgramCard card = robot.getProgramSheet().getRegister().getRegisterCards().get(i); //Get card from robot.
                moves.add(card); //A Robot should Always have 5 cards, rulebook p. 10 -> Locking register.
            }

            Collections.sort(moves, SimpleProgramCard::compareTo); //Sorts cards based on priority

            for (SimpleProgramCard card : moves) {
                // Do the move on the correct player
                for (SimpleRobot robot : robots) {
                    SimpleProgramCard robotCard = robot.getProgramSheet().getRegister().getRegisterCards().get(i); //Get card from robot.
                    if (robotCard.equals(card)) {
                        System.out.println("Robot " + robot + " used " + card + " card.");
                        card.action(robot);
                    }
                }
            }
        }
    }

    /**
     * Debug test method perform all the selected cards of a predefined player
     * @param player: test player whose cards will be executed
     */
    public void executePlayerProgramCards(Player player) {
        System.out.println("executeProgramCards is running in debug mode for one single player \n");
        ArrayList<SimpleProgramCard> playerCards = player.getProgramSheet().getRegister().getRegisterCards();
        for (int i = 0; i < 5; i++) { //Loop through all 5 cards in register.
            List<SimpleProgramCard> moves = new ArrayList<>(); //List with one from each player
            //Get 1 card from each player/robot
            if (player.getProgramSheet().getRegister().getRegisterCards().size() < 5) throw new IllegalArgumentException("Robot had less than 5 cards in their register! When calling CompleteRegisterPhase they must have 5 or more!");
            SimpleProgramCard playerCard = player.getProgramSheet().getRegister().getRegisterCards().get(i); //Get card from robot.
            moves.add(playerCard); //A Robot should Always have 5 cards, rulebook p. 10 -> Locking register.
            Collections.sort(moves, SimpleProgramCard::compareTo); //Sorts cards based on priority
            for (SimpleProgramCard card : moves) {
                // Do the move on the correct player
                SimpleProgramCard robotCard = player.getProgramSheet().getRegister().getRegisterCards().get(i); //Get card from robot.
                if (robotCard.equals(card)) {
                    card.action(player);
                    System.out.println("DEBUG:  Player"  + " used " + card + " card.");
                }
            }
        }
    }


    /**
     * Moves all conveyors, pushers if activated and rotates robots on Gear tiles.
     */
    protected void boardElementsMove() {
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
    protected void lasersFire() {
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
        if (gameBoard.isOnBoard(currentPosOfLaser)) return; //Laser is outside board.

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
    protected void moveConveyor(Boolean isExpress) {
        for (SimpleRobot robot : robots) {
            Vector2 robotLocation = robot.getPosition();

            if (gameBoard.isPosAConveyor(robotLocation)) { //If robot is on a conveyor
                Conveyor con = (Conveyor) gameBoard.getNonWallTileOnPos(robotLocation);

                //Conveyor cannot push robots through walls.
                if (!gameBoard.canGoToTile(robotLocation,con.getPushDirection())) continue;

                //If express, move only express conveyors
                if (isExpress) {
                    if (con.getSpeed() == 2) robot.setPosition(Direction.goDirection(robotLocation, con.getPushDirection()));
                } else {
                    if (con.getSpeed() == 1) robot.setPosition(Direction.goDirection(robotLocation, con.getPushDirection()));
                }
            }
        }
    }

    /**
     * Pushes players if on a Pusher and Pushers are activated.
     */
    protected void movePusher() {
        if (true) { //If pushers are on, move players
            for (SimpleRobot robot : robots) {
                Vector2 robotLocation = robot.getPosition();

                if (gameBoard.isPosAPusher(robotLocation)) { //If position is a conveyor
                    Pusher push = (Pusher) gameBoard.getWallTileOnPos(robotLocation);

                    //Pusher cannot push robots through walls.
                    if (!gameBoard.canGoToTile(robotLocation,push.getPushDirection())) continue;

                    robot.setPosition(Direction.goDirection(robotLocation, push.getPushDirection()));
                }
            }
        }
    }

    /**
     * Rotates players if on Gear tile
     */
    protected void rotatePlayer() {
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

    /**
     * Checks if robots are standing on a flag at the end of the phase.
     * If yes, adds flag to that robots programsheet.
     */
    protected void updateCheckPoints() {
        for (SimpleRobot robot : robots) {
            if (gameBoard.isPosAFlag(robot.getPosition())) {
                Flag flag = (Flag) gameBoard.getNonWallTileOnPos(robot.getPosition());
                robot.getProgramSheet().addFlag(flag);
            }
        }
    }

}