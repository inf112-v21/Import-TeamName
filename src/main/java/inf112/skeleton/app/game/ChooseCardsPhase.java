package inf112.skeleton.app.game;

import inf112.skeleton.app.objects.Actors.Robot;
import inf112.skeleton.app.screens.cardsUI.CardUI;

import static inf112.skeleton.app.game.MainGame.robots;


/**
 * Choose cards phase class:
 */
public class ChooseCardsPhase extends Thread {


        /** Calls the CardUI generate method for every robot.
         * This will in turn display the cardhand and the register for the player
         * When all players are accounted for as determined by the chosenCards boolean variable,
         * the method initiates next phase
         * @param mainGame
         * @param cardUI
         */
        public void run(MainGame mainGame, CardUI cardUI)  {
            int i = 0;
            for (Robot robot : robots) {
                if (robot.getProgramSheet().getPowerDown() || robot.getProgramSheet().isDead()) continue;
                i++;
                if (!robot.getProgramSheet().getRegister().chosenCards) {
                    System.out.println("#################################");
                    System.out.println("Player number " + i + " 's turn");

                    cardUI.generateCards(robot); // Generate cards for player that has not chosen cards
                    return; // Leave chooseCards
                }
            }
            resetSelection();
            mainGame.completeRegisterPhase.run(mainGame, cardUI);

    }

    /**
     * Iterates through every robot and resets the boolean variable keeping
     * track of whether or not a player has selected his/her cards for a round
     */
    public void resetSelection() {
        for (Robot robot : robots) {
            robot.getProgramSheet().getRegister().chosenCards = false;
        }
    }


}
