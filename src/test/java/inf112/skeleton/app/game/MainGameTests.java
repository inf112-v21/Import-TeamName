package inf112.skeleton.app.game;


import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.maps.tiled.TiledMap;
import com.badlogic.gdx.maps.tiled.TmxMapLoader;
import inf112.skeleton.app.GdxTestRunner;
import inf112.skeleton.app.assetManager.Assets;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;

import static org.junit.Assert.assertEquals;

@RunWith(GdxTestRunner.class)
public class MainGameTests {
    //Get map
    private TiledMap map = new TmxMapLoader().load("Maps/MapForJunitTests.tmx");
    //Splits player texture into the 3 parts. Live/Dead/Win
    private TextureRegion[][] textures = new TextureRegion(new Texture("Images/Robot/robot.png")).split(300, 300);
    private MainGame mainGame;

    @Before
    public void initialise() {
        Assets.load();
        Assets.manager.finishLoading();

        mainGame = new MainGame();
        mainGame.setup(map); //Make game board

    }

    @Test
    public void setNamesTest() {
        String[] names = {"Alan Turing"};
        mainGame.setNumPlayers(1, names);

        String name = mainGame.robots.get(0).getPlayerName();
        assertEquals("Alan Turing", name);
    }
}