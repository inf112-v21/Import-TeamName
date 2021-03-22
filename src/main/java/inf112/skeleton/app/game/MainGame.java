package inf112.skeleton.app.game;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.maps.tiled.TiledMap;
import inf112.skeleton.app.cards.CardDeck;
import inf112.skeleton.app.map.Board;
import inf112.skeleton.app.objects.Actors.IActor;
import inf112.skeleton.app.objects.Actors.Player;
import inf112.skeleton.app.objects.Actors.SimpleRobot;
import inf112.skeleton.app.objects.TileObjects.DockingBay;

import java.awt.*;
import java.util.ArrayList;
import java.util.List;


public final class MainGame {


   public static ArrayList<Player> robots;
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


    /**
     * TODO: Discuss how to add players.
     * @param numPlayers
     */
    public  static void setNumPlayers(int numPlayers) {
        List<DockingBay> startPositions = gameBoard.getDockingBays();
        TextureRegion[][] textures = new TextureRegion(new Texture("Images/player.png")).split(300, 300);

        for (int i = 0; i < numPlayers-1; i++) {
            Player robot = new Player(startPositions.get(i).getPosition(), textures);
            robots.add(robot);
        }
    }

    /**
     * Temporary. Used for tests.
     */
    public void addPlayer(Player player) {
        robots.add(player);
    }

    public static ArrayList<Player> getRobots() {return robots;}

}
