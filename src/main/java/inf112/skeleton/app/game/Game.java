package inf112.skeleton.app.game;

import inf112.skeleton.app.objects.Actors.IActor;
import inf112.skeleton.app.objects.Actors.Player;
import inf112.skeleton.app.objects.Actors.SimpleRobot;

import java.awt.*;
import java.util.ArrayList;


public class Game {


   public static ArrayList<SimpleRobot> robots;

    /**
     * Constructor method
     */
    public Game(int numPlayers) {
        robots = new ArrayList<>();


    }

    /**
     * Setup:
     * - Initialize robots Arraylist
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

    public void setNumPlayers(int numPlayers) {
        for (int i = 0; i < numPlayers; i++) {
            /**
             * Set to correct start positions
             */
            //Player robot = new Player()
            // robots.add(robot);
        }
    }

}
