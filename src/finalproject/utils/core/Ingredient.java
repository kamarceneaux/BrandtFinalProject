package finalproject.utils.core;

public class Ingredient implements Comparable<Ingredient>{
    private String name;

    public Ingredient(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return name;
    }

    @Override
    public int compareTo(Ingredient o) {
        return this.name.compareTo(o.getName());
    }

    public String getName(){
        return name;
    }
}
