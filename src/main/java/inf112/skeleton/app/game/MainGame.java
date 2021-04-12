package inf112.skeleton.app.game;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.maps.tiled.TiledMap;
import com.badlogic.gdx.maps.tiled.TiledMapTileLayer;
import inf112.skeleton.app.cards.CardDeck;
import inf112.skeleton.app.map.Board;
import inf112.skeleton.app.objects.Actors.Player;
import inf112.skeleton.app.objects.TileObjects.DockingBay;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;


public  final class MainGame {


   public static ArrayList<Player> robots;
   public static Board gameBoard;
   public static CardDeck deck;
   private boolean gameOver = false;

   public static List<TextureRegion[][]> robotTextures;

    /**
     * Constructor method
     */
    public MainGame() {

        robots = new ArrayList<>();
        robotTextures = getRobotTextures();
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
    public void gameLoop() {

        IPhase dealCardsPhase = new DealCardsPhase();
        IPhase announcePowerDownPhase = new AnnouncePowerDownPhase();
        IPhase completeRegisterPhase = new CompleteRegisterPhase();
        IPhase cleanupPhase = new CleanupPhase();

        while (gameOver == false) { //TODO: If game is over, end loop.
            dealCardsPhase.run(this);
            //chooseCardsPhase.run();
            //programRegisterPhase.run(this);
            //announcePowerDownPhase.run(this);
            //completeRegisterPhase.run(this);
            //cleanupPhase.run(this);
        }

    }

    /**
     * Nachspiel
     * Things to do after the game loop is finished
     */
    public  void end() {
        // Some critiera for the game to end?
        gameOver = true;
    }


    /**
     * @param numPlayers
     */
    public static void setNumPlayers(int numPlayers) {
        List<DockingBay> startPositions = gameBoard.getDockingBays();

        //TextureRegion[][] textures = new TextureRegion(new Texture("Images/robot.png")).split(300, 300);

        for (int i = 0; i < numPlayers; i++) {
            Player robot = new Player(startPositions.get(i).getPosition(), robotTextures.get(i));
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
     * @return List of all different colour textures a tank can have.
     */
    public List<TextureRegion[][]> getRobotTextures() {
        List<String> colour = new ArrayList<>(Arrays.asList("red","orange","blue","cyan","green","magenta","purple","yellow"));
        List<TextureRegion[][]> textures = new ArrayList<>();

        for (String co : colour) {
            TextureRegion[][] tankTexture = new TextureRegion(new Texture("Images/robot_" + co + ".png")).split(300, 300);
            textures.add(tankTexture);
        }
        return textures;
    }

    /**
     * Temporary. Used for tests.
     */
    public void addPlayer(Player player) {
        robots.add(player);
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
