package inf112.skeleton.app.game;

import inf112.skeleton.app.objects.Actors.SimpleRobot;

import static inf112.skeleton.app.game.MainGame.deck;
import static inf112.skeleton.app.game.MainGame.robots;


/**
 * Choose cards
 */
public class ChooseCardsPhase implements IPhase {


    @Override
    public void run(MainGame mainGame) {
        for (SimpleRobot robot : robots) {
            /*
            while (!robot.chosenCards) {

            }
            */

            //robot.chosenCards
            // Call on cardUI
            //while(chosenCards) {
            // next;
            //}
        }
        /*
        //Clear robots, choosing cards
        for (robot : robots) {
            robot.program.chooseCard = false;
        }
        */
    }

    //done

}
