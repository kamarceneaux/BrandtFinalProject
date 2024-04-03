package finalproject.utils.core;

import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;


public class Smoothie extends Item{
    private List<Ingredient> ingredients;
    private List<Ingredient> modifiedIngredients;
    private List<String> textToBeOutputted = new ArrayList<>();

    public Smoothie(String name){
        this(6.69, name);
    }

    public Smoothie(String name, List<Ingredient> ingredients){
        this(6.69, name);
        this.ingredients = ingredients;
    }

    public Smoothie(double price, String name) {
        super(price, name, TypeOfItem.SMOOTHIE);
    }

    @Override
    public double getPrice() {
        double price = super.getPrice();
        if(modifiedIngredients != null){
            if(modifiedIngredients.size() > 9){
                int numOver = modifiedIngredients.size() - 9;
                price += numOver * 0.75;
            }
        }
        return price;
    }

    private String priceFromatted(){
        DecimalFormat df = new DecimalFormat("#.##");
        return df.format(getPrice());
    }

    public List<Ingredient> getIngredients() {
        return ingredients;
    }

    public void compareIngredients(){
        List<Ingredient> copyOfIngredients = new ArrayList<>();
        copyOfIngredients.addAll(ingredients);

        if(modifiedIngredients == null){
            setModifiedIngredients(ingredients);
        }

        List<Ingredient> copyOfModifiedIngredients = new ArrayList<>();
        copyOfModifiedIngredients.addAll(modifiedIngredients);

        // Sort the Ingredients
        Collections.sort(copyOfIngredients);
        Collections.sort(copyOfModifiedIngredients);

        int i = 0, j = 0;
        while (i < copyOfIngredients.size() && j < copyOfModifiedIngredients.size()) {
            int comparison = copyOfIngredients.get(i).compareTo(copyOfModifiedIngredients.get(j));
            if (comparison == 0) {
                i++;
                j++;
            } else if (comparison < 0) {
//                System.out.println("Removed Item: " + copyOfIngredients.get(i));
                textToBeOutputted.add(String.format("Removed Item: %s", copyOfIngredients.get(i)));
                i++;
            } else {
//                System.out.println("Added Item: " + copyOfModifiedIngredients.get(j));
                textToBeOutputted.add(String.format("Added Item: %s", copyOfModifiedIngredients.get(j)));
                j++;
            }
        }

        // Print remaining items
        while (i < copyOfIngredients.size()) {
//            System.out.println("Removed Item: " + copyOfIngredients.get(i));
            textToBeOutputted.add(String.format("Removed Item: %s", copyOfIngredients.get(i)));
            i++;
        }
        while (j < copyOfModifiedIngredients.size()) {
//            System.out.println("Added Item: " + copyOfModifiedIngredients.get(j));
            textToBeOutputted.add(String.format("Added Item: %s", copyOfModifiedIngredients.get(j)));
            j++;
        }

        Collections.sort(textToBeOutputted);
    }

    @Override
    public String toString() {
        StringBuilder stringBuilder = new StringBuilder();

        stringBuilder.append("<div>");
        stringBuilder.append("<em>" + getName() + "</em>");
        stringBuilder.append(textToBeOutputted.size() > 0 ? ": ": " ");
        if(textToBeOutputted != null && textToBeOutputted.size() != 0){
            stringBuilder.append("<ul>");
            for (int i = 0; i < textToBeOutputted.size(); i++) {
                String ingredient = textToBeOutputted.get(i);
                if(ingredient.contains("Removed ") || ingredient.contains("Added ")){
                    stringBuilder.append("<li>");
                    stringBuilder.append(ingredient);
                    stringBuilder.append("</li>");
                }
            }
        }

        boolean extraCharge = false;

        if(modifiedIngredients != null){
            for (int i = 0; i < modifiedIngredients.size(); i++) {
                String ingredient = modifiedIngredients.get(i).getName();
                if(i  > 8){
                    stringBuilder.append("<li>");
                    stringBuilder.append("(EXTRA CHARGE) " + ingredient);
                    stringBuilder.append("</li>");
                }
            }
        }
        stringBuilder.append(textToBeOutputted.size() > 0 || extraCharge ? "</ul>": " ");
        stringBuilder.append("<br>Total for Smoothie: $" + priceFromatted() + "</div><br>");

        return stringBuilder.toString();
    }

    public void setModifiedIngredients(List<Ingredient> modifiedIngredients) {
        this.modifiedIngredients = modifiedIngredients;
    }


    public void setIngredients(List<Ingredient> ingredients) {
        this.ingredients = ingredients;
    }

    public List<Ingredient> getModifiedIngredients() {
        return modifiedIngredients;
    }

    @Override
    public boolean equals(Object obj) {
        if(obj instanceof Smoothie){
            Smoothie that = (Smoothie) obj;
            if(this.getName().equals(that.getName())){
                if(this.modifiedIngredients.equals(that.modifiedIngredients)) return true;
                return false;
            }
            return false;
        }
        return false;
    }
}
