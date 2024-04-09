package finalproject.utils.core.logic;

import finalproject.utils.core.*;
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
    private Map<String, Integer> correctData;
    private Cart correctItemsCart = new Cart();
    private int desiredTime;
    private String instructions;

    /**
     * Must be called to generate the game logic.
     */
    public void startLogic(){
        scenario = 0;

        random = new Random();
        correctData = generateAmountOfItems();
        // Generate the correctItem Cart
        buildCart();
        instructions = generateDirections();
        desiredTime = generateTimeForScenarioZero();
    }

    /**
     * Pass a users cart in and check if they have the same items.
     * @param userCart
     * @throws GameInformationError
     */
    public boolean checkScenarioZero(Cart userCart) throws GameInformationError {
        return checkScenarioZero(userCart, correctItemsCart);
    }

    /**
     * Code containing the information about the correct items.
     * Mainly used for testing.
     * @param userCart
     * @param correctItems
     * @return a boolean whether if the carts were equal or not.
     * @throws GameInformationError
     */
    public boolean checkScenarioZero(Cart userCart, Cart correctItems) throws GameInformationError {
        Comparator<Item> cartCompator = new Comparator<>() {
            @Override
            public int compare(Item o1, Item o2) {
                int diff = o1.getType().compareTo(o2.getType());
                if (diff != 0) return diff;
                diff = o1.getName().compareTo(o2.getName());
                if (diff != 0) return diff;
                diff = (int) (o1.getPrice() - o2.getPrice());
                return diff;
            }
        };
        userCart.getItems().sort(cartCompator);
        correctItems.getItems().sort(cartCompator);

        if(userCart.equals(correctItems)){
            return true;
        }else{
            // Check and see if there is a error for the cart Error for size of the cart
            if(userCart.numberOfItems() != correctItems.numberOfItems()){
                if(userCart.numberOfItems() > correctItems.numberOfItems())
                    throw new GameInformationError("Too many items were given to the customer. BE BETTER. ");
                if(userCart.numberOfItems() < correctItems.numberOfItems())
                    throw new GameInformationError("The customer did not receive their complete order.");
            }

            // Split the cart into separate items.
            List<Smoothie> smoothies = new ArrayList<>();
            List<Item> otherItems = new ArrayList<>();

            for(Item item: userCart.getItems()){
                if(item.getType().equals(TypeOfItem.SMOOTHIE)){
                    smoothies.add((Smoothie) item);
                }else {
                    otherItems.add(item);
                }
            }

            // Split the correct items cart into separate items
            List<Smoothie> correctSmoothies = new ArrayList<>();
            List<Item> correctOtherItems = new ArrayList<>();

            for (Item item: correctItems.getItems()){
                if(item.getType().equals(TypeOfItem.SMOOTHIE)){
                    correctSmoothies.add((Smoothie) item);
                }else {
                    correctOtherItems.add(item);
                }
            }

            if(smoothies.size() != correctSmoothies.size()){
                if(smoothies.size() > correctSmoothies.size()){
                    throw new GameInformationError("You have one too many smoothies.");
                }else {
                    throw new GameInformationError("You are missing at least one smoothie on the order");
                }
            }

            if(otherItems.size() != correctOtherItems.size()){
                if(otherItems.size() > correctOtherItems.size()){
                    throw new GameInformationError("You have one too many items.");
                }else {
                    throw new GameInformationError("You are missing at least one item on the order");
                }
            }

            // Check to see if all the smoothies are correct.
            for (int i = 0; i < smoothies.size(); i++) {
                Smoothie userSmoothie = smoothies.get(i);
                Smoothie targetSmoothie = correctSmoothies.get(i);

                try {
                    if(!userSmoothie.equals(targetSmoothie)){
                        throw new GameInformationError("One of the smoothie's is incorrect: either it is missing ingredients or " +
                                "name doesn't match the order.");
                    }
                } catch (NullPointerException e) {
                    throw new GameInformationError("Internal error.");
                }
            }

            // Check to see if all the item's are correct.
            for (int i = 0; i < correctOtherItems.size(); i++) {
                Item userItem = otherItems.get(i);
                Item targetItem = correctOtherItems.get(i);

                if(!userItem.equals(targetItem)){
                    throw new GameInformationError("One of the item's is incorrect either it is missing ingredients or " +
                            "doesn't match the order");
                }
            }

            throw new GameInformationError("Something internal is happening.");
        }
    }

    /**
     * Generate the correct data for generating scenarios.
     */
    public Map<String, Integer> generateAmountOfItems(){
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
     */
    public void buildCart(){
        Integer amtOfSmoothieOptions = correctData.get("smoothieOptions");
        generateSmoothiesForCart(amtOfSmoothieOptions);

        Integer amtOfCookies = correctData.get("totalCookies");
        Integer amtOfBars = correctData.get("totalBars");
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
            if(smoothie.getName().equalsIgnoreCase("Build your own Smoothie")){
                // Contains every single possible ingredient we have to work with
                // Generate amount of ingredients for a smoothie
                int generateAmountOfIngredientsForSmoothie = random.nextInt(10) + 3;
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

    /**
     * Regenerates the instructions for the correct items. Basically
     * what items the user is going for.
     */
    public String generateDirections(){
        return generateDirections(correctItemsCart);
    }

    /**
     * Given a cart generate directions for it.
     */
    public String generateDirections(Cart cart){
        StringBuilder sb = new StringBuilder();

        // Determine a greeting if possible
        String[] greetingChoices = {"Hey, how's it going? ", "Hi there! What's good today? "};
        String startGreeting = greetingChoices[random.nextInt(greetingChoices.length)];
        sb.append(startGreeting);

        scenario = 0;
        int counter = 0;
        // If in scenario zero --> which is follow instructions.
        if(scenario == 0){
            List<Item> items = cart.getItems();

            // Responsible for tracking the countOfItems
            HashMap<String, Integer> snackMap = new HashMap<>();
            int mochaCount = 0, pbCount = 0, banaCount = 0, chocCount = 0, sugCount = 0;
            snackMap.put("Mocha Madness Muscle Mender", mochaCount);
            snackMap.put("Peanut Butter Powerhouse", pbCount);
            snackMap.put("Banana Bread Buff Bar", banaCount);
            snackMap.put("Chocolate Chip Cookie", chocCount);
            snackMap.put("Sugar Cookie", sugCount);

            for(Item item: items){
                if(item.getType().equals(TypeOfItem.SMOOTHIE)){
                    Smoothie smoothie = (Smoothie) item;

                    if(item.getName().equalsIgnoreCase("build your own smoothie")){
                        // logic
                        String instruction_text = buildYourOwnInstructions(smoothie, counter);
                        sb.append(instruction_text);
                        counter++;
                    }else{
                        // This means it's not a build your own smoothie
                        String instruction_text = smoothieInstructions(smoothie, counter);
                        sb.append(instruction_text);
                        counter++;
                    }
                }
                if(item.getType().equals(TypeOfItem.SNACK)){
                    if(item.getName().equalsIgnoreCase("mocha madness muscle mender")){
                        snackMap.put("Mocha Madness Muscle Mender", ++mochaCount);
                    } else if (item.getName().equalsIgnoreCase("Peanut Butter Powerhouse")) {
                        snackMap.put("Peanut Butter Powerhouse", ++pbCount);
                    } else if (item.getName().equalsIgnoreCase("Banana Bread Buff Bar")) {
                        snackMap.put("Banana Bread Buff Bar", ++banaCount);
                    } else if (item.getName().equalsIgnoreCase("Chocolate Chip Cookie")) {
                        snackMap.put("Chocolate Chip Cookie", ++chocCount);
                    } else if (item.getName().equalsIgnoreCase("Sugar Cookie")) {
                        snackMap.put("Sugar Cookie", ++sugCount);
                    }
                }
            }

            // Generate Text for items to add
            String itemsText = generateItemsText(snackMap);
            sb.append(itemsText);
        }
        return sb.toString();
    }

    /**
     * Responsible for generating the instructions for the non smoothie items.
     */
    private String generateItemsText(HashMap<String, Integer> snackMap) {
        StringBuilder sb = new StringBuilder();

        HashMap<String, Integer> totalItemsInCart = new HashMap<>();

        // loops through the snap map and checks to see if snack is in the cart or not.
        // if it is zero, then it is not in the cart.
        for (Map.Entry<String, Integer> entry : snackMap.entrySet()) {
            if(entry.getValue() > 0){
                totalItemsInCart.put(entry.getKey(), entry.getValue());
            }
        }

        if(totalItemsInCart.size() > 0){
            sb.append("I also would like ");
            String conditional_s = totalItemsInCart.size() > 1 ? "following items " : "";
            sb.append(conditional_s);
        }

        try{
            int counter = 0;
            for (Map.Entry<String, Integer> entry: totalItemsInCart.entrySet()){
                String textRepresentation = convertToString(entry.getValue());
                if(counter == totalItemsInCart.size() - 1 && totalItemsInCart.size() > 1){
                        String replace = entry.getKey().replaceAll(",\\s*", "");
                        sb.append("and ");
//                        sb.append(String.format("%s ", textRepresentation)).append(replace);
                        if(entry.getValue() > 1){
                            sb.append(String.format("%s %s's", textRepresentation, entry.getKey()));
                        }else{
                            sb.append(String.format("%s %s", textRepresentation, entry.getKey()));
                        }
                }else{
                    if(totalItemsInCart.size() == 1){
                        if(entry.getValue() > 1){
                            sb.append(String.format("%s %s's", textRepresentation, entry.getKey()));
                        }else{
                            sb.append(String.format("%s %s", textRepresentation, entry.getKey()));
                        }
                    }else{
                        if(entry.getValue() > 1){
                            sb.append(String.format("%s %s's, ", textRepresentation, entry.getKey()));
                        }else{
                            sb.append(String.format("%s %s, ", textRepresentation, entry.getKey()));
                        }
                    }
                    counter++;
                }
            }
            if(!totalItemsInCart.isEmpty()) sb.append(".");
        }catch (Exception e){
            sb.append("I do not want any extra items. ");
        }

        return sb.toString();
    }

    /**
     * Generate instructions for smoothies that aren't make your own.
     */
    private String smoothieInstructions(Smoothie smoothie, int counter) {
        List<Ingredient> originalIngredients = smoothie.getIngredients();
        List<Ingredient> modifiedIngredients = smoothie.getModifiedIngredients();

        Collections.sort(originalIngredients);
        Collections.sort(modifiedIngredients);

        List<Ingredient> addedIngredients = new ArrayList<>();
        List<Ingredient> removedIngredients = new ArrayList<>();

        StringBuilder sb = new StringBuilder();

        // Responsible for the introduction
        if(counter == 0){
            sb.append("Can I get a ");
        }else{
            sb.append("May I also get a ");
        }

        sb.append(String.format("%s ", smoothie.getName()));

        // If a smoothie has ZERO modifications.
        if(originalIngredients.equals(modifiedIngredients)){
            sb.append("as is. ");
            return sb.toString();
        }

        // Check for added ingredients
        for (Ingredient ingredient : modifiedIngredients) {
            if (!originalIngredients.contains(ingredient)) {
                addedIngredients.add(ingredient);
            }
        }

        // Check for removed ingredients
        for (Ingredient ingredient : originalIngredients) {
            if (!modifiedIngredients.contains(ingredient)) {
                removedIngredients.add(ingredient);
            }
        }

        // Responsible for generating remove smoothies text.
        if(!removedIngredients.isEmpty()){
            sb.append("but remove ");
            for(Ingredient ingredient: removedIngredients){
                int checkToSeeIfLast = removedIngredients.indexOf(ingredient);
                String ingredientName = ingredient.getName();

                if(checkToSeeIfLast == removedIngredients.size() - 1 && removedIngredients.size() > 1){
                    String replace = ingredientName.replaceAll(",\\s*", "");
                    sb.append("and ");
                    sb.append(replace).append(" ");
                }else{
                    if(removedIngredients.size() == 1){
                        sb.append(String.format("%s ", ingredientName));
                    }else{
                        sb.append(String.format("%s, ", ingredientName));
                    }
                }
            }
            sb.append("from that smoothie. ");
        }

        if(!addedIngredients.isEmpty()){
            if(removedIngredients.isEmpty()){
                sb.append("do not remove anything, but could you add ");
            }else{
                sb.append("Would you mind adding ");
            }
            for(Ingredient ingredient: addedIngredients){
                int checkToSeeIfLast = addedIngredients.indexOf(ingredient);
                String ingredientName = ingredient.getName();

                if(checkToSeeIfLast == addedIngredients.size() - 1 && addedIngredients.size() > 1){
                    String replace = ingredientName.replaceAll(",\\s*", "");
                    sb.append("and ");
                    sb.append(replace).append(" ");
                }else{
                    if(addedIngredients.size() == 1){
                        sb.append(String.format("%s ", ingredientName));
                    }else{
                        sb.append(String.format("%s, ", ingredientName));
                    }
                }
            }
            sb.append("to that smoothie. ");
        }

        return sb.toString();
    }

    /** Generates instructions for BYO smoothies. **/
    private String buildYourOwnInstructions(Smoothie smoothie, int counter) {
        StringBuilder sb = new StringBuilder();

        if(counter == 0){
            sb.append("can I get a ");
        }else{
            sb.append("May I also get a ");
        }

        sb.append(String.format("%s with ", smoothie.getName()));
        List<Ingredient> addedIngredients = smoothie.getModifiedIngredients();

        // Formats Ingredient Text
        try{
            for (Ingredient ingredient : addedIngredients){
                int checkToSeeIfLast = addedIngredients.indexOf(ingredient);
                String ingredientName = ingredient.getName();

                if(checkToSeeIfLast == addedIngredients.size() - 1 && addedIngredients.size() > 1){
                    String replace = ingredientName.replaceAll(",\\s*", "");
                    sb.append("and ");
                    sb.append(replace).append(". ");
                }else{
                    sb.append(String.format("%s, ", ingredientName));
                }
            }
        }catch (NullPointerException e){
            for (Ingredient ingredient : smoothie.getIngredients()){
                int checkToSeeIfLast = smoothie.getIngredients().indexOf(ingredient);
                String ingredientName = ingredient.getName();

                if(checkToSeeIfLast == smoothie.getIngredients().size() - 1 && smoothie.getIngredients().size() > 1){
                    String replace = ingredientName.replaceAll(",\\s*", "");
                    sb.append("and ");
                    sb.append(replace).append(". ");
                }else{
                    sb.append(String.format("%s, ", ingredientName));
                }
            }
        }


        return sb.toString();
    }

    private int generateTimeForScenarioZero(){
        int totalTime = 10;

        for (Item item: getCorrectItemsCart().getItems()){
            if(item instanceof Smoothie){
                Smoothie smoothie = (Smoothie) item;
                List<Ingredient> ingredients = smoothie.getModifiedIngredients();
                if(ingredients.size() > 9){
                    totalTime +=  Math.round(ingredients.size() * 2.15);
                } else if (ingredients.size() > 4) {
                    totalTime +=  Math.round(ingredients.size() * 1.75);
                } else{
                    totalTime += ingredients.size();
                }
            }else{
                totalTime += 3;
            }
        }
        return totalTime;
    }

    /**
     * Responsible for converting a number into a text format.
     */
    public static String convertToString(int number) {
        switch (number) {
            case 1:
                return "one";
            case 2:
                return "two";
            case 3:
                return "three";
            case 4:
                return "four";
            case 5:
                return "five";
            case 6:
                return "six";
            case 7:
                return "seven";
            case 8:
                return "eight";
            case 9:
                return "nine";
            case 10:
                return "ten";
            default:
                return "unsupported";
        }
    }

    public String getInstructions() {
        return instructions;
    }

    public int getScenario() {
        return scenario;
    }

    public int getDesiredTime() {
        return desiredTime;
    }
}
