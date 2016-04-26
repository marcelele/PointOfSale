import java.text.DecimalFormat;

public class Item {

    private String name;
    private float price;

    public Item() {
        name = "";
        price = 0;
    }

    public Item(String name, float price) {
        this.name = name;
        this.price = price;
    }

    public float getPrice() {
        return price;
    }

    @Override
    public String toString() {
        return (name + "   " + new DecimalFormat("#.00").format(price));
    }

}
