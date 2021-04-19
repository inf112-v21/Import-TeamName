package inf112.skeleton.app.objects.TileObjects;

import com.badlogic.gdx.math.Vector2;
import inf112.skeleton.app.cards.SimpleProgramCard;
import inf112.skeleton.app.objects.SimpleObject;

/**
 *  Class for the staring point of robots.
 */
public class DockingBay extends SimpleObject {

    int priority;

    public DockingBay(Vector2 position, int priority) {
        super(position);
        this.priority = priority;
    }


    /**
     * Sorts dockingbays based on their priority.
     * @param otherDockingbay
     * @return
     */
    public int compareTo(DockingBay otherDockingbay) {
        return Integer.compare(this.priority, otherDockingbay.priority);
    }
}
