package inf112.skeleton.app.assetManager;

import com.badlogic.gdx.assets.AssetDescriptor;
import com.badlogic.gdx.assets.AssetManager;
import com.badlogic.gdx.graphics.Texture;

public class Assets {
    public static AssetManager manager = new AssetManager();

    public static final AssetDescriptor<Texture> texture =
            new AssetDescriptor<Texture>("Images/player.png", Texture.class);


    public static final AssetDescriptor<Texture> playButton =
            new AssetDescriptor<Texture>("Images/buttons/simpleButton.png", Texture.class);


    public static void load()
    {
        manager.load(texture);
        manager.load(playButton);

    }

    public void dispose() {
        manager.clear();
        manager.dispose();
    }
}