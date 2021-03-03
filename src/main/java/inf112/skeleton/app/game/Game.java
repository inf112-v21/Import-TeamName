package inf112.skeleton.app.game;

public class Game {

    /**
     * Setup:
     * - Give map to Board.java
     * - Create Program Card objects
    */
    public static void setup() {

    }

    /**
     * Game Loop
     * Executes the phases in correct order
    */
    public static void gameLoop() {

        IPhase dealCardsPhase = new DealCardsPhase();
        IPhase programRegisterPhase = new ProgramRegisterPhase();
        IPhase announcePowerDownPhase = new AnnouncePowerDownPhase();
        IPhase completeRegisterPhase = new CompleteRegisterPhase();
        IPhase cleanupPhase = new CleanupPhase();

        while (true) { //TODO: If game is over, end loop.
            dealCardsPhase.run();
            programRegisterPhase.run();
            announcePowerDownPhase.run();
            completeRegisterPhase.run();
            cleanupPhase.run();
        }

    }

    /**
     * Nachspiel
     * Things to do after the game loop is finished
     */
    public static void end() {

    }

}
