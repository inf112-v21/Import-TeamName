package inf112.skeleton.app.objects.Actors;

import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Vector2;
import inf112.skeleton.app.game.MainGame;
import inf112.skeleton.app.map.Board;

public class Player extends SimpleRobot {

    public Player(Vector2 pos, TextureRegion [][] texture, MainGame game) {
            super(pos, texture, game);
    }

}
