package inf112.skeleton.app.buttons;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.scenes.scene2d.ui.ImageButton;
import com.badlogic.gdx.scenes.scene2d.utils.Drawable;
import com.badlogic.gdx.scenes.scene2d.utils.TextureRegionDrawable;
import inf112.skeleton.app.assetManager.Assets;

public class PlayButton {
    Texture texture;
    Drawable drawable;
    ImageButton playButton;

    /**
     *
     * @param w: Height of placement of button
     * @param h: Width of placement of button
     * @param widthSize: Width of button
     * @param heightSize: Height of button
     */
    public PlayButton(float w, float h, float widthSize, float heightSize) {
        texture = Assets.manager.get(Assets.playButton);
        drawable = new TextureRegionDrawable(new TextureRegion(texture));
        playButton = new ImageButton(drawable);
        playButton.setPosition(w,h);
        playButton.setSize(widthSize,heightSize);
    }

    /**
     *
     * @param w: Height of placement of button
     * @param h: Width of placement of button
     */
    public PlayButton(float w, float h) {
        texture = Assets.manager.get(Assets.playButton);
        drawable = new TextureRegionDrawable(new TextureRegion(texture));
        playButton = new ImageButton(drawable);
        playButton.setPosition(w,h);
        playButton.setSize(30,30);
    }

    /**
     *
     * return
     */
    public ImageButton getButton() {
        return playButton;
    }
}
