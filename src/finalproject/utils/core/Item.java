package finalproject.utils.core;

/**
 * Represents the basic function of every item a user can purchase or buy.
 */
public class Item implements Comparable<Item> {
    private double price;
    private String description;
    private String name;
    private TypeOfItem type;

    public Item(double price, String name, TypeOfItem type){
        this(price, name, "", type);
    }

    public Item(double price, String name, String description, TypeOfItem type) {
        this.price = price;
        this.description = description;
        this.name = name;
        this.type = type;
    }

    public double getPrice() {
        return price;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return String.format("%s: $%.2f<br>", name, price);
    }

    @Override
    public int hashCode() {
        if(name.length() == 0){
            return 0;
        }
        return (int) price;
    }

    @Override
    public boolean equals(Object obj) {
        if(obj instanceof Item){
            Item that = (Item) obj;
            if(this.name.equals(that.name) && this.price == that.price && this.type.equals(that.type)){
                return true;
            }else{
                return false;
            }
        }
        return false;
    }

    @Override
    public int compareTo(Item o) {
        return this.name.compareTo(o.getName());
    }
}
