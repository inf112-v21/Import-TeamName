package inf112.skeleton.app.objects.Actors;

import inf112.skeleton.app.cards.ProgramCard;

import java.util.ArrayList;

public class ProgramSheet {

    private final ArrayList<ProgramCard> registers = new ArrayList<>();

    private int damageTokens;

    private int lifeTokens;

    private boolean powerDown;

    private int flags;

    public ProgramSheet() {
        damageTokens = 0;
        lifeTokens   = 0;
        powerDown    = false;
        flags        = 0;
    }

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

}
