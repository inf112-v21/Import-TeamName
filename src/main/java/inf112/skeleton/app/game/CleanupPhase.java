package inf112.skeleton.app.game;

public class CleanupPhase implements IPhase {

    @Override
    public void run() {
        repairs();
        upgrades();
    }

    private void repairs() {

    }

    private void upgrades() {

    }

    /*
    Clean up plan:
        - Repairs & Upgrades
            - Robots at single-wrench space, discard 1 damage token(heal 1). Robots at two-wrench space, discard 1 damage token token and draw 1 Options card.
     */

}
