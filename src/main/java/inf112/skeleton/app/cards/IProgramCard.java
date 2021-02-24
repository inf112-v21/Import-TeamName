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
     * @param robot: Robot which performs a given action
     */
    void action(Robot robot);


    int getPriority();

    CardType getType();

}
