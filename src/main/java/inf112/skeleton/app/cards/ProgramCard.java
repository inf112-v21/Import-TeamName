package inf112.skeleton.app.cards;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.maps.tiled.TiledMapTileLayer;
import inf112.skeleton.app.objects.Actors.Robot;

public abstract class ProgramCard implements IProgramCard {

    private final String name;
    private final int priority;
    private final Texture texture;

    ProgramCard(String name, int priority, Texture texture) {
        this.priority =  priority;
        this.name = name;
        this.texture = texture;
    }

    public String getName() {
        return this.name;
    }

    public int getPriority() {
        return this.priority;
    }

    public Texture getTexture() {
        return this.texture;
    }

    public abstract void action(TiledMapTileLayer tilePlayer, Robot robot);
}
