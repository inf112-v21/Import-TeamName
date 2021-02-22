package inf112.skeleton.app.objects.TileObjects;

import com.badlogic.gdx.math.Vector2;
import inf112.skeleton.app.enums.Direction;
import inf112.skeleton.app.objects.Object;

/**
 *  Class for conveyors
 */
public class Conveyor extends Object {

    private final Direction pushDirection;
    private final Integer speed; // Normal push 1 tile, Fast, push 2 tiles in 2 turns

    public Conveyor(Vector2 position, Direction pushDirection, Integer speed){
        super(position);
        this.pushDirection = pushDirection;
        this.speed = speed;
    }

    public Direction getPushDirection() {
        return this.pushDirection;
    }

    public Integer getSpeed() {
        return speed;
    }
}
