package finalproject.utils.core.managers;

import finalproject.utils.core.Item;
import finalproject.utils.core.TypeOfItem;

import javax.swing.JButton;

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
        return new Item(0,"Default Smoothie", TypeOfItem.SNACK);
    }

    public Item generateCookie(JButton btn){
        return new Item(2.34, "something", TypeOfItem.SNACK);
    }
}
