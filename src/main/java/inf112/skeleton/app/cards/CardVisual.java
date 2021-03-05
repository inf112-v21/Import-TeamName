package inf112.skeleton.app.cards;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.scenes.scene2d.ui.ImageButton;
import com.badlogic.gdx.scenes.scene2d.utils.Drawable;
import com.badlogic.gdx.scenes.scene2d.utils.TextureRegionDrawable;
import inf112.skeleton.app.assetManager.Assets;

public class CardVisual {

    private Drawable drawable;
    private CardType cardtype;
    private Texture texture;
    private ImageButton card;

    public CardVisual(float wPos, float hPos, CardType cardType) {

        this.cardtype = cardType;
        getTexture();
        drawable = new TextureRegionDrawable(new TextureRegion(texture));
        card = new ImageButton(drawable);

        card.setSize(5, 5);
        card.setPosition(wPos, hPos);
    }

    public ImageButton getCard() { return card; }

    public void setPosition(float wPos, float hPos) { card.setPosition(wPos, hPos); }

    private void getTexture() {
        switch (cardtype) {
            case MOVE1: texture = Assets.manager.get(Assets.Move1Card);  break;
            case MOVE2: texture = Assets.manager.get(Assets.Move2Card); break;
            case MOVE3: texture = Assets.manager.get(Assets.Move3Card); break;
            case ROTATELEFT: texture = Assets.manager.get(Assets.RotateLeftCard); break;
            case ROTATERIGHT: texture = Assets.manager.get(Assets.RotateRightCard); break;
            case UTURN: texture = Assets.manager.get(Assets.UTurnCard); break;
            default: throw new IllegalArgumentException("Expected enum cardtype of type (Move1, move2, move3, rotateleft, rotateright, uturn). Got :" + cardtype);
        }
    }

}
