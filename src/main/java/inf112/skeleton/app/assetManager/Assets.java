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

    // CARDS //

    public static final AssetDescriptor<Texture> BackUpCard =
            new AssetDescriptor<Texture>("Images/cards/BackUp.png", Texture.class);

    public static final AssetDescriptor<Texture> Move1Card =
            new AssetDescriptor<Texture>("Images/cards/Move1.png", Texture.class);

    public static final AssetDescriptor<Texture> Move2Card =
            new AssetDescriptor<Texture>("Images/cards/Move2.png", Texture.class);

    public static final AssetDescriptor<Texture> Move3Card =
            new AssetDescriptor<Texture>("Images/cards/Move3.png", Texture.class);

    public static final AssetDescriptor<Texture> RotateLeftCard =
            new AssetDescriptor<Texture>("Images/cards/RotateLeft.png", Texture.class);

    public static final AssetDescriptor<Texture> RotateRightCard =
            new AssetDescriptor<Texture>("Images/cards/RotateRight.png", Texture.class);

    public static final AssetDescriptor<Texture> UTurnCard =
            new AssetDescriptor<Texture>("Images/cards/U-Turn.png", Texture.class);

    public static final AssetDescriptor<Texture> VictoryImage =
            new AssetDescriptor<Texture>("Images/Victory.png", Texture.class);


    public static void load() {
        manager.load(texture);
        manager.load(playButton);
        manager.load(BackUpCard);
        manager.load(UTurnCard);
        manager.load(Move1Card);
        manager.load(Move2Card);
        manager.load(Move3Card);
        manager.load(RotateLeftCard);
        manager.load(RotateRightCard);
        manager.load(VictoryImage);

    }

    public void dispose() {
        manager.clear();
        manager.dispose();
    }
}