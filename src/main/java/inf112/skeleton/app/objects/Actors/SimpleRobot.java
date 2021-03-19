package inf112.skeleton.app.objects.Actors;

import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.maps.tiled.TiledMapTileLayer;
import com.badlogic.gdx.maps.tiled.tiles.StaticTiledMapTile;
import com.badlogic.gdx.math.Vector2;
import inf112.skeleton.app.cards.CardDeck;
import inf112.skeleton.app.enums.Direction;
import inf112.skeleton.app.map.Board;
import inf112.skeleton.app.objects.SimpleObject;
import static inf112.skeleton.app.game.MainGame.gameBoard;

public abstract class SimpleRobot extends SimpleObject implements IActor {

    private Direction lookDirection;
    private ProgramSheet programSheet;
    private final TiledMapTileLayer.Cell playerCell, playerCellDead, playerCellWon;

    public SimpleRobot(Vector2 startpos, TextureRegion[][] texture) {
        super(startpos);
        this.lookDirection = Direction.NORTH;
        this.programSheet = new ProgramSheet();
        this.playerCell = new TiledMapTileLayer.Cell().setTile(new StaticTiledMapTile(texture[0][0]));
        this.playerCellDead = new TiledMapTileLayer.Cell().setTile(new StaticTiledMapTile(texture[0][1]));
        this.playerCellWon = new TiledMapTileLayer.Cell().setTile(new StaticTiledMapTile(texture[0][2]));
    }

    /**
     * Move robot forward based on input
     * Recursive method to move robot forward
     * Backwards movement is determined by the MovementCard class
     * @param steps : number of steps to be taken
     */
    public void moveRobot(int steps) {

        TiledMapTileLayer playerTile = (TiledMapTileLayer) gameBoard.getMap().getLayers().get("Player");
        if (steps == 0) return;

        Vector2 pos = getPosition();
        playerTile.setCell((int) pos.x, (int) pos.y, new TiledMapTileLayer.Cell()); // Set empty cell where robot once existed

        if (!gameBoard.canGoToTile(pos, lookDirection)) return;


        setPosition(Direction.goDirection(pos, lookDirection)); // Move forward
        moveRobot(steps - 1);
        checkPosition();
    }



    public void checkPosition() {
        Vector2 playerPos = getPosition();


        //Player on flag
        if (gameBoard.isPosAFlag(playerPos)) {
            this.programSheet.addFlag();
            return;
        }

        //If player is on Pit or outside map. Set player to dead.
        if (gameBoard.isOnBoard(playerPos) || gameBoard.isPosAPit(playerPos)) {
            getProgramSheet().setDead(true); // Temporary?
            return;
        }
    }

    public void dealCards(CardDeck deck) {this.programSheet.dealCards( deck);}

    @Override
    public ProgramSheet getProgramSheet() {
        return this.programSheet;
    }

    public TiledMapTileLayer.Cell getPlayerCell() {
        return playerCell;
    }

    public TiledMapTileLayer.Cell getPlayerCellDead() {
        return playerCellDead;
    }

    public TiledMapTileLayer.Cell getPlayerCellWon() {
        return playerCellWon;
    }

    public Board getBoard() {
        return gameBoard;
    }

    @Override
    public Direction getLookDirection() {
        return this.lookDirection;
    }

    @Override
    public void setLookDirection(Direction direction) {
        this.lookDirection = direction;
    }

    @Override
    public void rotate(int clockwiseTurns) {

        for (int i = 0; i < clockwiseTurns; i++) {
            setLookDirection(Direction.DirectionClockwise(getLookDirection()));
        }

    }

}
