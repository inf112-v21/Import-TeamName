package inf112.skeleton.app.game;

import inf112.skeleton.app.objects.Actors.Player;
import inf112.skeleton.app.objects.Actors.SimpleRobot;
import inf112.skeleton.app.screens.cardsUI.CardUI;

import static inf112.skeleton.app.game.MainGame.deck;
import static inf112.skeleton.app.game.MainGame.robots;


/**
 * Choose cards
 */
public class ChooseCardsPhase extends Thread {
    Thread wait;

    public void debugRun(CardUI cardUI) {
        Player robot = robots.get(0);
        cardUI.generateCards(robot);
    }

        /**
         *
         * @param cardUI

         */
        public void run(CardUI cardUI)  {
            for (Player robot : robots) {
                cardUI.generateCards(robot);
                try {
                    Thread.sleep(50000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                while (!robot.getProgramSheet().getRegister().chosenCards) {
                        Thread.onSpinWait();
                    }

                robot.getProgramSheet().getRegister().chosenCards = false;

            }

        //
    }

    public void recursiveWait(Player robot) {
            if (!robot.getProgramSheet().getRegister().chosenCards) {
                try {
                    Thread.sleep(5000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
    }

    /**
     * Wait for selection
     * @param robot: Player to wait for
     */
    private void waitForPlayerSelection(Player robot) throws InterruptedException {
        while (!robot.getProgramSheet().getRegister().chosenCards) {
            wait();
            //Thread.sleep(20);
            if (robot.getProgramSheet().getRegister().chosenCards) break;
           // wait.wait(200);

        }
    }
}
