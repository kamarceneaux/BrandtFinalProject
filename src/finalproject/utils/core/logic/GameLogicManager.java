package finalproject.utils.core.logic;

import finalproject.utils.core.Cart;
import finalproject.utils.core.Ingredient;
import finalproject.utils.core.Item;
import finalproject.utils.core.Smoothie;
import finalproject.utils.core.managers.ItemManager;
import finalproject.utils.core.managers.SmoothieManager;

import java.util.*;

public class GameLogicManager {
    private static Random random = new Random();
    /*
    Scenario = 0 --> follow exact order
    Scenario = 1 --> make a order under a budget
     */
    private int scenario = random.nextInt(2);
    private int totalItems;
    private SmoothieManager smoothieManager = new SmoothieManager();
    private ItemManager itemManager = new ItemManager();
    private int totalSmoothies;
    private int otherItems;
    private Cart correctItemsCart = new Cart();

    public void startLogic(){
        // Follow Exact Order Sequence scenario

        scenario = 0;

        if(scenario == 0){
            Map<String, Integer> correctData = generateAmountOfItems();

            // Generate the correctItem Cart
            buildCart(correctData);

        } else {

        }
    }

    /**
     * Generate the correct data for generating scenarios.
     */
    private Map<String, Integer> generateAmountOfItems(){
        Map<String, Integer> correctOrderData = new HashMap<>();

        // Total Items
        totalItems = random.nextInt(6) + 1;

        // Generate total amount of smoothie and items options
        int smoothieOptions = random.nextInt(totalItems) + 1;
        int otherItems = totalItems - smoothieOptions;

        int totalBars = 0, totalCookies = 0;
        // Subdivide other items
        if(otherItems > 0){
            totalBars = random.nextInt(otherItems);
            totalCookies = otherItems - totalBars;
        }

        correctOrderData.put("totalItems", totalItems);
        correctOrderData.put("smoothieOptions", smoothieOptions);
        correctOrderData.put("otherItems", otherItems);
        correctOrderData.put("totalBars", totalBars);
        correctOrderData.put("totalCookies", totalCookies);

        return correctOrderData;
    }

    /**
     * Responsible for building the cart for the correct items.
     * @param data responsible the data that talks about the amount of smoothie options and more.
     */
    public void buildCart(Map<String, Integer> data){
        Integer amtOfSmoothieOptions = data.get("smoothieOptions");
        generateSmoothiesForCart(amtOfSmoothieOptions);

        Integer amtOfCookies = data.get("totalCookies");
        Integer amtOfBars = data.get("totalBars");
        generateItemsForCart(amtOfBars, amtOfCookies);
    }

    /**
     * Responsible for generating misc. items for the cart
     */
    private void generateItemsForCart(Integer barCount, Integer cookieCount) {
        for (int i = 0; i < barCount; i++) {
            Item bar = itemManager.generateProteinBar();
            correctItemsCart.addItem(bar);
        }

        for (int i = 0; i < cookieCount; i++) {
            Item cookie = itemManager.generateCookie();
            correctItemsCart.addItem(cookie);
        }

    }

    /**
     * Responsible for generating the smoothies for the correct items cart.
     */
    private void generateSmoothiesForCart(Integer amtOfSmoothieOptions) {
        for (int i = 0; i < amtOfSmoothieOptions; i++) {
            // Get a smoothie object
            Smoothie smoothie = smoothieManager.generateSmoothie();

            List<Ingredient> allIngredients = new ArrayList<>();
            allIngredients.addAll(SmoothieManager.ALL_POSSIBLE_INGREDIENTS);

            // If we are working with a byo smoothie, then we need new logic
            if(smoothie.getName().equals("Build your own Smoothie")){
                // Contains every single possible ingredient we have to work with
                // Generate amount of ingredients for a smoothie
                int generateAmountOfIngredientsForSmoothie = random.nextInt(10) + 1;
                Set<Ingredient> newSetModifiedIngredients = new HashSet<>();

                // Add random ingredients to the smoothie
                for (int j = 0; j < generateAmountOfIngredientsForSmoothie; j++) {
                    Collections.shuffle(allIngredients);
                    Ingredient ingredientToBeAdded = allIngredients.get(random.nextInt(allIngredients.size()));
                    newSetModifiedIngredients.add(ingredientToBeAdded);
                }

                List<Ingredient> modifiedIngedients = new ArrayList<>();
                modifiedIngedients.addAll(newSetModifiedIngredients);

                // Set the smoothie ingredients.
                smoothie.setModifiedIngredients(modifiedIngedients);
            }else{
                // Generate how many ingredients, if any to remove.
                int generateItemsToBeRemoved = random.nextInt(4);
                int generateItemsToBeAdded = random.nextInt(4);

                // Get all the ingredients, into the workingset of ingredients
                List<Ingredient> workingsetOfIngredients = new ArrayList<>();
                workingsetOfIngredients.addAll(smoothie.getIngredients());

                // Loop through the workingset and remove ingredeints at random
                for (int j = 0; j < generateItemsToBeRemoved; j++) {
                    int indexToBeRemoved = random.nextInt(workingsetOfIngredients.size());
                    workingsetOfIngredients.remove(indexToBeRemoved);
                }

                // Convert to a set to avoid duplicate ingredients
                Set<Ingredient> setWSofIngredients = new HashSet<>(workingsetOfIngredients);

                // Loop through the working set and add ingredients at random
                for (int j = 0; j < generateItemsToBeAdded; j++) {
                    int indexToBeAdded = random.nextInt(allIngredients.size());
                    setWSofIngredients.add(allIngredients.get(indexToBeAdded));
                }

                // Set smoothie ingredients
                List<Ingredient> modifiedIngredients = new ArrayList<>();
                modifiedIngredients.addAll(setWSofIngredients);

                smoothie.setModifiedIngredients(modifiedIngredients);
            }
            // Add Smoothie to Cart
            correctItemsCart.addItem(smoothie);
        }
    }

    public Cart getCorrectItemsCart() {
        return correctItemsCart;
    }

    private String generateDirections(){
        return null;
    }
}
