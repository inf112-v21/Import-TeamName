package inf112.skeleton.app.objects.Actors;

import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.maps.tiled.TiledMapTileLayer;
import com.badlogic.gdx.maps.tiled.tiles.StaticTiledMapTile;
import com.badlogic.gdx.math.Vector2;
import inf112.skeleton.app.enums.Direction;
import inf112.skeleton.app.map.Board;
import inf112.skeleton.app.objects.SimpleObject;

import static inf112.skeleton.app.enums.Direction.NORTH;

public abstract class SimpleRobot extends SimpleObject implements IActor {

    private Direction lookDirection;
    private ProgramSheet programSheet;
    private final Board board;
    private final TiledMapTileLayer.Cell playerCell, playerCellDead, playerCellWon;

    public SimpleRobot(Vector2 startpos, TextureRegion[][] texture, Board board) {
        super(startpos);

        this.board = board;
        this.lookDirection = Direction.NORTH;
        this.programSheet = new ProgramSheet();
        this.playerCell = new TiledMapTileLayer.Cell().setTile(new StaticTiledMapTile(texture[0][0]));
        this.playerCellDead = new TiledMapTileLayer.Cell().setTile(new StaticTiledMapTile(texture[0][1]));
        this.playerCellWon = new TiledMapTileLayer.Cell().setTile(new StaticTiledMapTile(texture[0][2]));
    }

    /**
     * Move forward using WASD keys
     *
     * METHOD MEANT FOR TESTING
     * @param playerTile
     * @param keycode
     */
    public void moveRobotWASD(TiledMapTileLayer playerTile, int keycode) {
        Vector2 pos = getPosition();

        playerTile.setCell((int) pos.x, (int) pos.y, new TiledMapTileLayer.Cell());
        if (keycode == Input.Keys.W) {
            if (board.canGoToTile(pos, NORTH)) {
                //pos.y += 1;
                setPosition(Direction.goDirection(pos, NORTH));
                System.out.println(pos);
            }
        }
        if (keycode == Input.Keys.A) {
            if (board.canGoToTile(pos, Direction.WEST)) {
                //pos.x -= 1;
                setPosition(Direction.goDirection(pos, Direction.WEST));
                System.out.println(pos);
            }
        }
        if (keycode == Input.Keys.S) {
            if (board.canGoToTile(pos, Direction.SOUTH)) {
                //pos.y -= 1;
                setPosition(Direction.goDirection(pos, Direction.SOUTH));
                System.out.println(pos);
            }
        }
        if (keycode == Input.Keys.D) {
            if (board.canGoToTile(pos, Direction.EAST)) {
                //pos.x += 1;
                setPosition(Direction.goDirection(pos, Direction.EAST));
                System.out.println(pos);
            }
        }
        checkPosition();
    }

    /**
     * Move robot forward based on input
     * Recursive method to move robot forward
     * Backwards movement is determined by the MovementCard class
     * @param steps: number of steps to be taken
     */
    public void moveRobot(TiledMapTileLayer playerTile, int steps) {
        if (steps == 0) return;

        Vector2 pos = getPosition();
        playerTile.setCell((int) pos.x, (int) pos.y, new TiledMapTileLayer.Cell()); // Set empty cell where robot once existed

        if (!board.canGoToTile(pos, lookDirection)) return;


        setPosition(Direction.goDirection(pos, lookDirection)); // Move forward
        moveRobot(playerTile, steps - 1);
        checkPosition();
    }



    public void checkPosition() {
        Vector2 playerPos = getPosition();
        int xPos = (int) playerPos.x;
        int yPos = (int) playerPos.y;

        //Player on flag
        if (board.isPosAFlag(playerPos)) {
            this.programSheet.addFlag();
            return;
        }

        //If player is on Pit or outside map. Set player to dead.
        if (board.isOnBoard(playerPos) || board.isPosAPit(playerPos)) {
            getProgramSheet().setDead(true); // Temporary?
            return;
        }
    }

    public void dealCards() {this.programSheet.dealCards();}
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