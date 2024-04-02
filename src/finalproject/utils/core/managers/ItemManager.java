package finalproject.utils.core.managers;

import finalproject.utils.core.Item;
import finalproject.utils.core.TypeOfItem;

import javax.swing.JButton;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Random;

public class ItemManager {

    /**
     * Generates a protein bar.
     */
    public Item generateProteinBar(JButton btn){
        if(btn.getText().equals("Mocha Madness Muscle Mender")){
            return new Item(4.99, "Mocha Madness Muscle Mender", TypeOfItem.SNACK);
        } else if (btn.getText().equals("Peanut Butter Powerhouse")) {
            return new Item(4.99, "Peanut Butter Powerhouse", TypeOfItem.SNACK);
        } else if (btn.getText().equals("Banana Bread Buff Bar")) {
            return new Item(4.99, "Banana Bread Buff Bar", TypeOfItem.SNACK);
        }
        return null;
    }

    public Item generateProteinBar(){
        // Add all the choices to be queryed from
        List<Item> allBars = new ArrayList<>();
        allBars.add(new Item(4.99, "Mocha Madness Muscle Mender", TypeOfItem.SNACK));
        allBars.add(new Item(4.99, "Peanut Butter Powerhouse", TypeOfItem.SNACK));
        allBars.add(new Item(4.99, "Banana Bread Buff Bar", TypeOfItem.SNACK));

        // Randomly shuffle the bars
        Collections.shuffle(allBars);
        Random random = new Random();
        return allBars.get(random.nextInt(allBars.size()));
    }

    public Item generateCookie(JButton btn){
        if(btn.getText().contains("Sugar")){
            return new Item(3.99, "Sugar Cookie", TypeOfItem.SNACK);
        } else if (btn.getText().contains("Chocolate Chip")) {
            return new Item(3.99, "Chocolate Chip Cookie", TypeOfItem.SNACK);
        }
        return null;
    }

    public Item generateCookie(){
        // Add all the choices to be queryed from
        List<Item> allCookies = new ArrayList<>();
        allCookies.add(new Item(3.99, "Sugar Cookie", TypeOfItem.SNACK));
        allCookies.add(new Item(3.99, "Chocolate Chip Cookie", TypeOfItem.SNACK));

        // Randomly shuffle the bars
        Collections.shuffle(allCookies);
        Random random = new Random();
        return allCookies.get(random.nextInt(allCookies.size()));
    }
}
