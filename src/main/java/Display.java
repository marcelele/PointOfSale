/**
 * This interface is a mock of an LCD Display, which would display:
 * - A product's name and price when a product is scanned
 * - A message "Product not found" when the product is not found in the database
 * - A message "Invalid bar-code" when the code scanned is empty
 * - A total sum when "exit" is input
 */
public interface Display {
    /**
     * Prints out a String passed as a parameter into the LCD Display
     * @param string A String object passed as an argument
     */
    void print(String string);
}
