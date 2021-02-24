package inf112.skeleton.app.objects.Actors;

import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Vector2;
import inf112.skeleton.app.enums.Direction;
import inf112.skeleton.app.map.Board;

public class AI extends Robot {


    public AI(int startRow, int startCol, TextureRegion[][] texture, Board board) {
        super(startRow, startCol, texture, board);
    }

}
