package inf112.skeleton.app.objects.TileObjects;

import com.badlogic.gdx.math.Vector2;
import inf112.skeleton.app.objects.SimpleObject;

public class RepairSite extends SimpleObject {

    //Change name maybe? Can be 1 or 2. Single or Double.
    private int strength;

    public RepairSite(Vector2 position, int strength) {
        super(position);
        this.strength = strength;
    }

    public int getStrength() {
        return strength;
    }
}
