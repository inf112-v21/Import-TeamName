package inf112.skeleton.app.objects.Actors;

import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Vector2;
import inf112.skeleton.app.enums.Direction;
import inf112.skeleton.app.map.Board;

public class AI extends Robot {


    public AI(Vector2 startPos, TextureRegion[][] texture, Board board) {
        super(startPos, texture, board);
    }

}
