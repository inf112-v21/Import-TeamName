package inf112.skeleton.app.objects;

import inf112.skeleton.app.enums.Direction;

public interface IActor extends IObject {

    Direction getLookDirection();

    void setLookDirection(Direction direction);

}
