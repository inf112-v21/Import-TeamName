package inf112.skeleton.app.objects.TileObjects;

import com.badlogic.gdx.math.Vector2;
import inf112.skeleton.app.enums.Direction;
import inf112.skeleton.app.objects.SimpleObject;

/**
 *  Class for conveyors
 */
public class Conveyor extends SimpleObject {

    private final Direction pushDirection;
    private final Integer speed; // Normal push 1 tile, Fast, push 2 tiles in 2 turns
    private final boolean turn;  // If a conveyor should change lookdirection of actor standing on it.

    public Conveyor(Vector2 position, Direction pushDirection, Integer speed, boolean turn){
        super(position);
        this.pushDirection = pushDirection;
        this.speed = speed;
        this.turn = turn;
    }

    public Direction getPushDirection() {
        return this.pushDirection;
    }

    public Integer getSpeed() {
        return this.speed;
    }

    public boolean isTurn() {
        return this.turn;
    }
}
