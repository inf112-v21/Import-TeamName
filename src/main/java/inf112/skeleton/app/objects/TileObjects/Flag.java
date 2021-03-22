package inf112.skeleton.app.objects.TileObjects;

import com.badlogic.gdx.math.Vector2;
import inf112.skeleton.app.objects.SimpleObject;

public class Flag extends SimpleObject {

    private int flagID;

    public Flag(Vector2 position, int flagID) {
        super(position);
        this.flagID = flagID;
    }

    public int getFlagID() {
        return flagID;
    }
}
