package inf112.skeleton.app.cards;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.maps.tiled.TiledMapTileLayer;
import inf112.skeleton.app.enums.Direction;
import inf112.skeleton.app.objects.Actors.Robot;

public class RotationCard extends ProgramCard {

    int clockwiseTurns; // The amount of times the robot will execute a 45° clockwise turn.

    RotationCard(String name, int priority, Texture texture, int amountOfClockwiseTurns) {
        super(name, priority, texture);
        this.clockwiseTurns = amountOfClockwiseTurns;
    }

    @Override
    public void action(TiledMapTileLayer tilePlayer, Robot robot) {
        robot.rotate(clockwiseTurns);
    }
}
