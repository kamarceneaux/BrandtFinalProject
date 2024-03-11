package finalproject.utils.core;

import javax.swing.*;
import java.util.ArrayList;
import java.util.List;

/**
 * This class is responsible for managing all the smoothie objects that are created
 */
public class SmoothieManager {
    public static List<Ingredient> ALL_POSSIBLE_INGREDIENTS = new ArrayList<>(
            List.of(new Ingredient("Strawberries"), //0
                    new Ingredient("Blueberries"), //1
                    new Ingredient("Raspberries"), //2
                    new Ingredient("Banana"), //3
                    new Ingredient("Greek Yogurt"), //4
                    new Ingredient("Honey"), //5
                    new Ingredient("Almond Milk"), //6
                    new Ingredient("Peanut Butter"), //7
                    new Ingredient("Kale"), //8
                    new Ingredient("Avocado"), //9
                    new Ingredient("Oats"), //10
                    new Ingredient("Pineapple"), //11
                    new Ingredient("Spinach"), //12
                    new Ingredient("Cucumber"), //13
                    new Ingredient("Milk"), //14
                    new Ingredient("Oatmilk")) //15
    );

    private List<Ingredient> berryIngredients = new ArrayList<>(
            List.of(ALL_POSSIBLE_INGREDIENTS.get(0),
                    ALL_POSSIBLE_INGREDIENTS.get(1),
                    ALL_POSSIBLE_INGREDIENTS.get(2),
                    ALL_POSSIBLE_INGREDIENTS.get(3),
                    ALL_POSSIBLE_INGREDIENTS.get(4),
                    ALL_POSSIBLE_INGREDIENTS.get(5),
                    ALL_POSSIBLE_INGREDIENTS.get(6))
    );

    private List<Ingredient> pbIngredients = new ArrayList<>(
            List.of(
                    ALL_POSSIBLE_INGREDIENTS.get(7),
                    ALL_POSSIBLE_INGREDIENTS.get(0),
                    ALL_POSSIBLE_INGREDIENTS.get(1),
                    ALL_POSSIBLE_INGREDIENTS.get(3),
                    ALL_POSSIBLE_INGREDIENTS.get(4),
                    ALL_POSSIBLE_INGREDIENTS.get(14),
                    ALL_POSSIBLE_INGREDIENTS.get(5)
            )
    );

    private List<Ingredient> hulkIngredients = new ArrayList<>(
            List.of(
                    ALL_POSSIBLE_INGREDIENTS.get(8),
                    ALL_POSSIBLE_INGREDIENTS.get(9),
                    ALL_POSSIBLE_INGREDIENTS.get(11),
                    ALL_POSSIBLE_INGREDIENTS.get(3),
                    ALL_POSSIBLE_INGREDIENTS.get(4),
                    ALL_POSSIBLE_INGREDIENTS.get(14),
                    ALL_POSSIBLE_INGREDIENTS.get(5)
            )
    );

    private List<Ingredient> popeyeIngredients = new ArrayList<>(
            List.of(
                    ALL_POSSIBLE_INGREDIENTS.get(12),
                    ALL_POSSIBLE_INGREDIENTS.get(3),
                    ALL_POSSIBLE_INGREDIENTS.get(4),
                    ALL_POSSIBLE_INGREDIENTS.get(11),
                    ALL_POSSIBLE_INGREDIENTS.get(14),
                    ALL_POSSIBLE_INGREDIENTS.get(5)
            )
    );

    private List<Ingredient> cucumberIngredients = new ArrayList<>(
            List.of(
                    ALL_POSSIBLE_INGREDIENTS.get(13),
                    ALL_POSSIBLE_INGREDIENTS.get(3),
                    ALL_POSSIBLE_INGREDIENTS.get(4),
                    ALL_POSSIBLE_INGREDIENTS.get(14),
                    ALL_POSSIBLE_INGREDIENTS.get(11)
            )
    );

    private List<Ingredient> oatIngredients = new ArrayList<>(
            List.of(
                    ALL_POSSIBLE_INGREDIENTS.get(10),
                    ALL_POSSIBLE_INGREDIENTS.get(1),
                    ALL_POSSIBLE_INGREDIENTS.get(2),
                    ALL_POSSIBLE_INGREDIENTS.get(3),
                    ALL_POSSIBLE_INGREDIENTS.get(4),
                    ALL_POSSIBLE_INGREDIENTS.get(15),
                    ALL_POSSIBLE_INGREDIENTS.get(5)
            )
    );

    private List<Ingredient> monkeyIngredients = new ArrayList<>(
            List.of(
                    ALL_POSSIBLE_INGREDIENTS.get(3),
                    ALL_POSSIBLE_INGREDIENTS.get(7),
                    ALL_POSSIBLE_INGREDIENTS.get(4),
                    ALL_POSSIBLE_INGREDIENTS.get(14),
                    ALL_POSSIBLE_INGREDIENTS.get(5),
                    ALL_POSSIBLE_INGREDIENTS.get(10),
                    ALL_POSSIBLE_INGREDIENTS.get(0)
            )
    );

    /**
     * Responsible for generating the Smoothie customization screen for the user.
     * @param btn
     * @return The Smoothie object
     */
    public Smoothie generateSmoothie(JButton btn){
        String text = btn.getText();

        if(text.contains("Blastoff")){
            System.out.println("Berry Smoothie");
            return new Smoothie("Berry Blastoff Smoothie", berryIngredients);
        } else if (text.contains("Peanut")) {
            System.out.println("Peanut Butter Smoothie");
            return new Smoothie("Peanut Butter Smoothie", pbIngredients);
        } else if (text.contains("Power")) {
            System.out.println("Power Potion");
            return new Smoothie("Popeye's Power Potion", popeyeIngredients);
        } else if (text.contains("Cucumber")) {
            System.out.println("Cucumber Squeeze");
            return new Smoothie("Cucumber Squeeze", cucumberIngredients);
        } else if (text.contains("Oat")) {
            System.out.println("Berry Oat-a-pult");
            return new Smoothie("Berry Oat-a-pult", oatIngredients);
        } else if (text.contains("Hulk")) {
            return new Smoothie("Hulk's Secret Garden", hulkIngredients);
        } else if (text.contains("Monkey")) {
            System.out.println("Monkey Business");
            return new Smoothie("Monkey Business", monkeyIngredients);
        } else if (text.contains("Build")) {
            System.out.println("Build your own smoothie");
            return new Smoothie("Build your own Smoothie");
        }
        return null;
    }

}
