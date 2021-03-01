package inf112.skeleton.app.game;

/**
 * Lets the players choose their preferred cards.
 */
public class ProgramRegisterPhase {

    public static void showCards() {
        // Print to console maybe? Easy debugging.
    }

    public static void chooseCards() {
        //Player chooses from given cards.
    }

    public static void discardLeftOverCards() {
        //Discards cards not picked by player
    }

    public static void announceDone() {
        // When a player has finished picking their cards, they will announce done
    }

    /*
    Program Registers plan:
        - Look at your hand and choose five cards you
        - Put cards in the registers on your program sheet, face down, from left to right in the order they'll be executed.
        - Discard any cards you have left over.
        - Announce that you're done.
        - Timer, if one player remains who hasn't called "Done" start the 30 second timer.
            - If the timer runs out before the player finishes, put that player's unused cards face down on the table, and randomly fill in the remaining empty registers.
            -In the case that only one player is programming registers on a given turn (due to the others being powered down or out of game),
                the timer then starts the instant he/she is dealt cards.
     */

    // Maybe add timer later

}
