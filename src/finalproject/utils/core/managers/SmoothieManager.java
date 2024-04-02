package finalproject.utils.core.managers;

import finalproject.utils.core.Ingredient;
import finalproject.utils.core.Smoothie;

import javax.swing.*;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Random;

/**
 * This class is responsible for managing all the smoothie objects that are created
 */
public class SmoothieManager {
    public final static List<Ingredient> ALL_POSSIBLE_INGREDIENTS = new ArrayList<>(
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
     *
     * @param btn
     * @return The Smoothie object
     */
    public Smoothie generateSmoothie(JButton btn) {
        String text = btn.getText();

        if (text.contains("Blastoff")) {
            return new Smoothie("Berry Blastoff Smoothie", berryIngredients);
        } else if (text.contains("Peanut")) {
            return new Smoothie("Peanut Butter Smoothie", pbIngredients);
        } else if (text.contains("Power")) {
            return new Smoothie("Popeye's Power Potion", popeyeIngredients);
        } else if (text.contains("Cucumber")) {
            return new Smoothie("Cucumber Squeeze", cucumberIngredients);
        } else if (text.contains("Oat")) {
            return new Smoothie("Berry Oat-a-pult", oatIngredients);
        } else if (text.contains("Hulk")) {
            return new Smoothie("Hulk's Secret Garden", hulkIngredients);
        } else if (text.contains("Monkey")) {
            return new Smoothie("Monkey Business", monkeyIngredients);
        } else if (text.contains("Build")) {
            return new Smoothie("Build your own Smoothie");
        }
        return null;
    }

    /**
     * Method is used mainly to generate a random smoothie for order generation
     * @return one singular smoothie for randomization.
     */
    public Smoothie generateSmoothie() {
        List<Smoothie> allSmoothies = new ArrayList<>();
        allSmoothies.add(new Smoothie("Berry Blastoff Smoothie", berryIngredients));
        allSmoothies.add(new Smoothie("Peanut Butter Smoothie", pbIngredients));
        allSmoothies.add(new Smoothie("Popeye's Power Potion", popeyeIngredients));
        allSmoothies.add(new Smoothie("Cucumber Squeeze", cucumberIngredients));
        allSmoothies.add(new Smoothie("Berry Oat-a-pult", oatIngredients));
        allSmoothies.add(new Smoothie("Hulk's Secret Garden", hulkIngredients));
        allSmoothies.add(new Smoothie("Monkey Business", monkeyIngredients));
        allSmoothies.add(new Smoothie("Build your own Smoothie"));

        // Randomly shuffle the smoothie
        Collections.shuffle(allSmoothies);
        Random random = new Random();
        return allSmoothies.get(random.nextInt(allSmoothies.size()));
    }

}
