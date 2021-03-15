package inf112.skeleton.app.game;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.maps.tiled.TiledMap;
import com.badlogic.gdx.maps.tiled.TiledMapTileLayer;
import com.badlogic.gdx.maps.tiled.TmxMapLoader;
import inf112.skeleton.app.GdxTestRunner;
import inf112.skeleton.app.assetManager.Assets;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;

@RunWith(GdxTestRunner.class)
public class CompleteRegisterPhaseTest {


    // Make maps
    private TiledMap map;

    private TextureRegion[][] textures;
    private TiledMapTileLayer tilePlayer;
    private MainGame game;

    @Before
    public void initalise() {
        textures = new TextureRegion(new Texture("Images/player.png")).split(300, 300);  // Splits player texture into the 3 parts. Live/Dead/Win
        map = new TmxMapLoader().load("Maps/MapForJunitTests.tmx");       // Get map file
        tilePlayer = (TiledMapTileLayer) map.getLayers().get("Player");

        Assets.load();
        Assets.manager.finishLoading();

        game = new MainGame();
        game.setup(map);

        CompleteRegisterPhase phase = new CompleteRegisterPhase();
    }

    @Test
    public void test1() {
        Assert.assertEquals(true,true);
    }






}
