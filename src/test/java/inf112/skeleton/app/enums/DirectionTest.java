package inf112.skeleton.app.enums;

import com.badlogic.gdx.math.Vector2;
import org.junit.*;

import static org.junit.Assert.fail;

/**
 * Tests funcitons in Direction.java.
 */
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
    public void goDirectionShouldFailWhenGivenRotation() {
        try {
            Direction.goDirection(new Vector2(2,2), Direction.AGAINST_CLOCK);
            fail();
        } catch (IllegalArgumentException e) {

        }
    }
}
