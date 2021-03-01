package inf112.skeleton.app.game;

public class Game {

    /**
       Setup:
            - Give map to Board.java
            - Create Program Card objects
    */
    public static void setup() {

    }

    /**
     * Game Loop
     * Executes the phases in correct order
    */
    public static void gameLoop() {

        while (true) { //TODO: If game is over, end loop.
            DealCardsPhase.run();
            ProgramRegisterPhase.run();
            AnnouncePowerDownPhase.run();
            CompleteRegisterPhase.run();
            CleanupPhase.run();
        }

    }

    /**
     * Nachspiel
     * Things to do after the game loop is finished
     */
    public static void end() {

    }

}
