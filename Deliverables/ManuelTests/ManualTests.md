## Manual Tests
This document contains all manual tests for Import-TeamName's version of the RoboRally Game.

# Graphics and player figures load
The graphics of the game loads when the game is launched (23.04.21)
1. Launch the game.
2. In the main menu screen: click on the Offline button to get to the game settings screen.
3. In the game settings screen: select 'x' number of players
4. In the game settings screen: click on Exchange

If you see a board with 'x' number of player figures on it and nine cards beneath it, the test is successful. Otherwise, it failed.

# Exit game
Closing the game window. (23.04.21)
1. Launch the game.
2. In the main menu screen: click on the Offline button to get to the game settings screen.
3. In the game screen: click on the 'x' in the top right corner. 

If the game window closes, the test is successful. Otherwise, it failed.

# Exit out of and go back in menu
Going back in the menu and closing the window from menu. (23.04.21)
1. Launch the game.
2. In the main menu screen: click on the Offline button to get to the game settings screen.
3. In the game settings screen: click on the back button to get back to the main menu.
4. In the main menu screen: click on the Online button to get to the find game screen.
5. In the find game screen: click on the back button to get back to the main menu.
6. In the main menu screen: click on the exit button.

If all the steps are completed and the game window closes, the test is successful. Otherwise, it failed.

# Movement of player
Move the player figure by clicking on the cards (23.04.21)
1. Launch the game.
2. In the main menu screen: click on the Offline button to go to the game settings screen.
3. In the game settings screen: click on Exchange
4. In the mainGame screen: click on five cards that does not lead to death, and then the green arrow button.

If the player figure moves as predicted from the cards, the test is successful. Otherwise, it failed.

# Death of player
The player dissapears after 3 deaths (23.04.21)
1. Launch the game.
2. In the main menu screen: click on the Offline button to go to the game settings screen.
3. In the game settings screen: click on Exchange
4. In the mainGame screen: click on five cards that does lead to death, and then the green arrow button. Reapeat step 4 three times.

If the player figure is no longer on the map, the test is successful. Otherwise, it failed.

# Online
See other player move on board (23.04.21)
1. Launch the game.
2. In the main menu screen: click on the Online button to get to the find game screen.
3. In the find game screen: click on host.
4. Launch another instance of the game.
5. In the main menu screen: click on the Online button to get to the find game screen.
6. In the find game screen: click on the Find button, then the Join button.
7. Move the player figures in both windows using 'WASD'.

If the player figures move identically in both windows, the test is successful, otherwise it fails.
