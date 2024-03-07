package finalproject.utils.core;

import java.util.List;

public class Cart {
    private List<Item> items;
    private double total = 0;

    public double getTotal(){
        for (int i = 0; i < items.size(); i++) {
            total += items.get(i).getPrice();
        }
        return total;
    }

}