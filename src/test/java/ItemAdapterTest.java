import org.junit.Test;

import java.util.Arrays;
import java.util.List;

import static org.junit.Assert.*;

/**
 * Tests methods of an ItemAdapter class
 */
public class ItemAdapterTest {
    /**
     * Tests if the total sum String created by ItemAdapter's "getSumString()" method is equal to the expected String
     */
    @Test
    public void testSumString() {
        String actual = new ItemAdapter().getSumString(Arrays.asList(new Item("Fries", 7.20F), new Item("Juice", 2.50F), new Item("Bread", 2.30F)));
        String expected = "Sum:   12,00";
        assertEquals(expected, actual);

    }

    /**
     * Tests if the List of Strings created by ItemAdapter's "mapToStrings()" method is equal to the expected List
     */
    @Test
    public void testMapToStrings() {
        List<String> actual = new ItemAdapter().mapToStrings(Arrays.asList(new Item("Fries", 7.20F), new Item("Juice", 2.50F), new Item("Bread", 2.30F)));
        List<String> expected = Arrays.asList("Fries   7,20", "Juice   2,50", "Bread   2,30");
        assertEquals(expected, actual);

    }

}