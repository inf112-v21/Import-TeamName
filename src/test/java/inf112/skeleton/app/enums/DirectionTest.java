package inf112.skeleton.app.enums;

import com.badlogic.gdx.math.Vector2;
import org.junit.Assert;
import org.junit.Test;

public class DirectionTest {


    @Test
    public void DirectionOppositeShouldReturnNorth() {
        Assert.assertEquals(Direction.NORTH, Direction.DirectionOpposite(Direction.SOUTH));
    }

    @Test
    public void DirectionOppositeShouldReturnSouth() {
        Assert.assertEquals(Direction.SOUTH, Direction.DirectionOpposite(Direction.NORTH));
    }

    @Test
    public void DirectionOppositeShouldReturnEast() {
        Assert.assertEquals(Direction.EAST, Direction.DirectionOpposite(Direction.WEST));
    }

    @Test
    public void DirectionOppositeShouldReturnWest() {
        Assert.assertEquals(Direction.WEST, Direction.DirectionOpposite(Direction.EAST));
    }



    @Test
    public void goDirectionShouldReturnCorrectVectorWest() {
        Vector2 westPos = Direction.goDirection(new Vector2(0,0), Direction.WEST);
        Assert.assertEquals(new Vector2(-1,0), westPos);
    }

    @Test
    public void goDirectionShouldReturnCorrectVectorEast() {
        Vector2 eastPos = Direction.goDirection(new Vector2(0,0), Direction.EAST);
        Assert.assertEquals(new Vector2(1,0), eastPos);
    }

    @Test
    public void goDirectionShouldReturnCorrectVectorNorth() {
        Vector2 northPos = Direction.goDirection(new Vector2(0,0), Direction.NORTH);
        Assert.assertEquals(new Vector2(0,1), northPos);
    }

    @Test
    public void goDirectionShouldReturnCorrectVectorSouth() {
        Vector2 southPos = Direction.goDirection(new Vector2(0,0), Direction.SOUTH);
        Assert.assertEquals(new Vector2(0,-1), southPos);
    }

    @Test
    public void goDirectionShouldReturnIncorrectVectorNorth() {
        Vector2 northPos = Direction.goDirection(new Vector2(0,0), Direction.NORTH);
        Assert.assertNotEquals(new Vector2(1,1), northPos);
    }

}
