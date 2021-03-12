package inf112.skeleton.app.screens;

import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import inf112.skeleton.app.objects.Actors.SimpleRobot;

public class CardUI extends Actor {

    private Table table;
    private Stage stage;



    public CardUI(GameScreen screen, SimpleRobot robot) {
        stage = screen.getUIStage();
        this.table = new Table();

    }


}
