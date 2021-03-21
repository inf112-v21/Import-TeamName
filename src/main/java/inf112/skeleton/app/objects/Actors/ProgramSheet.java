package inf112.skeleton.app.objects.Actors;

import com.badlogic.gdx.math.Vector2;
import inf112.skeleton.app.cards.CardDeck;
import inf112.skeleton.app.cards.CardHand;
import inf112.skeleton.app.cards.Register;
import inf112.skeleton.app.objects.TileObjects.Flag;
import java.util.ArrayList;
import java.util.List;

public class ProgramSheet {


    private CardHand hand;
    private Register register;
    private int damageTokens;
    private int lifeTokens;
    private List<Integer> flags;
    private boolean powerDown;
    private boolean dead;
    private Vector2 archiveMarker; //Respawn point when reentering the game.

    public ProgramSheet() {
        damageTokens = 0;
        lifeTokens = 0;
        powerDown = false;
        flags = new ArrayList<>();
        dead = false;
        hand = new CardHand(9);
        register = new Register(); //TODO: Is this correct? Added for testing CompleteRegisterPhase -Endre
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
        hand.setNumCardsDeck(9 - damageTokens);
    }

    /**
     * Called every round for its robot
     */
    public void dealCards(CardDeck deck) {
        hand = new CardHand(9 - damageTokens);

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

    /**
     * Cards that will be executed in CompleteRegisterPhase
     * @return
     */
    public Register getRegister() {return this.register;}

    /**
     * All cards on hand?
     * @return
     */
    public CardHand getCardHand() {return this.hand;}

    public int getNumberOfFlags() { return flags.size(); }

    /**
     * Tries to add flag to visited flags.
     * Can only add if the flag id is equal to last visited flag +1. --> Only add flags in order.
     * @param flag
     */
    public void addFlag(Flag flag) {
        archiveMarker = flag.getPosition(); //Update respawn point according to rules.

        int lastVisitedFlag = flags.isEmpty() ? 0 : flags.get(flags.size()-1); //Get last visited flag
        if (lastVisitedFlag+1 == flag.getFlagID()) flags.add(flag.getFlagID()); //If in order, add flag.
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

    public boolean isDead() {
        return this.dead;
    }

    public void setDead(boolean dead) {
        this.dead = dead;
    }

    /**
     * Sets respawn point.
     * @param archiveMarker
     */
    public void setArchiveMarker(Vector2 archiveMarker) {
        this.archiveMarker = archiveMarker;
    }

    /**
     * Returns respawn point
     * @return
     */
    public Vector2 getArchiveMarker() {
        return archiveMarker;
    }
}
