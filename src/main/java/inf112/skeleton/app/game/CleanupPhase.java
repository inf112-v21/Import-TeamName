package inf112.skeleton.app.game;

import inf112.skeleton.app.objects.Actors.Robot;
import inf112.skeleton.app.objects.TileObjects.RepairSite;
import inf112.skeleton.app.screens.utilities.CardUI;

import static inf112.skeleton.app.game.MainGame.gameBoard;
import static inf112.skeleton.app.game.MainGame.robots;

public class CleanupPhase {


    /**
     * Cleanup every robot.
     * Called every roundd
     * @param mainGame: instance of mainGame
     * @param cardUI: instance of cardUI
     */
    public void run(MainGame mainGame, CardUI cardUI) {
        repairs();
        mainGame.startGameRound(cardUI);
    }

    /**
     * Perform repairs for all robots
     */
    private void repairs() {
        for (Robot robot : robots) {
            if (robot.getProgramSheet().isDead()) continue;
            if (gameBoard.isPosARepaiSite(robot.getPosition())) {
                RepairSite repair = (RepairSite) gameBoard.getNonWallTileOnPos(robot.getPosition());
                robot.getProgramSheet().setArchiveMarker(repair.getPosition());
                robot.getProgramSheet().addDamage(-1 * repair.getStrength()); //Repair 1 damageToken
            }
        }
    }



}
