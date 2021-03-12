package inf112.skeleton.app.objects.Actors;

import inf112.skeleton.app.cards.CardHand;

public class ProgramSheet {


    private CardHand carddeck;
    private int damageTokens;
    private int lifeTokens;
    private int flags;
    private boolean powerDown;
    private boolean dead;

    public ProgramSheet() {
        damageTokens = 0;
        lifeTokens = 0;
        powerDown = false;
        flags = 0;
        dead = false;
        carddeck = new CardHand(9);
    }


    public void addDamage(int amount) {

        this.damageTokens += amount;
        if (damageTokens > lifeTokens) this.dead = true;
        if (this.damageTokens > 10) {
            this.damageTokens = 10;
        }

        if (this.damageTokens < 0) {
            this.damageTokens = 0;
        }
        carddeck.setNumCardsDeck(9 - damageTokens);
    }

    /**
     * Called every round for its robot
     */
    public void dealCards() {
        carddeck = new CardHand(9 - damageTokens);

    }

    public void addLife(int amount) {
        this.lifeTokens += amount;

        if (this.lifeTokens > 3) {
            this.lifeTokens = 3;
        }

        if (this.lifeTokens < 0) {
            this.lifeTokens = 0;
        }
    }

    public CardHand getCardDeck() {return this.carddeck;}

    public int getNumberOfFlags() {return flags;}

    public void addFlag() {flags++;}

    public int getDamage() {
        return this.damageTokens;
    }

    public int getLife() {
        return this.lifeTokens;
    }

    public void setPowerDown(boolean state) {
        this.powerDown = state;
    }

    public boolean getPowerDown() {
        return this.powerDown;
    }

    public boolean isDead() {
        return this.dead;
    }

    public void setDead(boolean dead) {
        this.dead = dead;
    }
}
