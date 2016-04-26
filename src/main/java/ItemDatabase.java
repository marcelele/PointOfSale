import java.util.List;
import java.util.Optional;

public interface ItemDatabase {
    Optional<Item> getItemById(String itemID);
}
