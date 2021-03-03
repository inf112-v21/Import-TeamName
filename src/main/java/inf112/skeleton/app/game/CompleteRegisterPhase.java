package inf112.skeleton.app.game;

public class CompleteRegisterPhase implements IPhase {

    @Override
    public void run() {
        revealProgramCards();
        executeProgramCards();
        boardElementsMove();
        lasersFire();
    }

    private void revealProgramCards() {

    }

    private void executeProgramCards() {

    }

    private void boardElementsMove() {

    }

    private void lasersFire() {

    }

    /*
    CompleteRegister plan:
        - Reveal Program Cards
            - Each player reveals his or her program card for that register at the same time.
        - Execute Program Cards (Robots Move)
            - Move each robot as its program card indicates.
            - Note card priority
            - Resolve interactions -> Hit Wall?, other Player?, touch Flag? Repair Sites.
        - Board Elements Move
            1. Express conveyor belts move 1 space.
            2. All conveyor belts move 1 space.
            3. Pushers push if active.
            4. Gear rotate 90 degrees.
        - Lasers Fire
        - Touch Checkpoints
     */

}
