/**
 * This interface is a mock of a Barcode scanner which waits for the input through its scanID() method.
 * If we had the Scanner's implementation, the whole program would wait for the scanner to return a String representing an ID.
 * The ID is later checked if it's not equal to some particular Strings(such as an empty string, "exit", or "full exit")
 */
public interface BarcodeScanner {
    /**
     * Waits for an ID to be scanned, returning the ID as a String when it happens.
     * @return returns a String, which is an ID scanned by the method
     */
    String scanID();
}
