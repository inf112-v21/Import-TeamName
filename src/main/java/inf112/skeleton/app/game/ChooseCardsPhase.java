package inf112.skeleton.app.game;

import inf112.skeleton.app.objects.Actors.Player;
import inf112.skeleton.app.objects.Actors.SimpleRobot;
import inf112.skeleton.app.screens.cardsUI.CardUI;

import static inf112.skeleton.app.game.MainGame.deck;
import static inf112.skeleton.app.game.MainGame.robots;


/**
 * Choose cards
 */
public class ChooseCardsPhase {


    public void debugRun(CardUI cardUI) {
        Player robot = robots.get(0);
        cardUI.generateCards(robot);
    }

    public void run(CardUI cardUI) throws InterruptedException {
        for (Player robot : robots) {
            cardUI.generateCards(robot);
           while (!robot.getProgramSheet().getRegister().chosenCards) { // Wait for the robot to choose cards
              // try
               //wait();
            }
            //cardUI.clearTable();
            robot.getProgramSheet().getRegister().chosenCards = false;

        }


    }
}
