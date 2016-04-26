import org.junit.Test;
import java.util.Arrays;
import java.util.List;

import static org.junit.Assert.*;

public class ItemAdapterTest {
    @Test
    public void testSumString() {
        String actual = new ItemAdapter().getSumString(Arrays.asList(new Item("Fries", 7.20F), new Item("Juice", 2.50F), new Item("Bread", 2.30F)));
        String expected = "Sum:   12,00";
        assertEquals(expected, actual);

    }

    @Test
    public void testMapToStrings() {
        List<String> actual = new ItemAdapter().mapToStrings(Arrays.asList(new Item("Fries", 7.20F), new Item("Juice", 2.50F), new Item("Bread", 2.30F)));
        List<String> expected = Arrays.asList("Fries   7,20", "Juice   2,50", "Bread   2,30");
        assertEquals(expected, actual);

    }

}