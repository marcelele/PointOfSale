import java.text.DecimalFormat;
import java.util.List;
import java.util.stream.Collectors;

/**
 * This class is responsible for mapping the List of Items to Strings as well as
 * calculating the total Sum to be paid and mapping it into a String
 * for representation purposes
 */
public class ItemAdapter {

    /**
     * Creates a String of a total sum to be paid for, used as a parameter in Display class,
     * as well as being added to the List of Strings after finishing scanning the products.
     * @param items A List of Item objects
     * @return Returns a String containing "Sum:   " + the total price to be paid
     */
    public String getSumString(List<Item> items) {
        float sum = (float) items.stream().mapToDouble(i -> i.getPrice()).sum();
        return ("Sum:   " + new DecimalFormat("#.00").format(sum));
    }

    /**
     * Creates a List of Strings returned by toString() methods of Item objects contained in passed List
     * @param items A List of Item objects
     * @return Returns a List of Strings returned by toString() methods of Item objects
     */
    public List<String> mapToStrings(List<Item> items) {
        return items.stream().map(i -> i.toString()).collect(Collectors.toList());
    }
}
