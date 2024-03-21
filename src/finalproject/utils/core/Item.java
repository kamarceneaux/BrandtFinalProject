package finalproject.utils.core;

/**
 * Represents the basic function of every item a user can purchase or buy.
 */
public class Item {
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

    public void setPrice(double price) {
        this.price = price;
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

    public TypeOfItem getType() {
        return type;
    }

    @Override
    public String toString() {
        return String.format("%s: $%.2f", name, price);
    }
}
