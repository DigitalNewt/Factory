/**
 * Created by Brent Baker on 1/31/16.
 */

import org.junit.Test;
import static org.junit.Assert.*;

public class TestRange {

    @Test
    public void testInRangeRangeTest() {
        Range r = new RangeImpl();
        assertTrue(r.newRange(1,5).isIn(3));
    }

    @Test
    public void testNotInRangeRangeTest() {
        Range r = new RangeImpl();
        assertFalse(r.newRange(1,5).isIn(6));
    }
}
