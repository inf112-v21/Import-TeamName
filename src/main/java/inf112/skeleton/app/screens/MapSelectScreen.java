package inf112.skeleton.app.screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.maps.tiled.TiledMap;
import com.badlogic.gdx.maps.tiled.TmxMapLoader;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.*;
import com.badlogic.gdx.scenes.scene2d.utils.ChangeListener;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.badlogic.gdx.utils.viewport.FitViewport;
import com.badlogic.gdx.utils.viewport.StretchViewport;
import inf112.skeleton.app.RoboRally;
import inf112.skeleton.app.game.MainGame;
import inf112.skeleton.app.map.Board;

import static com.badlogic.gdx.Gdx.gl;

public class MapSelectScreen implements Screen {

    final RoboRally switcher;

    private Stage stage;
    private Skin skin;

    private TiledMap map;

    float width;
    float height;
    int count = 1;

    String mapPath;
    FitViewport viewPort;
    MainGame mainGame;

    private TextField name1;
    private TextField name2;
    private TextField name3;
    private TextField name4;
    private TextField name5;
    private TextField name6;
    private TextField name7;
    private TextField name8;


    int alignToAxisX = Gdx.graphics.getWidth()/2;

    public MapSelectScreen (RoboRally switcher, FitViewport viewPort) {
        this.switcher = switcher;
        this.viewPort = viewPort;
    }


