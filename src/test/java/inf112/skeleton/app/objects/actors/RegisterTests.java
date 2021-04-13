package inf112.skeleton.app.objects.actors;

import com.badlogic.gdx.maps.tiled.TiledMap;
import com.badlogic.gdx.maps.tiled.TmxMapLoader;
import inf112.skeleton.app.GdxTestRunner;
import inf112.skeleton.app.assetManager.Assets;
import inf112.skeleton.app.cards.CardType;
import inf112.skeleton.app.cards.MovementCard;
import inf112.skeleton.app.cards.SimpleProgramCard;
import inf112.skeleton.app.game.MainGame;
import inf112.skeleton.app.objects.Actors.Player;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;

import java.util.ArrayList;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

@RunWith(GdxTestRunner.class)
public class RegisterTests {


    private TiledMap map;
    MainGame mainGame;
    ArrayList<SimpleProgramCard> exampleRegister;

    @Before
    public void initialize() {
        map = new TmxMapLoader().load("assets/Maps/MapForJunitTests.tmx");  // Get map file
        Assets.load();
        Assets.manager.finishLoading();

        mainGame = new MainGame();
        mainGame.setup(map);
        mainGame.setNumPlayers(1);

        exampleRegister = new ArrayList<>();
        exampleRegister.add(new MovementCard(1, CardType.BACK1));
        exampleRegister.add(new MovementCard(1, CardType.BACK1));
        exampleRegister.add(new MovementCard(1, CardType.BACK1));
        exampleRegister.add(new MovementCard(1, CardType.BACK1));
        exampleRegister.add(new MovementCard(1, CardType.BACK1));
    }

    @Test
    public void registerLocksAfterDamage() {
        Player player  = mainGame.robots.get(0);
        player.getProgramSheet().getRegister().addCardsToRegister(exampleRegister); // Send cards to register
        player.getProgramSheet().addDamage(6);
        ArrayList<SimpleProgramCard> lockedCards  =player.getProgramSheet().getLockedCards();

        assertEquals(1,lockedCards.size());

    }
}
