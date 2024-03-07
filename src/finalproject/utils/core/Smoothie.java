package finalproject.utils.core;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class Smoothie extends Item{
    private List<Ingredient> ingredients;

    public Smoothie(double price, String name) {
        super(price, name, TypeOfItem.SMOOTHIE);
    }

    public Smoothie(double price, String name, String description) {
        super(price, name, description, TypeOfItem.SMOOTHIE);
    }

    public void addIngredient(Ingredient ingredient){
        ingredients.add(ingredient);
    }

    public void addIngredients(List<Ingredient> ingredientsToBeAdded){
        for (int i = 0; i < ingredientsToBeAdded.size(); i++) {
            addIngredient(ingredientsToBeAdded.get(i));
        }
    }

    public void removeIngredient(String ingredient){
        // Checks for all the ingredients in a menu item.
        Set<Ingredient> allPossibleIngredients = new HashSet<>(ingredients);

        if(allPossibleIngredients.contains(ingredient)){
            for (int i = 0; i < ingredients.size(); i++) {
                if(ingredient.equals(ingredients.get(i))){
                    ingredients.remove(i);
                }
            }
        }
    }

}
