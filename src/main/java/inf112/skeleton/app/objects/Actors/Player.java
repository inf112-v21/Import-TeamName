package inf112.skeleton.app.objects.Actors;

import com.badlogic.gdx.graphics.g2d.TextureRegion;
import inf112.skeleton.app.map.Board;

public class Player extends Robot {

    public Player(int startRow, int startCol, TextureRegion [][] texture, Board board) {
            super(startRow, startCol, texture, board);
    }



}
