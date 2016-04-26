import java.text.DecimalFormat;
import java.util.List;
import java.util.stream.Collectors;

public class ItemAdapter {



    public String getSumString(List<Item> items) {
        float sum = (float) items.stream().mapToDouble(i -> i.getPrice()).sum();
        return ("Sum:   " + new DecimalFormat("#.00").format(sum));
    }

    public List<String> mapToStrings(List<Item> items) {
        return items.stream().map(i -> i.toString()).collect(Collectors.toList());
    }
}
