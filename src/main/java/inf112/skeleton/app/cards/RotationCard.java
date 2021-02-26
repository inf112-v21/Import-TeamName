package inf112.skeleton.app.cards;

import com.badlogic.gdx.graphics.Texture;
import inf112.skeleton.app.enums.Direction;
import inf112.skeleton.app.objects.Actors.Robot;

public class RotationCard extends ProgramCard{

    Direction rotation;
    boolean uTurn;

    RotationCard(String name, int priority, CardType cardtype, Texture texture, Direction rotation, boolean uTurn) {
        super(name, priority, cardtype, texture);
        this.uTurn = uTurn;
        this.rotation = rotation;
    }

    @Override
    public void action(Robot robot) {
        if (uTurn) { robot.setLookDirection(Direction.DirectionOpposite(robot.getLookDirection())); } // Set look direction to the opposite direction
        robot.rotate(rotation);
    }
}
