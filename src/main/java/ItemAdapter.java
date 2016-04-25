import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Created by Marceli Baczewski on 25.04.2016.
 */
public class ItemAdapter {


    public ItemAdapter() {
    }

    public String getSumString(List<Item> items) {
        float sum = (float) items.stream().mapToDouble(Item::getPrice).sum();
        return ("Sum:   " + sum);
    }

    public List<String> mapToStrings(List<Item> items) {
        List<String> itemsAsStrings = items.stream().map(Item::toString).collect(Collectors.toList());
        List<String> result = new ArrayList<>(itemsAsStrings);
        return result;
    }
}
