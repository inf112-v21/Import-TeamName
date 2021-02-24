package inf112.skeleton.app.cards;

import com.badlogic.gdx.graphics.Texture;
import inf112.skeleton.app.objects.Actors.Robot;

public class MovementCard extends ProgramCard {


    MovementCard(String name, int priority, CardType cardtype, Texture texture) {
        super(name, priority, cardtype, texture);
    }

    @Override
    public void action(Robot robot) {

    }
}
