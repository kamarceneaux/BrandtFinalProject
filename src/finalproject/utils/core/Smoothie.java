package finalproject.utils.core;

import java.util.HashSet;
import java.util.List;
import java.util.Set;



public class Smoothie extends Item{
    private List<Ingredient> ingredients;
    private List<Ingredient> allIngredients = SmoothieManager.ALL_POSSIBLE_INGREDIENTS;

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

    public void addIngredient(Ingredient ingredient){
        boolean canAdd = true;
        for (int i = 0; i < ingredients.size(); i++) {
            Ingredient ingreidentToCompare = ingredients.get(i);
            if(ingredient.getName().equals(ingreidentToCompare.getName())){
                canAdd = false;
            }
        }
        if(canAdd){
            ingredients.add(ingredient);
        }
    }

//    public void addIngredients(List<Ingredient> ingredientsToBeAdded){
//        for (int i = 0; i < ingredientsToBeAdded.size(); i++) {
//            addIngredient(ingredientsToBeAdded.get(i));
//        }
//    }

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

//    public void removeIngredient(String ingredient, List<Ingredient> ingredients){
//        // Checks for all the ingredients in a menu item.
//        Set<Ingredient> allPossibleIngredients = new HashSet<>(ingredients);
//
//        if(allPossibleIngredients.contains(ingredient)){
//            for (int i = 0; i < ingredients.size(); i++) {
//                if(ingredient.equals(ingredients.get(i))){
//                    ingredients.remove(i);
//                }
//            }
//        }
//    }

    public List<Ingredient> getIngredients() {
        return ingredients;
    }

    @Override
    public String toString() {
        String string = super.toString();
        StringBuilder stringBuilder = new StringBuilder();

        stringBuilder.append(string);
        stringBuilder.append(" - Ingredients are: ");
        stringBuilder.append(ingredients);

        return stringBuilder.toString();
    }

    public void setIngredients(List<Ingredient> ingredients) {
        this.ingredients = ingredients;
    }
}
