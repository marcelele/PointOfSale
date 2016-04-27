import org.junit.Before;
import org.junit.Test;


import static org.mockito.Mockito.*;

import java.util.Arrays;
import java.util.Optional;

/**
 * Tests the PointOfSale class
 */
public class PointOfSaleTest {
    private BarcodeScanner mockedScanner;
    private ItemDatabase mockedDatabase;
    private Display mockedLcd;
    private Printer mockedPrinter;
    private PointOfSale testPoint;

    /**
     * Sets up the necessary mock objects and construct the testPoint.
     */
    @Before
    public void setUp() {
        mockedScanner = mock(BarcodeScanner.class);
        mockedDatabase = mock(ItemDatabase.class);
        mockedLcd = mock(Display.class);
        mockedPrinter = mock(Printer.class);
        testPoint = new PointOfSale(mockedDatabase, mockedLcd, mockedPrinter, mockedScanner);
    }

    /**
     * Tests an exit input with an empty List of Items,
     * veryfing that no printing methods have been called
     */
    @Test
    public void testNullExit() {

        when(mockedScanner.scanID()).thenReturn("exit");
        testPoint.checkInput(mockedScanner.scanID());

        verify(mockedLcd, never()).print(anyObject());
        verify(mockedPrinter, never()).printOut(anyObject());
    }

    /**
     * Tests if an LCD Display is working properly
     * verifying if it printed the expected Strings
     */
    @Test
    public void testLcd() {

        when(mockedScanner.scanID()).thenReturn("Test");
        when(mockedDatabase.getItemById("Test")).thenReturn(Optional.of(new Item("Test", 5F)));
        testPoint.checkInput(mockedScanner.scanID());

        when(mockedScanner.scanID()).thenReturn("exit");
        testPoint.checkInput(mockedScanner.scanID());

        verify(mockedLcd).print("Test   5,00");
        verify(mockedLcd).print("Sum:   5,00");
    }

    /**
     * Tests if a Printer is working properly
     * verifying if it printed the expected List of Strings after adding 2 objects and passing "exit" as input
     */
    @Test
    public void testPrinter() {

        when(mockedScanner.scanID()).thenReturn("ID01");
        when(mockedDatabase.getItemById("ID01")).thenReturn(Optional.of(new Item("Fries", 7.96F)));
        testPoint.checkInput(mockedScanner.scanID());

        when(mockedScanner.scanID()).thenReturn("ID02");
        when(mockedDatabase.getItemById("ID02")).thenReturn(Optional.of(new Item("Chicken nuggets", 4.04F)));
        testPoint.checkInput(mockedScanner.scanID());

        when(mockedScanner.scanID()).thenReturn("exit");
        testPoint.checkInput(mockedScanner.scanID());

        verify(mockedPrinter).printOut(Arrays.asList("Fries   7,96", "Chicken nuggets   4,04", "Sum:   12,00"));

    }

    @Test
    public void testNotFound() {

        when(mockedScanner.scanID()).thenReturn("empty");
        when(mockedDatabase.getItemById("empty")).thenReturn(Optional.empty());

        testPoint.checkInput(mockedScanner.scanID());

        verify(mockedLcd).print("Product not found");

    }

    @Test
    public void testInvalidCode() {

        when(mockedScanner.scanID()).thenReturn("");

        testPoint.checkInput(mockedScanner.scanID());

        verify(mockedLcd).print("Invalid bar-code");

    }

}