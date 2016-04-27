import org.junit.Test;

import static org.junit.Assert.*;

/**
 * Tests the Item's toString representation correctness
 */
public class ItemTest {

    @Test
    public void testToString() {
        assertEquals("Fries   7,20", new Item("Fries", 7.20F).toString());
    }

}