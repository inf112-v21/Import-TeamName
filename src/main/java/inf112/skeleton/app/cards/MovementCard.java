package inf112.skeleton.app.cards;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.maps.tiled.TiledMapTileLayer;
import inf112.skeleton.app.enums.Direction;
import inf112.skeleton.app.objects.Actors.Robot;

import static java.lang.Math.abs;

public class MovementCard extends ProgramCard {

    /**
     * The number of steps the robot will make. (-1 if Back Up)
     */
    private final int numberOfSteps;

    public MovementCard(String name, int priority, CardType cardtype, Texture texture, int numberOfSteps) {
        super(name, priority, cardtype, texture);
        this.numberOfSteps = numberOfSteps;
    }

    /**
     * Moves robot with the amount of steps determined by the field variable numberOfSteps
     * @param robot: Robot which performs a given action
     */
    @Override
    public void action(TiledMapTileLayer tilePlayer, Robot robot) {
        if (numberOfSteps < 0) {
            Direction lookDirectionRobot = robot.getLookDirection();
            robot.setLookDirection(Direction.DirectionOpposite(lookDirectionRobot)); // Change look direction
        }
        robot.moveRobot(tilePlayer, abs(numberOfSteps)); //TODO: Support negative integers.
    }


    public int getNumberOfSteps() {
        return this.numberOfSteps;
    }

}
