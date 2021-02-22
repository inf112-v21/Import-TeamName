package inf112.skeleton.app;

import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.graphics.Texture;

import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.maps.tiled.TiledMap;
import com.badlogic.gdx.maps.tiled.TiledMapTileLayer;
import com.badlogic.gdx.maps.tiled.TmxMapLoader;

import inf112.skeleton.app.objects.Actors.Player;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;

import static org.junit.Assert.assertEquals;

@RunWith(GdxTestRunner.class)
public class PlayerTest {

    // Make maps
    private TiledMap map;

    private Texture playerTexture;
    private TextureRegion[][] textures;
    private TiledMapTileLayer tilePlayer;


    @Before
    public void initialise() {
        playerTexture = new Texture("Images/player.png"); // Texture of player
        textures = new TextureRegion(new Texture("Images/player.png")).split(300, 300);  // Splits player texture into the 3 parts. Live/Dead/Win
        map = new TmxMapLoader().load("Maps/Chess.tmx");       // Get map file
        tilePlayer = (TiledMapTileLayer) map.getLayers().get("Player");
    }


    @Test
    public void playerMoveUp() {
        Player player = new Player(2, 2, textures );
        player.moveRobot(tilePlayer, 51); // Keycode 51 -> w Should move up
        assertEquals(player.getPosition(), new Vector2(2,3));
    }


}
