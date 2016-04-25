/**
 * Created by Marceli Baczewski on 25.04.2016.
 */
public class Item {

    private String name;
    private Float price;

    public Item(String name, Float price){
        this.name = name;
        this.price = price;
    }
    @Override
    public String toString(){
        return name + "  " + price;
    }
}
