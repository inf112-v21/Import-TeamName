package inf112.skeleton.app.object;

import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.maps.tiled.TiledMapTileLayer;
import com.badlogic.gdx.maps.tiled.tiles.StaticTiledMapTile;
import com.badlogic.gdx.math.Vector2;
import inf112.skeleton.app.Game;

public class Player implements Actor {
    private Vector2 position;
    private TextureRegion texture;
    TiledMapTileLayer.Cell playerCellLayer;


    public Player(Vector2 position, TextureRegion texture) {
        this.position = position;
        this.texture = texture;
        playerCellLayer =  new TiledMapTileLayer.Cell().setTile(new StaticTiledMapTile(texture));

    }

    @Override
    public Vector2 getPosition() {
        return position;
    }

    @Override
    public void setPosition(Vector2 position){
        this.position = position;
    }
}
