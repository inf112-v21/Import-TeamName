package inf112.skeleton.app.game;

import inf112.skeleton.app.screens.cardsUI.CardUI;

public interface IPhase {

    /**
     * Run method executes the parts of a phase in order.
     * @param mainGame
     * @param cardUI
     *
     */
    void run(MainGame mainGame, CardUI cardUI);

}
