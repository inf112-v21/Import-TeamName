package inf112.skeleton.app.objects.Actors;

import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Vector2;
import inf112.skeleton.app.map.Board;

public class Player extends Robot {

    public Player(Vector2 pos, TextureRegion [][] texture, Board board) {
            super(pos, texture, board);
    }

}