    @Override
    public void show() {
        mainGame = new MainGame();
        width = Gdx.graphics.getWidth();
        height = Gdx.graphics.getHeight();
        stage = new Stage(new StretchViewport(width, height));
        skin = new Skin(Gdx.files.internal("uiskin.json"));


        //UI elements
        SelectBox<Integer> playerCount = new SelectBox<Integer>(skin);
        playerCount.setItems(1, 2, 3, 4, 5, 6, 7, 8);
        playerCount.setWidth(width*0.04f);
        playerCount.setHeight(height*0.04f);
        playerCount.setX(alignToAxisX - playerCount.getWidth()/2);
        playerCount.setY(height - playerCount.getHeight() - height*0.11f);


        playerCount.addListener(new ChangeListener() {
            @Override
            public void changed(ChangeEvent event, Actor actor) {
                count = playerCount.getSelected();
            }
        });

        //name fields
        name1 = new TextField("1", skin);
        name1.setWidth(width*0.10f);
        name1.setHeight(height*0.04f);
        name1.setX(alignToAxisX + name1.getWidth()*3);
        name1.setY(height - name1.getHeight() - height*0.20f);

        name2 = new TextField("2", skin);
        name2.setWidth(width*0.10f);
        name2.setHeight(height*0.04f);
        name2.setX(alignToAxisX + name2.getWidth()*3);
        name2.setY(height - name2.getHeight() - height*0.25f);

        name3 = new TextField("3", skin);
        name3.setWidth(width*0.10f);
        name3.setHeight(height*0.04f);
        name3.setX(alignToAxisX + name3.getWidth()*3);
        name3.setY(height - name3.getHeight() - height*0.30f);

        name4 = new TextField("4", skin);
        name4.setWidth(width*0.10f);
        name4.setHeight(height*0.04f);
        name4.setX(alignToAxisX + name4.getWidth()*3);
        name4.setY(height - name4.getHeight() - height*0.35f);

        name5 = new TextField("5", skin);
        name5.setWidth(width*0.10f);
        name5.setHeight(height*0.04f);
        name5.setX(alignToAxisX + name5.getWidth()*3);
        name5.setY(height - name5.getHeight() - height*0.40f);

        name6 = new TextField("6", skin);
        name6.setWidth(width*0.10f);
        name6.setHeight(height*0.04f);
        name6.setX(alignToAxisX + name6.getWidth()*3);
        name6.setY(height - name6.getHeight() - height*0.45f);

        name7 = new TextField("7", skin);
        name7.setWidth(width*0.10f);
        name7.setHeight(height*0.04f);
        name7.setX(alignToAxisX + name7.getWidth()*3);
        name7.setY(height - name7.getHeight() - height*0.50f);

        name8 = new TextField("8", skin);
        name8.setWidth(width*0.10f);
        name8.setHeight(height*0.04f);
        name8.setX(alignToAxisX + name8.getWidth()*3);
        name8.setY(height - name8.getHeight() - height*0.55f);

        //Labels
        Label names = new Label("Player names", skin);
        names.setX(name1.getX());
        names.setY(name1.getY() * 1.1f);

        Label players = new Label("Select player count", skin);
        players.setX(alignToAxisX - players.getWidth()/2);
        players.setY(playerCount.getY() * 1.05f);

        //buttons
        final Button chess = new TextButton("Chess", skin);
        chess.setWidth(width*0.40f);
        chess.setHeight(height*0.10f);
        chess.setX(alignToAxisX - chess.getWidth()/2);
        chess.setY(height - chess.getHeight()-height*0.20f);

        final Button dizzy = new TextButton("DizzyHighway", skin);
        dizzy.setWidth(width*0.40f);
        dizzy.setHeight(height*0.10f);
        dizzy.setX(alignToAxisX - dizzy.getWidth()/2);
        dizzy.setY(height - dizzy.getHeight()-height*0.32f);

        final Button exchange = new TextButton("Exchange", skin);
        exchange.setWidth(width*0.40f);
        exchange.setHeight(height*0.10f);
        exchange.setX(alignToAxisX - exchange.getWidth()/2);
        exchange.setY(height - exchange.getHeight()-height*0.44f);

        final Button vault = new TextButton("Vault", skin);
        vault.setWidth(width*0.40f);
        vault.setHeight(height*0.10f);
        vault.setX(alignToAxisX - vault.getWidth()/2);
        vault.setY(height - vault.getHeight()-height*0.56f);

        final Button whirlwind = new TextButton("Whirlwind", skin);
        whirlwind.setWidth(width*0.40f);
        whirlwind.setHeight(height*0.10f);
        whirlwind.setX(alignToAxisX - whirlwind.getWidth()/2);
        whirlwind.setY(height - whirlwind.getHeight()-height*0.68f);

        final Button backButton = new TextButton("Back", skin);
        backButton.setWidth(width*0.40f);
        backButton.setHeight(height*0.08f);
        backButton.setX(alignToAxisX - backButton.getWidth()/2);
        backButton.setY(height - backButton.getHeight()-height*0.90f);

        stage.addActor(playerCount);
        stage.addActor(chess);
        stage.addActor(dizzy);
        stage.addActor(exchange);
        stage.addActor(vault);
        stage.addActor(whirlwind);
        stage.addActor(backButton);
        stage.addActor(name1);
        stage.addActor(name2);
        stage.addActor(name3);
        stage.addActor(name4);
        stage.addActor(name5);
        stage.addActor(name6);
        stage.addActor(name7);
        stage.addActor(name8);
        stage.addActor(names);
        stage.addActor(players);

        chess.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y){
                mapPath = "Maps/Chess.tmx";
                startGame();
            }
        });

        dizzy.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y){
                mapPath = "Maps/DizzyHighway.tmx";
                startGame();
            }
        });

        exchange.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y){
                mapPath = "Maps/Exchange.tmx";
                startGame();
            }
        });

        vault.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y){
                mapPath = "Maps/tmxVault.";
                startGame();
            }
        });

        whirlwind.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y){
                mapPath = "Maps/Whirlwind.tmx";
                startGame();
            }
        });

        backButton.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                switcher.setTitleScreen();
            }
        });

        name1.setTextFieldListener(new TextField.TextFieldListener() {
            public void keyTyped(TextField textField, char c) {
                if (c == '\n') textField.getOnscreenKeyboard().show(false);
            }
        });

        name2.setTextFieldListener(new TextField.TextFieldListener() {
            public void keyTyped(TextField textField, char c) {
                if (c == '\n') textField.getOnscreenKeyboard().show(false);
            }
        });

        name3.setTextFieldListener(new TextField.TextFieldListener() {
            public void keyTyped(TextField textField, char c) {
                if (c == '\n') textField.getOnscreenKeyboard().show(false);
            }
        });
        name4.setTextFieldListener(new TextField.TextFieldListener() {
            public void keyTyped(TextField textField, char c) {
                if (c == '\n') textField.getOnscreenKeyboard().show(false);
            }
        });

        name5.setTextFieldListener(new TextField.TextFieldListener() {
            public void keyTyped(TextField textField, char c) {
                if (c == '\n') textField.getOnscreenKeyboard().show(false);
            }
        });

        name6.setTextFieldListener(new TextField.TextFieldListener() {
            public void keyTyped(TextField textField, char c) {
                if (c == '\n') textField.getOnscreenKeyboard().show(false);
            }
        });

        name7.setTextFieldListener(new TextField.TextFieldListener() {
            public void keyTyped(TextField textField, char c) {
                if (c == '\n') textField.getOnscreenKeyboard().show(false);
            }
        });

        name8.setTextFieldListener(new TextField.TextFieldListener() {
            public void keyTyped(TextField textField, char c) {
                if (c == '\n') textField.getOnscreenKeyboard().show(false);
            }
        });

        Gdx.input.setInputProcessor(stage);
    }

    @Override
    public void render(float delta) {
        gl.glClearColor(0.0f, 0.0f, 0.0f, 0.0f);
        gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

        stage.act(delta);
        stage.draw();

    }

    private void startGame() {
        this.map = new TmxMapLoader().load(mapPath);
        Board tempBoard = new Board(map);
        if (tempBoard.getNrDockingBays() < count) return; //Check that selected map supports the number of players.
        mainGame.setup(map);
        //todo: slap name input onto robots
        String[] names = {name1.getText(), name2.getText(), name3.getText(), name4.getText(), name5.getText(),
        name6.getText(), name7.getText(), name8.getText()};

        mainGame.setNumPlayers(count);
        switcher.getGameScreen().setMap(map);
        switcher.setGameScreen(mainGame);

    }

    @Override
    public void resize(int width, int height) {
        stage.getViewport().update(width,height,true);
        stage.getCamera().viewportHeight = height;
        stage.getCamera().viewportWidth = width;
    }

    @Override
    public void pause() {

    }

    @Override
    public void resume() {

    }

    @Override
    public void hide() {
        Gdx.input.setInputProcessor(null);
    }

    @Override
    public void dispose() {
        stage.dispose();
        skin.dispose();
    }
    public void setToMapSelect(MainGame mainGame) {
        this.mainGame = mainGame;
    }
}
