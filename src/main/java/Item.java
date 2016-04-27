import java.text.DecimalFormat;

/**
 * This class is a representation of a single Item- it contains its name and price,
 * and overrides a ToString method in order to create a suitable representation for the other
 * classes' needs.
 */
public class Item {

    private String name;
    private float price;

    public Item(String name, float price) {
        this.name = name;
        this.price = price;
    }

    /**
     * Returns the price of an Item. Created especially for TestAdapter purposes.
     * No other getters or setters in the Item class are needed or used.
     * @return Returns a price of the Item.
     */
    public float getPrice() {
        return price;
    }

    /**
     * Overrides a default toString method in order to create a more suitable representation of an Item.
     * In order to properly display the numbers on the LCD Display and Printer, this method uses a dynamically created
     * DecimalFormat object, which formats the price so that it has always 2 decimal places.
     * @return Returns a String of format: "name   1,00" in which "name" is the particular Item's name, and
     * "1,00" is the particular Item's name fixed to 2 decimal places.
     */
    @Override
    public String toString() {
        return (name + "   " + new DecimalFormat("#.00").format(price));
    }

}
