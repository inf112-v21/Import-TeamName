package inf112.skeleton.app.cards;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.scenes.scene2d.ui.ImageButton;
import com.badlogic.gdx.scenes.scene2d.utils.Drawable;
import com.badlogic.gdx.scenes.scene2d.utils.TextureRegionDrawable;
import inf112.skeleton.app.assetManager.Assets;
import inf112.skeleton.app.objects.Actors.Robot;


/**
 * Abstract simple class.
 */
public abstract class SimpleProgramCard implements IProgramCard{

    private ImageButton cardButton;
    private final int priority;
    private CardType cardtype;
    private Texture texture;
    private Texture toggledTexture;


    SimpleProgramCard(int priority, CardType cardType) {
        this.priority = priority;
        this.cardtype = cardType;
        processTextures();
        Drawable drawable = new TextureRegionDrawable(new TextureRegion(texture));
        cardButton = new ImageButton(drawable);
    }


    public int getPriority() {
        return this.priority;
    }


    public CardType getType() {return this.cardtype;}


    public ImageButton getCardButton() { return cardButton; }

    public abstract void action(Robot robot);

    /**
     * Prints cardtype
     * @return: "String of card types
     */
    public String toString() {
        return " " + cardtype;
    }


    /**
     * Set correct textures for a given card
      */
    private void processTextures() {
        switch (cardtype) {
            case MOVE1: texture = Assets.manager.get(Assets.Move1Card); toggledTexture = Assets.manager.get(Assets.Move1CardToggled); break;
            case MOVE2: texture = Assets.manager.get(Assets.Move2Card); toggledTexture = Assets.manager.get(Assets.Move2CardToggled); break;
            case MOVE3: texture = Assets.manager.get(Assets.Move3Card); toggledTexture = Assets.manager.get(Assets.Move3CardToggled); break;
            case BACK1: texture = Assets.manager.get(Assets.BackUpCard); toggledTexture = Assets.manager.get(Assets.BackUpCardToggled); break;
            case ROTATELEFT: texture = Assets.manager.get(Assets.RotateLeftCard); toggledTexture = Assets.manager.get(Assets.RotateLeftCardToggled);break;
            case ROTATERIGHT: texture = Assets.manager.get(Assets.RotateRightCard); toggledTexture = Assets.manager.get(Assets.RotateRightCardToggled); break;
            case UTURN: texture = Assets.manager.get(Assets.UTurnCard); toggledTexture = Assets.manager.get(Assets.UTurnCardToggled); break;
            default: throw new IllegalArgumentException("Expected enum cardtype of type (Move1, move2, move3, rotateleft, rotateright, uturn). Got :" + cardtype);
        }
    }


    /**
     * Returns texture when player has selcted cards
     * @return: texture
     */
    public Texture getToggledTexture() {return this.toggledTexture;}


    public int compareTo(SimpleProgramCard otherCard) {
        return Integer.compare(getPriority(), otherCard.getPriority());
    }

}
