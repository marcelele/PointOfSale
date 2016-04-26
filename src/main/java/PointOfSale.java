import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

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

    public void initiateSales() {
        while (true) {
            checkInput(scanner.scanID());
        }
    }

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

    public void exit() {
        if (!items.isEmpty()) {
            List<String> receipt = new ItemAdapter().mapToStrings(items);
            String totalSum = new ItemAdapter().getSumString(items);
            receipt.add(totalSum);
            lcd.print(totalSum);
            printer.printOut(receipt);
            items = new ArrayList<>();
        }

    }

    public void addItem(Item itemToAdd) {
        items.add(itemToAdd);
        lcd.print(itemToAdd.toString());
    }

    public void notFound() {
        lcd.print("Product not found");
    }

    public void invalidCode() {
        lcd.print("Invalid bar-code");
    }

    public void tryAdding(String itemID) {
        Optional<Item> itemToAdd = database.getItemById(itemID);
        if (itemToAdd.isPresent()) addItem(itemToAdd.get());
        else notFound();
    }
}

