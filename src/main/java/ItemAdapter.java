import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class ItemAdapter {


    public ItemAdapter() {
    }

    public String getSumString(List<Item> items) {
        float sum = (float) items.stream().mapToDouble(i -> i.getPrice()).sum();
        return ("Sum:   " + sum);
    }

    public List<String> mapToStrings(List<Item> items) {
        return items.stream().map(i -> i.toString()).collect(Collectors.toList());
    }
}
