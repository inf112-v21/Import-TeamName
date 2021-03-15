package inf112.skeleton.app.game;

import com.badlogic.gdx.maps.tiled.TiledMap;
import inf112.skeleton.app.cards.CardDeck;
import inf112.skeleton.app.map.Board;
import inf112.skeleton.app.objects.Actors.IActor;
import inf112.skeleton.app.objects.Actors.Player;
import inf112.skeleton.app.objects.Actors.SimpleRobot;
import java.awt.*;
import java.util.ArrayList;


public class MainGame {


   public static ArrayList<SimpleRobot> robots;
   public static Board gameBoard;
   public static CardDeck deck;

    /**
     * Constructor method
     */
    public MainGame() {
        robots = new ArrayList<>();
    }

    /**
     * Setup:
     * - Initialize robots Arraylist
     * - Give map to Board.java
     * - Create Program Card objects
    */
    public static void setup(TiledMap map) {
        deck = new CardDeck();
        gameBoard = new Board(map);
    }

    /**
     * Game Loop
     * Executes the phases in correct order
    */
    public static void gameLoop() {

        IPhase dealCardsPhase = new DealCardsPhase();
        IPhase programRegisterPhase = new ProgramRegisterPhase();
        IPhase announcePowerDownPhase = new AnnouncePowerDownPhase();
        IPhase completeRegisterPhase = new CompleteRegisterPhase();
        IPhase cleanupPhase = new CleanupPhase();

        while (true) { //TODO: If game is over, end loop.
            dealCardsPhase.run();
            programRegisterPhase.run();
            announcePowerDownPhase.run();
            completeRegisterPhase.run();
            cleanupPhase.run();
        }

    }

    /**
     * Nachspiel
     * Things to do after the game loop is finished
     */
    public static void end() {

    }

    public Board getGameBoard() { return gameBoard; }
    public CardDeck getDeck() { return deck; }

    public void setNumPlayers(int numPlayers) {
        for (int i = 0; i < numPlayers; i++) {
            /**
             * Set to correct start positions
             */
            //Player robot = new Player()
            // robots.add(robot);
        }
    }

}
