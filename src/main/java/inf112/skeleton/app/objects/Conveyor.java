package inf112.skeleton.app.objects;

import com.badlogic.gdx.math.Vector2;
import inf112.skeleton.app.enums.Direction;

public class Conveyor implements IObject {

    private Vector2 position;
    private final Direction pushDirection;
    private final Integer speed; // Normal push 1 tile, Fast, push 2 tiles in 2 turns

    public Conveyor(Vector2 position, Direction pushDirection, Integer speed){
        this.position = position;
        this.pushDirection = pushDirection;
        this.speed = speed;
    }

    @Override
    public Vector2 getPosition() {
        return this.position;
    }

    @Override
    public void setPosition(Vector2 pos) {
        this.position = pos;
    }

    @Override
    public boolean isPassableFromDirection(Direction dir) {
        return true;
    }


    public Direction getPushDirection() {
        return this.pushDirection;
    }

    public Integer getSpeed() {
        return speed;
    }
}
