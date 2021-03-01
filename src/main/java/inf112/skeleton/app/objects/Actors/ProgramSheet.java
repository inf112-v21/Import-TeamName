package inf112.skeleton.app.objects.Actors;

import inf112.skeleton.app.cards.ProgramCard;

import java.util.ArrayList;

public class ProgramSheet {

    private final ArrayList<ProgramCard> registers = new ArrayList<>();

    private int damageTokens = 0;

    private int lifeTokens = 0;

    private boolean powerDown = false;

    public void addDamage(int amount) {
        this.damageTokens += amount;

        if (this.damageTokens > 10) {
            this.damageTokens = 10;
        }

        if (this.damageTokens < 0) {
            this.damageTokens = 0;
        }
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

}
