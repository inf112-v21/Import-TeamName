package inf112.skeleton.app.assetManager;

import com.badlogic.gdx.assets.AssetDescriptor;
import com.badlogic.gdx.assets.AssetManager;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.TextureRegion;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Assets {

    public static AssetManager manager = new AssetManager();

    public static final AssetDescriptor<Texture> texture =
            new AssetDescriptor<Texture>("Images/Robot/player.png", Texture.class);


    public static final AssetDescriptor<Texture> playButton =
            new AssetDescriptor<Texture>("Images/buttons/simpleButton.png", Texture.class);

    // CARDS //

    public static final AssetDescriptor<Texture> BackUpCard =
            new AssetDescriptor<Texture>("Images/cards/BackUp.png", Texture.class);

    public static final AssetDescriptor<Texture> Move1Card =
            new AssetDescriptor<Texture>("Images/cards/Move1.png", Texture.class);
    public static final AssetDescriptor<Texture> Move1CardToggled =
            new AssetDescriptor<Texture>("Images/cards/Move1Toggled.png", Texture.class);

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

    public static final List<TextureRegion[][]> robotTextures = getRobotTextures();

    /**
     * @return List of all different colour textures a tank can have.
     */
    public static List<TextureRegion[][]> getRobotTextures() {
        List<String> colour = new ArrayList<>(Arrays.asList("red","orange","blue","cyan","green","magenta","purple","yellow"));
        List<TextureRegion[][]> textures = new ArrayList<>();

        for (String co : colour) {
            TextureRegion[][] tankTexture = new TextureRegion(new Texture("Images/Robot/robot_" + co + ".png")).split(300, 300);
            textures.add(tankTexture);
        }
        return textures;
    }


    public static void load() {

        manager.load(texture);
        manager.load(playButton);
        manager.load(BackUpCard);
        manager.load(UTurnCard);
        manager.load(Move1Card);
        manager.load(Move1CardToggled);
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