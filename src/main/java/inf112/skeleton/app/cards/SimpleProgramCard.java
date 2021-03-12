package inf112.skeleton.app.cards;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.maps.tiled.TiledMapTileLayer;
import inf112.skeleton.app.objects.Actors.SimpleRobot;

/**
 * Abstract simple class.
 */
public abstract class SimpleProgramCard implements IProgramCard {

    private final String name;
    private final int priority;


    SimpleProgramCard(String name, int priority) {
        this.priority =  priority;
        this.name = name;

    }

    public String getName() {
        return this.name;
    }

    public int getPriority() {
        return this.priority;
    }



    public abstract void action(TiledMapTileLayer tilePlayer, SimpleRobot robot);

}
