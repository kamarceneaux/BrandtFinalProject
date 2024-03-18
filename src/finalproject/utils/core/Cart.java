package finalproject.utils.core;

import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.List;

public class Cart {
    private List<Item> items = new ArrayList<>();
    private double total = getTotal();

    public double getTotal(){
        total = 0;
        for (int i = 0; i < items.size(); i++) {
            total += items.get(i).getPrice();
        }
        total = Double.parseDouble(String.format("%.2f", total));
        return total;
    }

    public void addItem(Item i){
        items.add(i);
        getTotal();
    }

    private String priceFromatted(){
        DecimalFormat df = new DecimalFormat("#.##");
        return df.format(total);
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("<html>");

        for (int i = 0; i < items.size(); i++) {
            sb.append(items.get(i).toString());
        }
        sb.append("Total for the cart: $" + priceFromatted());
        sb.append("</html>");

        return sb.toString();
    }
}