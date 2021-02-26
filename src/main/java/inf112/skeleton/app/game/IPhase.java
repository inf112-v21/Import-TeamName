package inf112.skeleton.app.game;

public interface IPhase {

    //Deal cards
    void shuffleCards();
    void dealCards();



    // Program Register
    void showCards(); //Print to console maybe? Easy debugging.
    void chooseCards(); //Player chooses from given cards.
    void discardLeftOverCards(); //Discards cards not picked by player
    void announceDone();
        //Timer maybe later


    // Power Down
    void declarePowerdownIntention(); //If no, do nothing.




    // Complete Register


    // CleanUp

/*
      Plan:
            Deal cards
                - Shuffle deck.
                - If robot doesn't have a Damage token --> Deal 9 cards.
                        - If robot has and damage, give out 9 cards - damage taken.
                                - If more than5 damage tokens --> See "Locking Register".
                - Don't show cards until all hands have been dealt.


            Program Registers
                - Look at your hand and choose five cards you
                - Put cards in the registers on your program sheet, face down, from left to right in the order they'll be executed.
                - Discard any cards you have left over.
                - Announce that you're done.
                - Timer, if one player remains who hasn't called "Done" start the 30 second timer.
                    - If the timer runs out before the player finishes, put that player's unused cards face down on the table, and randomly fill in the remaining empty registers.
                    -In the case that only one player is programming registers on a given turn (due to the others being powered down or out of game),
                        the timer then starts the instant he/she is dealt cards.

            PowerDown
                - Declare if you want to power down or continue running.
                - If power down:
                    - Next turn, discard all damage tokens.
                    - Discard all given program cards.
                    - Robot cannot move on its own. If it stands on a conveyor/Pusher/Other enemy robot, robot will still be moved.

                * Nice to have
                        - If a player is killed when powered down. Player may re-enter the game, either powered down or not.

            CompleteRegister
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

            Clean up
                - Repairs & Upgrades
                    - Robots at single-wrench space, discard 1 damage token(heal 1). Robots at two-wrench space, discard 1 damage token token and draw 1 Options card.
 */



/*
        Nice to have:
            - See other players program cards.
            - Tacos:)
 */



}
