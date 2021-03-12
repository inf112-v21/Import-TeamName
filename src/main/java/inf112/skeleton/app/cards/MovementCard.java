package inf112.skeleton.app.cards;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.maps.tiled.TiledMapTileLayer;
import inf112.skeleton.app.enums.Direction;
import inf112.skeleton.app.objects.Actors.SimpleRobot;

import static java.lang.Math.abs;

public class MovementCard extends SimpleProgramCard {

    /**
     * The number of steps the robot will make. (Negative number if Back Up)
     */
    private final int numberOfSteps;

    public MovementCard(String name, int priority, int numberOfSteps) {
        super(name, priority);
        this.numberOfSteps = numberOfSteps;
    }

    /**
     * Moves robot with the amount of steps determined by the field variable numberOfSteps
     * @param robot: Robot which performs a given action
     */
    @Override
    public void action(TiledMapTileLayer tilePlayer, SimpleRobot robot) {
        if (numberOfSteps < 0) {
            Direction lookDirectionRobot = robot.getLookDirection();
            robot.setLookDirection(Direction.DirectionOpposite(lookDirectionRobot)); // Change look direction
        }
        robot.moveRobot(tilePlayer, abs(numberOfSteps));
    }

    public int getNumberOfSteps() {
        return this.numberOfSteps;
    }

}
