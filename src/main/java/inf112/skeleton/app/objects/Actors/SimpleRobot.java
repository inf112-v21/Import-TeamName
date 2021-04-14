package inf112.skeleton.app.objects.Actors;

import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.maps.tiled.TiledMapTileLayer;
import com.badlogic.gdx.maps.tiled.tiles.StaticTiledMapTile;
import com.badlogic.gdx.math.Vector2;
import inf112.skeleton.app.cards.CardDeck;
import inf112.skeleton.app.enums.Direction;
import inf112.skeleton.app.objects.SimpleObject;

import static inf112.skeleton.app.game.MainGame.gameBoard;
import static inf112.skeleton.app.game.MainGame.robots;

public abstract class SimpleRobot extends SimpleObject implements IActor {

    private Direction lookDirection;
    private ProgramSheet programSheet;
    private boolean chosenCards; // TODO: Track if a robot has chosen cards for ChooseCardsPhase. Should this be put in ProgramSheet instead?
    private final TiledMapTileLayer.Cell playerCellDead;
    private TiledMapTileLayer.Cell playerCellWon;

    private final TiledMapTileLayer.Cell DirectionTextureNORTH, DirectionTextureSOUTH, DirectionTextureEAST, DirectionTextureWEST;

    public SimpleRobot(Vector2 startpos, TextureRegion[][] texture) {
        super(startpos);
        this.lookDirection = Direction.NORTH;
        this.programSheet = new ProgramSheet();
        programSheet.setArchiveMarker(startpos);

        this.playerCellDead = new TiledMapTileLayer.Cell().setTile(new StaticTiledMapTile(texture[0][4]));
        this.playerCellWon = new TiledMapTileLayer.Cell().setTile(new StaticTiledMapTile(texture[0][4])); //TODO: Expand textures to include a 'Win' sprite for picking up a flag.
        
        this.DirectionTextureNORTH = new TiledMapTileLayer.Cell().setTile(new StaticTiledMapTile(texture[0][0]));
        this.DirectionTextureSOUTH = new TiledMapTileLayer.Cell().setTile(new StaticTiledMapTile(texture[0][1]));
        this.DirectionTextureWEST = new TiledMapTileLayer.Cell().setTile(new StaticTiledMapTile(texture[0][2]));
        this.DirectionTextureEAST = new TiledMapTileLayer.Cell().setTile(new StaticTiledMapTile(texture[0][3]));
    }

    /**
     * Move robot forward based on input
     * Recursive method to move robot forward
     * Backwards movement is determined by the MovementCard class
     * @param steps : number of steps to be taken
     */
    public void moveRobot(int steps) {
        TiledMapTileLayer playerTile = (TiledMapTileLayer) gameBoard.getMap().getLayers().get("Player");
        if (steps == 0) return;

        Vector2 pos = getPosition();
        playerTile.setCell((int) pos.x, (int) pos.y, new TiledMapTileLayer.Cell()); // Set empty cell where robot once existed

        //Player & Wall Collision
        playerCollisionHandler(this, pos, lookDirection, false);

        moveRobot(steps - 1);
    }

    /**
     * Accounts for player collision and tries to move robot 1 step in pushDirection.
     * Checks that when player collision occurs, there is an empty space the robots can be pushed onto. If not, robots stand still.
     * @param pos : Location of current robot.
     * @param pushDir : Direction robot wants to move.
     * @param collisionOccurred : If robot is moved due to collision.
     */
    protected boolean playerCollisionHandler(SimpleRobot currentRobot, Vector2 pos, Direction pushDir, Boolean collisionOccurred) {
        //No wall fills the next position
        if (!gameBoard.canGoToTile(pos,pushDir)) return false;

        Vector2 nextPos = Direction.goDirection(pos, pushDir);

        if (occupied(nextPos)) { //Check for player collision
            for (SimpleRobot collidedRobot : robots) { //Find robot we have collided with.
                if (nextPos.equals(collidedRobot.getPosition())) {
                    if (!playerCollisionHandler(collidedRobot, nextPos, pushDir, true)) return false; //Check if collided robot is pushable.
                }
            }
        }
        //Update pos
        currentRobot.setPosition(nextPos);

        if (collisionOccurred) {
            checkPosition(currentRobot); //Check if pushed robot is still alive.
            return true;
        } else {
            return checkPosition(currentRobot);
        }
    }

