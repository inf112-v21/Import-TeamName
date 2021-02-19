package inf112.skeleton.app.objects;

import inf112.skeleton.app.enums.Direction;

public interface Actor extends Object {

    Direction getLookDirection();

    void setLookDirection(Direction direction);

}
