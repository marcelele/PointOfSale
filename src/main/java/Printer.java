import java.util.List;

/**
 * This interface is a mock of an output device, which would act as a receipt printer:
 * after finishing scanning products on a single sale, it prints out a receipt.
 * In case of our program, the receipt would be a list of all scanned items
 * plus the "sum:   " and an amount to be paid.
 */
public interface Printer {
    /**
     * Prints out a List of Strings by the printer
     * @param strings A List of String objects to be printed out one-per-line
     */
    void printOut(List<String> strings);

}
