import java.util.Optional;

/**
 * This interface is a mock of a database from which the PointOfSale class would get objects and add them to a List of Item objects,
 * which represents already scanned items.
 * The database may not find an item, and when that happens, it would return a null value.
 * To prevent unpredictable behavior and cumbersome null checks, the Item is wrapped in an Optional container.
 */
public interface ItemDatabase {
    /**
     * Attempts to get an item from the database, returning an Optional container with an Item or null value inside it,
     * depending on whether the ID has been matched with an Item object
     * @param itemID
     * String representing an Item's ID in database
     * @return Depending on whether the database has found an Item matching the ID or not, it either:
     * -returns an Optional with an Item object inside it
     * -returns an Optional with a null value inside it
     */
    Optional<Item> getItemById(String itemID);
}
