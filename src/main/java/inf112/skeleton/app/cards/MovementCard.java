package inf112.skeleton.app.cards;

import inf112.skeleton.app.enums.Direction;
import inf112.skeleton.app.objects.Actors.SimpleRobot;

import static java.lang.Math.abs;

public class MovementCard extends SimpleProgramCard {

    /**
     * The number of steps the robot will make. (Negative number if Back Up)
     */
    private final int numberOfSteps;
    private CardType cardType;

    public MovementCard(int priority, CardType cardType) {
        super(priority, cardType);
        this.cardType = cardType;
        this.numberOfSteps = numberOfSteps();


    }

    private int numberOfSteps() {
        switch(cardType) {
            case MOVE1: return 1;
            case MOVE2: return 2;
            case MOVE3: return 3;
            case BACK1: return -1;
            default: throw new IllegalArgumentException("Expected movement types not, " + cardType);
        }
    }

    /**
     * Moves robot with the amount of steps determined by the field variable numberOfSteps
     * @param robot : Robot which performs a given action
     */
    @Override
    public void action(SimpleRobot robot) {
        if (numberOfSteps < 0) {
            Direction lookDirectionRobot = robot.getLookDirection();
            robot.setLookDirection(Direction.DirectionOpposite(lookDirectionRobot)); // Change look direction
        }
        robot.moveRobot(abs(numberOfSteps));
    }

    public int getNumberOfSteps() {
        return this.numberOfSteps;
    }

}
