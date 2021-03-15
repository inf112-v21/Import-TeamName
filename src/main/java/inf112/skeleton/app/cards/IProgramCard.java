package inf112.skeleton.app.cards;

import com.badlogic.gdx.maps.tiled.TiledMapTileLayer;
import inf112.skeleton.app.objects.Actors.SimpleRobot;

public interface IProgramCard {

    /**
     * @return: The type of the card
     */
    CardType getType();

    /**
     * Perform action
     * @param robot: Robot which performs a given action
     */
    void action(TiledMapTileLayer playerTile, SimpleRobot robot);

    /**
     * @return The priority value of the program card
     */
    int getPriority();




}
