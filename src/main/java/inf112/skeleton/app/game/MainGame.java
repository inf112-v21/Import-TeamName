package inf112.skeleton.app.game;

import com.badlogic.gdx.maps.tiled.TiledMap;
import inf112.skeleton.app.assetManager.Assets;
import inf112.skeleton.app.cards.CardDeck;
import inf112.skeleton.app.map.Board;
import inf112.skeleton.app.multiplayer.NetworkPackets;
import inf112.skeleton.app.objects.Actors.Robot;
import inf112.skeleton.app.objects.TileObjects.DockingBay;
import inf112.skeleton.app.screens.cardsUI.CardUI;

import java.util.ArrayList;
import java.util.List;


public  final class MainGame {


   public static ArrayList<Robot> robots;
   public static Board gameBoard;
   public static CardDeck deck;
   private boolean gameOver = false;



   public static DealCardsPhase dealCardsPhase;
   public static ChooseCardsPhase chooseCardsPhase;
   public static CompleteRegisterPhase completeRegisterPhase;
   public static CleanupPhase cleanupPhase;

    /**
     * Constructor method
     */
    public MainGame() { robots = new ArrayList<>(); }

    /**
     * Setup:
     * - Initialize robots Arraylist
     * - Give map to Board.java
     * - Create Program Card objects
    */
    public static void setup(TiledMap map) {
        deck = new CardDeck();
        gameBoard = new Board(map);
        dealCardsPhase = new DealCardsPhase();
        chooseCardsPhase = new ChooseCardsPhase();
        completeRegisterPhase = new CompleteRegisterPhase();
        cleanupPhase = new CleanupPhase();

    }

    /**
     * Game Loop
     * Executes the phases in correct order
    */
    public void startGameRound(CardUI cardUI)  {
        dealCardsPhase.run();
        chooseCardsPhase.run(this, cardUI);

    }



    /** Method for adding players to the game
     * @param numPlayers
     */
    public static void setNumPlayers(int numPlayers, String[] playerNames) {
        List<DockingBay> startPositions = gameBoard.getDockingBays();

        for (int i = 0; i < numPlayers; i++) {
            Robot robot = new Robot(startPositions.get(i).getPosition(), Assets.robotTextures.get(i), playerNames[i]);
            robots.add(robot);
        }
    }

    /**
     * Method for adding players used in Junit tests
     * @param numPlayers
     */
    public static void setNumPlayers(int numPlayers) {
        List<DockingBay> startPositions = gameBoard.getDockingBays();

        for (int i = 0; i < numPlayers; i++) {
            Robot robot = new Robot(startPositions.get(i).getPosition(), Assets.robotTextures.get(i), "1");
            robots.add(robot);
        }
    }

    /**
     * Temporary. Used for tests.
     */
    public void addPlayer(Robot robot) { robots.add(robot); }

    public void multiplayerAddPlayer(int id) {
        List<DockingBay> startPositions = gameBoard.getDockingBays();
        id--;
        Robot newRobo = new Robot(startPositions.get(id).getPosition(), Assets.robotTextures.get(id), "1");
        addPlayer(newRobo);
    }


    public void removePlayer (int id){
        id --;
        int x = (int) robots.get(id).getPosition().x;
        int y = (int) robots.get(id).getPosition().y;
        robots.get(id).clearRobotSprite(x, y);
        robots.remove(id);
    }

    public void cheatPosition (NetworkPackets.MovedRobot packet) {
        if (packet.playerID == 1) {
            robots.get(packet.playerID).moveRobotWASD(packet.keycode);
        } else {
            robots.get(packet.playerID -1).moveRobotWASD(packet.keycode);
            System.out.println(packet.playerID);
        }
    }

    public static ArrayList<Robot> getRobots() {return robots;}


}
