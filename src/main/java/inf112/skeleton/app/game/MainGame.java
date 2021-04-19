package inf112.skeleton.app.game;

import com.badlogic.gdx.maps.tiled.TiledMap;
import com.badlogic.gdx.maps.tiled.TiledMapTileLayer;
import com.badlogic.gdx.math.Vector2;
import inf112.skeleton.app.assetManager.Assets;
import inf112.skeleton.app.cards.CardDeck;
import inf112.skeleton.app.map.Board;
import inf112.skeleton.app.multiplayer.NetworkPackets;
import inf112.skeleton.app.objects.Actors.Player;
import inf112.skeleton.app.objects.Actors.SimpleRobot;
import inf112.skeleton.app.objects.TileObjects.DockingBay;
import inf112.skeleton.app.screens.cardsUI.CardUI;

import java.util.ArrayList;
import java.util.List;
import java.util.Vector;


public  final class MainGame {


   public static ArrayList<Player> robots;
   public static Board gameBoard;
   public static CardDeck deck;
   private boolean gameOver = false;

   public CompleteRegisterPhase completeRegisterPhase;
   public ChooseCardsPhase chooseCardsPhase;
   public CleanupPhase cleanupPhase;

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

    }

    /**
     * Game Loop
     * Executes the phases in correct order
    */
    public void gameLoop(CardUI cardUI)  {

        DealCardsPhase dealCardsPhase = new DealCardsPhase();
        chooseCardsPhase = new ChooseCardsPhase();
        IPhase announcePowerDownPhase = new AnnouncePowerDownPhase();
        completeRegisterPhase = new CompleteRegisterPhase();
        cleanupPhase = new CleanupPhase();

        dealCardsPhase.run();
        //chooseCardsPhase.debugRun( cardUI); // For debugging
        chooseCardsPhase.run(this,cardUI);
        //announcePowerDownPhase.run(this);

        //cleanupPhase.run(this);


    }

    /**
     * Nachspiel
     * Things to do after the game loop is finished
     */
    public  void end() {
        // Some criteria for the game to end?
        gameOver = true;
    }


    /**
     * @param numPlayers
     */
    public static void setNumPlayers(int numPlayers) {
        // input string list of names, length == numPlayers
        List<DockingBay> startPositions = gameBoard.getDockingBays();

        //TextureRegion[][] textures = new TextureRegion(new Texture("Images/robot.png")).split(300, 300);

        for (int i = 0; i < numPlayers; i++) {
            Player robot = new Player(startPositions.get(i).getPosition(), Assets.robotTextures.get(i));
            robots.add(robot);

            //Test hand.
            /*
            robot.getProgramSheet().getRegister().selectCard(new MovementCard(1, CardType.MOVE1));
            robot.getProgramSheet().getRegister().selectCard(new MovementCard(2, CardType.MOVE1));
            robot.getProgramSheet().getRegister().selectCard(new RotationCard(1, CardType.ROTATELEFT));
            robot.getProgramSheet().getRegister().selectCard(new MovementCard(4, CardType.MOVE1));
            robot.getProgramSheet().getRegister().selectCard(new RotationCard(1, CardType.ROTATERIGHT));
             */
        }
    }

    /**
     * Temporary. Used for tests.
     */
    public void addPlayer(Player player) {
        robots.add(player);
    }

    public void multiplayerAddPlayer(int id) {
        List<DockingBay> startPositions = gameBoard.getDockingBays();
        id--;
        Player newRobo = new Player(startPositions.get(id).getPosition(), Assets.robotTextures.get(id));
        addPlayer(newRobo);
    }


    public void removePlayer (int id){
        id --;
        robots.remove(id);
        //todo: only removes robot from list, but not from board.
    }

    public void cheatPosition (NetworkPackets.MovedRobot packet) {
        //robots.get(packet.playerID -1).cheatPosition(packet.x, packet.y, packet.xD, packet.yD);
        if (packet.playerID == 1) {
            robots.get(packet.playerID).moveRobotWASD(packet.keycode);
        } else {
            robots.get(packet.playerID -1).moveRobotWASD(packet.keycode);
        }
    }

    public static ArrayList<Player> getRobots() {return robots;}

    /**
     * Temporary method for excuting cards for a single player
     * In later iterations will be handles by CompleteRegisterphase
     */
    public void executeCards(Player player) {
        TiledMapTileLayer playerTile = (TiledMapTileLayer) gameBoard.getMap().getLayers().get("Player");
        playerTile.setCell((int) player.getPosition().x, (int) player.getPosition().y, new TiledMapTileLayer.Cell()); // Clear previous robot image
        CompleteRegisterPhase phase = new CompleteRegisterPhase();
        phase.executePlayerProgramCards(player);
        System.out.println("CompleteRegisterPhase is running.");
        System.out.println("Damage tokens: " + player.getProgramSheet().getDamage());
        System.out.println("Flags: " + player.getProgramSheet().getNumberOfFlags());
        System.out.println("Position: " + player.getPosition() + "\n");

    }


}
