package inf112.skeleton.app.objects.Actors;

import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.maps.tiled.TiledMapTileLayer;
import com.badlogic.gdx.maps.tiled.tiles.StaticTiledMapTile;
import com.badlogic.gdx.math.Vector2;
import inf112.skeleton.app.map.ProgramSheet;
import inf112.skeleton.app.enums.Direction;
import inf112.skeleton.app.map.Board;
import inf112.skeleton.app.objects.SimpleObject;

import static inf112.skeleton.app.enums.Direction.NORTH;

public abstract class Robot extends SimpleObject implements IActor {

    private Direction lookDirection;
    private ProgramSheet programSheet = new ProgramSheet();

    private final Board board;

    private final TiledMapTileLayer.Cell playerCell, playerCellDead, playerCellWon;

    public Robot(int startRow, int startCol, TextureRegion[][] texture, Board board) {
        super(new Vector2(startRow, startCol));

        this.board = board;
        lookDirection = Direction.NORTH;
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
    }

    /**
     * Move robot forward based on input
     * Recursive
     */
    public void moveRobot(int steps) {
        Vector2 pos = getPosition();
        if (board.canGoToTile(pos, lookDirection)) {

        }

    }


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
    public void rotate(Direction rotation) {

        switch (rotation) {

            case WITH_CLOCK:
                switch (lookDirection) {
                    case NORTH:
                        setLookDirection(Direction.EAST);
                        break;
                    case EAST:
                        setLookDirection(Direction.SOUTH);
                        break;
                    case SOUTH:
                        setLookDirection(Direction.WEST);
                        break;
                    case WEST:
                        setLookDirection(Direction.NORTH);
                        break;
                }
                break;
            case AGAINST_CLOCK:
                switch (lookDirection) {
                    case NORTH:
                        setLookDirection(Direction.WEST);
                    case EAST:
                        setLookDirection(Direction.NORTH);
                    case SOUTH:
                        setLookDirection(Direction.EAST);
                    case WEST:
                        setLookDirection(Direction.SOUTH);
                }
        }

    }
}