    /**
     * Checks if position is occupied by an actor.
     * @param pos : Pos to check
     * @return true if position has an actor
     */
    public boolean occupied(Vector2 pos) {
        for (SimpleRobot robot : robots) {
            if (robot.getPosition().equals(pos)) return true;
        }
        return false;
    }

    public void robotLoseLife(SimpleRobot robot){
        if(robot.getProgramSheet().getLife()>1){
            robot.getProgramSheet().loseLife();
            robot.newPosition(robot);
        } else {
            robot.getProgramSheet().setDead(true);
            //Remove dead player from map
            TiledMapTileLayer playerTile = (TiledMapTileLayer) gameBoard.getMap().getLayers().get("Player");
            playerTile.setCell((int) robot.getPosition().x, (int) robot.getPosition().y, new TiledMapTileLayer.Cell()); // Set empty cell where robot once existed
        }
    }

    public void robotTakeDmg(SimpleRobot robot, int amount){
        robot.getProgramSheet().addDamage(amount);
        if(getProgramSheet().getDamage()==0 && !(getProgramSheet().getLife()==0)){
            robot.newPosition(robot);
        }
    }

    public void newPosition(SimpleRobot robot){
        Vector2 pos = robot.getProgramSheet().getArchiveMarker();
        robot.setPosition(pos);
    }

    /**
     * Check at after each step the robot takes, it is still alive.
     * @param robot : Robot to perform check on.
     * @return True if robot can still move.
     */
    public boolean checkPosition(SimpleRobot robot) {
        Vector2 playerPos = robot.getPosition();
        //If player is on Pit or outside map. Set player to dead.
        if (gameBoard.isOnBoard(playerPos) || gameBoard.isPosAPit(playerPos)) {
            robotLoseLife(robot);
            //TODO: Remove this dead robot from map.
            // Must happen here, cannot be in CompleteRegisterPhase - Endre
            // Maybe move dead player to x:-100,y:-100. Then after all cards are executed/loops are done. Remove dead players entirely.
            //      - To prevent potential execution errors when modifying lists used in loops.

            return false;
        }
        return true;
    }

    public void dealCards(CardDeck deck) {this.programSheet.dealCards();}

    @Override
    public ProgramSheet getProgramSheet() {
        return this.programSheet;
    }

    public TiledMapTileLayer.Cell getPlayerCell() {
        if (this.lookDirection == Direction.NORTH) {
            return this.DirectionTextureNORTH;
        } else if (this.lookDirection == Direction.SOUTH) {
            return this.DirectionTextureSOUTH;
        } else if (this.lookDirection == Direction.EAST) {
            return this.DirectionTextureEAST;
        } else if (this.lookDirection == Direction.WEST) {
            return this.DirectionTextureWEST;
        }
         throw new IllegalArgumentException("LookDirection of " + this + " robot did not match any of the cardinal directions.");
    }

    public TiledMapTileLayer.Cell getPlayerCellDead() {
        return playerCellDead;
    }

    public TiledMapTileLayer.Cell getPlayerCellWon() {
        return getPlayerCell();
    }

    @Override
    public Direction getLookDirection() {
        return this.lookDirection;
    }

    @Override
    public void setLookDirection(Direction direction) {
        this.lookDirection = direction;
    }

    @Override
    public void rotate(int clockwiseTurns) {
        for (int i = 0; i < clockwiseTurns; i++) {
            setLookDirection(Direction.DirectionClockwise(getLookDirection()));
        }

    }

}
