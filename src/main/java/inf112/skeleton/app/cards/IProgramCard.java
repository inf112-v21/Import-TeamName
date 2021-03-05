package inf112.skeleton.app.cards;

import com.badlogic.gdx.maps.tiled.TiledMapTileLayer;
import inf112.skeleton.app.objects.Actors.Robot;

public interface IProgramCard {


    /**
     * @return: The name of the program card
     */
    String getName();

    /**
     * Perform action
     * @param robot: Robot which performs a given action
     */
    void action(TiledMapTileLayer playerTile, Robot robot);

    /**
     * @return The priority value of the program card
     */
    int getPriority();

}
