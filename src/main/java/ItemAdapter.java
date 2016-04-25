import java.util.ArrayList;

/**
 * Created by Marceli Baczewski on 25.04.2016.
 */
public class ItemAdapter {


    public ItemAdapter(){

    }

    public ArrayList<String> adapt(ArrayList<Item> items) {
        ArrayList<String> products = new ArrayList<String>();
        int counter = 0;
        for(Item i : items){
            products.add(counter, items.get(counter).toString());
            counter++;
        }
        return products;
    }
}
