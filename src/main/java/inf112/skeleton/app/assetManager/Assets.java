package inf112.skeleton.app.assetManager;

import com.badlogic.gdx.assets.AssetDescriptor;
import com.badlogic.gdx.assets.AssetManager;
import com.badlogic.gdx.graphics.Texture;

public class Assets {
    public static AssetManager manager = new AssetManager();

    public static final AssetDescriptor<Texture> texture =
            new AssetDescriptor<Texture>("Images/player.png", Texture.class);


    public static final AssetDescriptor<Texture> simpleButton =
            new AssetDescriptor<Texture>("Images/buttons/simpleButton.png", Texture.class);


    //BUTTONS
    public static final AssetDescriptor<Texture> EXIT_BUTTON
            = new AssetDescriptor<Texture>("assets/buttons/exitButton.png", Texture.class);



    public static void load()
    {
        manager.load(texture);
        manager.load(simpleButton);

    }

    public void dispose() {
        manager.clear();
        manager.dispose();
    }
}