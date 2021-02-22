package inf112.skeleton.app.cards;

import inf112.skeleton.app.objects.Actors.Robot;

public interface IProgramCard {


    /**
     *
     * @return: Name of programcard
     */
    String getName();

    /**
     * Perform action
     * @param robot: Robot which performs a given actio
     */
    void action(Robot robot);

}
