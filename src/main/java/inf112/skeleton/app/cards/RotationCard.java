package inf112.skeleton.app.cards;

import com.badlogic.gdx.graphics.Texture;
import inf112.skeleton.app.enums.Direction;
import inf112.skeleton.app.objects.Actors.Robot;

public class RotationCard extends ProgramCard{

    Direction rotation;

    RotationCard(String name, int priority, CardType cardtype, Texture texture, Direction rotation) {
        super(name, priority, cardtype, texture);
        this.rotation = rotation;
    }

    @Override
    public void action(Robot robot) { robot.rotate(rotation);}
}
