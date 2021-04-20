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

import static org.junit.Assert.*;

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
        /**
         *
         */
        Player player  = mainGame.robots.get(0);
        player.getProgramSheet().getRegister().addCardsToRegister(exampleRegister); // Send cards to register
        player.getProgramSheet().addDamage(5);
        assertEquals(1,player.getProgramSheet().getLockedCards().size());
    }



    @Test
    public void registerLocksAfterDamage2() {
        /**
         *
         */
        Player player  = mainGame.robots.get(0);
        player.getProgramSheet().getRegister().addCardsToRegister(exampleRegister); // Send cards to register
        player.getProgramSheet().addDamage(6);
        assertEquals(2,player.getProgramSheet().getLockedCards().size());
    }

    @Test
    public void registerLocksAfterDamage3() {
        /**
         *
         */
        Player player  = mainGame.robots.get(0);
        player.getProgramSheet().getRegister().addCardsToRegister(exampleRegister); // Send cards to register
        player.getProgramSheet().addDamage(7);
        assertEquals(3,player.getProgramSheet().getLockedCards().size());
    }

    @Test
    public void unlockRegisterAfterLosing1Damage() {
        Player player  = mainGame.robots.get(0);
        player.getProgramSheet().getRegister().addCardsToRegister(exampleRegister); // Send cards to register
        player.getProgramSheet().addDamage(5);
        assertEquals(1,player.getProgramSheet().getLockedCards().size()); // Confirm locked register
        player.getProgramSheet().addDamage(-1);
        assertEquals(0,player.getProgramSheet().getLockedCards().size()); // Confirm locked register

    }

    @Test
    public void unlockRegisterAfterLosing2Damage() {
        Player player  = mainGame.robots.get(0);
        player.getProgramSheet().getRegister().addCardsToRegister(exampleRegister); // Send cards to register
        player.getProgramSheet().addDamage(6);
        assertEquals(2,player.getProgramSheet().getLockedCards().size()); // Confirm locked register
        player.getProgramSheet().addDamage(-2);
        assertEquals(0,player.getProgramSheet().getLockedCards().size()); // Confirm locked register
    }

    @Test
    public void unlockRegisterAfterLosing3Damage() {
        Player player  = mainGame.robots.get(0);
        player.getProgramSheet().getRegister().addCardsToRegister(exampleRegister); // Send cards to register
        player.getProgramSheet().addDamage(9);
        assertEquals(5,player.getProgramSheet().getLockedCards().size()); // Confirm locked register
        player.getProgramSheet().addDamage(-3);
        assertEquals(2,player.getProgramSheet().getLockedCards().size()); // Confirm locked register
    }

}

