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

    @Override
    public boolean equals(Object obj) {
        if(obj instanceof Ingredient){
            Ingredient that = (Ingredient) obj;
            if(this.name.equals(that.name)){
                return true;
            }else {
                return false;
            }
        }
        return false;
    }

    public String getName(){
        return name;
    }
}
