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
        this.damageTokens = 0;
        this.lifeTokens = 0;
        this.powerDown = false;
        this.flags = new ArrayList<>();
        this.dead = false;
        this.hand = new CardHand(9);
        this.register = new Register(); //TODO: Is this correct? Added for testing CompleteRegisterPhase -Endre
    }

    /**
     * Adds or subtracts damage tokens from an actor's program sheet.
     * @param amount The amount of tokens to be added. (If negative, remove tokens)
     */
    public void addDamage(int amount) {
        this.damageTokens += amount;
        if (this.damageTokens > this.lifeTokens) this.dead = true;
        if (this.damageTokens > 10) {
            this.damageTokens = 10;
        }

        if (this.damageTokens < 0) {
            this.damageTokens = 0;
        }
        this.hand.setNumCardsDeck(9 - this.damageTokens);
    }

    /**
     * Deals the correct amount of cards to an actor, based on its damage tokens.
     * Method is called every round.
     */
    public void dealCards(CardDeck deck) {
        this.hand = new CardHand(9 - this.damageTokens);
    }

    /**
     * Adds or subtracts life tokens from an actor's program sheet.
     * @param amount The amount of tokens to be added. (If negative, remove tokens)
     */
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
     * Gets the cards that will be executed in CompleteRegisterPhase.
     * @return register from actor's program sheet.
     */
    public Register getRegister() {
        return this.register;
    }

    /**
     * Gets an actor's card hand.
     * @return actor's card hand.
     */
    public CardHand getCardHand() {
        return this.hand;
    }

    /**
     * Gets the amount of flags an actor has visited.
     * @return amount of flags in this.flags.
     */
    public int getNumberOfFlags() {
        return this.flags.size();
    }

    /**
     * Tries to add flag to visited flags.
     * Can only add if the flag id is equal to last visited flag +1. --> Only add flags in order.
     * @param flag
     */
    public void addFlag(Flag flag) {
        this.archiveMarker = flag.getPosition(); //Update respawn point according to rules.

        int lastVisitedFlag = this.flags.isEmpty() ? 0 : this.flags.get(this.flags.size()-1); //Get last visited flag
        if (lastVisitedFlag+1 == flag.getFlagID() && !this.flags.contains(flag.getFlagID())) this.flags.add(flag.getFlagID()); //If in order, add flag.
    }

    /**
     * Gets the amount of damage tokens on an actor's program sheet.
     * @return int - amount of damage tokens.
     */
    public int getDamage() {
        return this.damageTokens;
    }

    /**
     * Gets the amount of life tokens on an actor's program sheet.
     * @return int - amount of life tokens.
     */
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

    /**
     * Sets the boolean value of this.dead. Decides whether an actor is dead or alive.
     * @param dead boolean - true if dead, false if alive.
     */
    public void setDead(boolean dead) {
        this.dead = dead;
    }

    /**
     * Sets an actor's respawn point.
     * @param archiveMarker Vector2
     */
    public void setArchiveMarker(Vector2 archiveMarker) {
        this.archiveMarker = archiveMarker;
    }

    /**
     * Gets an actor's respawn point.
     * @return Vector2
     */
    public Vector2 getArchiveMarker() {
        return this.archiveMarker;
    }
}
