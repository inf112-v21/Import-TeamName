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




    public void debugRun(CardUI cardUI) {

        Player robot = robots.get(0);
        cardUI.generateCards(robot);
    }

        /**
         *
         * @param mainGame
         * @param cardUI

         */
        public void run(MainGame mainGame, CardUI cardUI)  {

            int i = 0;
            for (Player robot: robots) {
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

    public void resetSelection() {
        for (Player robot: robots) {
            robot.getProgramSheet().getRegister().chosenCards = false;
        }
    }


}
