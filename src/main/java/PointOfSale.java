import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

/**
 * This class is an implementation of a single point of sale. Through its methods, it provides a functionality needed
 * for a point of sale to function properly, given that the IO device and Database would function properly.
 */
public class PointOfSale {
    private List<Item> items;
    private ItemDatabase database;
    private Display lcd;
    private Printer printer;
    private BarcodeScanner scanner;

    public PointOfSale(ItemDatabase database, Display lcd, Printer printer, BarcodeScanner scanner) {
        items = new ArrayList<>();
        this.database = database;
        this.lcd = lcd;
        this.printer = printer;
        this.scanner = scanner;
    }

    /**
     * Having the complete implementation of the Database and IO devices, all we would need to do is run this method,
     * which would keep the program in a constant loop, constantly waiting for the Barcode scanner's input and taking actions
     * depending on the ID's value. The program would exit, if an ID returned by the scanner would equal "full exit", which would be
     * returned in case of turning off the module.
     */
    public void initiateSales() {
        while (true) {
            checkInput(scanner.scanID());
        }
    }

    /**
     * Checks the value of the input String representing an object's ID. In case a
     * @param itemID A string passed from the BarcodeScanner
     */
    public void checkInput(String itemID) {
        switch (itemID) {
            case "":
                invalidCode();
                break;
            case "exit":
                exit();
                break;
            case "full exit":
                System.exit(0);
                break;
            default:
                tryAdding(itemID);
                break;
        }
    }

    /**
     * In case of an "exit" input, the method checks if the Item collection is empty.
     * If it's not, then it creates a receipt (a List of Strings), in which it stores every Item's toString representation.
     * It also creates a totalSum String, adding it to the receipt and printing it out on an LCD Display .
     * After that, it prints out on a Printer the whole receipt with the totalSum on its end, and clears the items List.
     */
    private void exit() {
        if (!items.isEmpty()) {
            List<String> receipt = new ItemAdapter().mapToStrings(items);
            String totalSum = new ItemAdapter().getSumString(items);
            receipt.add(totalSum);
            lcd.print(totalSum);
            printer.printOut(receipt);
            items.clear();
        }

    }

    /**
     * Tries getting and adding an Item object.
     * If the object is present in an Optional container, it calls the addItem method.
     * If not, it prints out a "Product not found" message on the LCD display.
     * @param itemID A String representing the scanned ID.
     */
    private void tryAdding(String itemID) {
        Optional<Item> itemToAdd = database.getItemById(itemID);
        if (itemToAdd.isPresent()) addItem(itemToAdd.get());
        else notFound();
    }

    /**
     * Adds an item to the List of items.
     * @param itemToAdd Item Object passed as a parameter
     */
    private void addItem(Item itemToAdd) {
        items.add(itemToAdd);
        lcd.print(itemToAdd.toString());
    }

    /**
     * Prints out a "Product not found" message on the LCD display.
     */
    private void notFound() {
        lcd.print("Product not found");
    }

    /**
     * Prints out a "Invalid bar-code" message on the LCD display.
     */
    private void invalidCode() {
        lcd.print("Invalid bar-code");
    }

}

