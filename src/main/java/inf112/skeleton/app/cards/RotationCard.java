package inf112.skeleton.app.cards;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.maps.tiled.TiledMapTileLayer;
import inf112.skeleton.app.objects.Actors.SimpleRobot;

public class RotationCard extends SimpleProgramCard {

    /**
     * The amount of times the robot will execute a 45° clockwise turn.
     */
    int clockwiseTurns;

    public RotationCard(String name, int priority, Texture texture, int amountOfClockwiseTurns) {
        super(name, priority, texture);
        this.clockwiseTurns = amountOfClockwiseTurns;
    }

    @Override
    public void action(TiledMapTileLayer tilePlayer, SimpleRobot robot) {
        robot.rotate(clockwiseTurns);
    }

}
