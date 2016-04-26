import org.junit.Before;
import org.junit.Test;


import static org.mockito.Mockito.*;

import java.util.Arrays;
import java.util.Optional;

public class PointOfSaleTest {
    private BarcodeScanner mockedScanner;
    private ItemDatabase mockedDatabase;
    private Display mockedLcd;
    private Printer mockedPrinter;
    private PointOfSale testPoint;

    @Before
    public void setUp() {
        mockedScanner = mock(BarcodeScanner.class);
        mockedDatabase = mock(ItemDatabase.class);
        mockedLcd = mock(Display.class);
        mockedPrinter = mock(Printer.class);
        testPoint = new PointOfSale(mockedDatabase, mockedLcd, mockedPrinter, mockedScanner);
    }

    @Test
    public void testNullExit() {
        when(mockedScanner.scanID()).thenReturn("exit");
        testPoint.checkInput(mockedScanner.scanID());
        verify(mockedLcd, never()).print(null);
        verify(mockedPrinter, never()).printOut(null);
    }

    @Test
    public void testExit() {
        when(mockedScanner.scanID()).thenReturn("Test");
        when(mockedDatabase.getItemById("Test")).thenReturn(Optional.of(new Item("Test", 5F)));
        testPoint.checkInput(mockedScanner.scanID());
        when(mockedScanner.scanID()).thenReturn("exit");
        testPoint.checkInput(mockedScanner.scanID());
        verify(mockedScanner, times(2)).scanID();
        verify(mockedLcd, times(1)).print("Test   5,00");
        verify(mockedLcd, times(1)).print("Sum:   5,00");
        verify(mockedPrinter, times(1)).printOut(Arrays.asList("Test   5,00", "Sum:   5,00"));
    }

    @Test
    public void testNotFound() {
        when(mockedScanner.scanID()).thenReturn("empty");
        when(mockedDatabase.getItemById("empty")).thenReturn(Optional.empty());
        testPoint.checkInput(mockedScanner.scanID());
        verify(mockedLcd, times(1)).print("Product not found");

    }

    @Test
    public void testInvalidCode() {
        when(mockedScanner.scanID()).thenReturn("");
        testPoint.checkInput(mockedScanner.scanID());
        verify(mockedLcd, times(1)).print("Invalid bar-code");

    }

    @Test
    public void testAdding() {
        when(mockedScanner.scanID()).thenReturn("ID01");
        when(mockedDatabase.getItemById("ID01")).thenReturn(Optional.of(new Item("Fries", 7.96F)));
        testPoint.checkInput(mockedScanner.scanID());
        when(mockedScanner.scanID()).thenReturn("ID02");
        when(mockedDatabase.getItemById("ID02")).thenReturn(Optional.of(new Item("Chicken nuggets", 4.04F)));
        testPoint.checkInput(mockedScanner.scanID());
        when(mockedScanner.scanID()).thenReturn("exit");
        testPoint.checkInput(mockedScanner.scanID());
        verify(mockedPrinter, times(1)).printOut(Arrays.asList("Fries   7,96", "Chicken nuggets   4,04", "Sum:   12,00"));

    }
}