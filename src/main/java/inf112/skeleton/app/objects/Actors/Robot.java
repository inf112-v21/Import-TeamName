package inf112.skeleton.app.objects.Actors;

import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.maps.tiled.TiledMapTileLayer;
import com.badlogic.gdx.maps.tiled.tiles.StaticTiledMapTile;
import com.badlogic.gdx.math.Vector2;
import inf112.skeleton.app.enums.Direction;
import inf112.skeleton.app.map.Board;
import inf112.skeleton.app.objects.SimpleObject;

public abstract class Robot extends SimpleObject implements IActor {
    private Direction lookDirection;

    private final Board board;

    private final TiledMapTileLayer.Cell playerCell, playerCellDead, playerCellWon;

    public Robot(int startRow, int startCol, TextureRegion[][] texture, Board board) {
        super(new Vector2(startRow, startCol));

        this.board = board;

        this.playerCell =  new TiledMapTileLayer.Cell().setTile(new StaticTiledMapTile(texture[0][0]));
        this.playerCellDead = new TiledMapTileLayer.Cell().setTile(new StaticTiledMapTile(texture[0][1]));
        this.playerCellWon  = new TiledMapTileLayer.Cell().setTile(new StaticTiledMapTile(texture[0][2]));
    }

    public void moveRobot(TiledMapTileLayer playerTile, int keycode) {
        Vector2 pos = getPosition();

        playerTile.setCell((int) pos.x,(int) pos.y, new TiledMapTileLayer.Cell());
        switch(keycode) {
            case Input.Keys.W:
                if (board.canGoToTile(pos, Direction.NORTH)) {
                    //pos.y += 1;
                    setPosition(Direction.goDirection(pos,Direction.NORTH));
                    System.out.println(pos);
                    break;
                }
            case Input.Keys.S:
                if (board.canGoToTile(pos, Direction.SOUTH)) {
                    //pos.y -= 1;
                    setPosition(Direction.goDirection(pos,Direction.SOUTH));
                    System.out.println(pos);

                    break;
                }
            case Input.Keys.A:
                if (board.canGoToTile(pos, Direction.WEST)) {
                    //pos.x -= 1;
                    setPosition(Direction.goDirection(pos,Direction.WEST));
                    System.out.println(pos);
                    break;
                }
            case Input.Keys.D:
                if (board.canGoToTile(pos, Direction.EAST)) {
                    //pos.x += 1;
                    setPosition(Direction.goDirection(pos,Direction.EAST));

                    System.out.println(pos);

                    break;
                }
        }
    }

    @Override
    public Direction getLookDirection() {
        return this.lookDirection;
    }

    @Override
    public void setLookDirection(Direction direction) {
        this.lookDirection = direction;
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
}
