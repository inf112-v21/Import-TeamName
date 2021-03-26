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
     * TODO: Discuss how to add players.
     * @param numPlayers
     */
    public  static void setNumPlayers(int numPlayers) {
        List<DockingBay> startPositions = gameBoard.getDockingBays();
        TextureRegion[][] textures = new TextureRegion(new Texture("Images/player.png")).split(300, 300);
        for (int i = 0; i < numPlayers; i++) {
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

    /**
     * Temporary method for excuting cards for a single player
     * In later iterations will be handles by CompleteRegisterphase
     */
    public void executeCards() {
        Player player = robots.get(0); // Temporarily one player
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
