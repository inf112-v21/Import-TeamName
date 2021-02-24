package inf112.skeleton.app.cards;

import com.badlogic.gdx.graphics.Texture;

public abstract class ProgramCard implements IProgramCard {

    String name;
    int priority;
    CardType cardtype;
    Texture texture;

    ProgramCard(String name, int priority, CardType cardtype, Texture texture) {
        this.priority =  priority;
        this.name = name;
        this.cardtype = cardtype;
        this.texture = texture;
    }

    public String getName() {
        return name;
    }

    public int getPriority(){ return priority; }

    public CardType getType() {return cardtype; }





}
