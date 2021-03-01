package inf112.skeleton.app.cards;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.maps.tiled.TiledMapTileLayer;
import inf112.skeleton.app.enums.Direction;
import inf112.skeleton.app.objects.Actors.Robot;

public class MovementCard extends ProgramCard {

    int numberOfSteps;
    boolean backUpCard;
    public MovementCard(String name, int priority, CardType cardtype, Texture texture, int numberOfSteps, boolean backUpCard) {
        super(name, priority, cardtype, texture);
        this.backUpCard = backUpCard;
        this.numberOfSteps = numberOfSteps;
    }




    /**
     * Moves robot with the amount of steps determined by the field variable numberOfSteps
     * @param robot: Robot which performs a given action
     */
    @Override
    public void action(TiledMapTileLayer tilePlayer, Robot robot) {
            if (backUpCard) {
                Direction lookDirectionRobot= robot.getLookDirection();
                robot.setLookDirection(Direction.DirectionOpposite(lookDirectionRobot)); // Change look direction
            }
        robot.moveRobot(tilePlayer, numberOfSteps);
    }


    public int getNumberOfSteps() {return getNumberOfSteps();}

}
