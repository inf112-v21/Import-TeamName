package inf112.skeleton.app.cards;

import inf112.skeleton.app.objects.Actors.Robot;

public class RotationCard extends SimpleProgramCard {

    /**
     * The amount of times the robot will execute a 90° clockwise turn.
     */
    private final int clockwiseTurns;
    private CardType cardType;

    public RotationCard(int priority, CardType cardType) {
        super(priority, cardType);
        this.cardType = cardType;
        this.clockwiseTurns = clockwiseTurns();
    }

    private int clockwiseTurns() {
        switch(cardType) {
            case ROTATERIGHT: return 1;
            case UTURN: return 2;
            case ROTATELEFT: return 3;
            default: throw new IllegalArgumentException("Expected rotation cards got, " + cardType);
        }
    }


    @Override
    public void action(Robot robot) {
        robot.rotate(clockwiseTurns);
    }

}
