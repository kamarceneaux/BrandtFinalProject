package finalproject.utils.screens;

import basicgraphics.BasicContainer;
import finalproject.utils.core.Ingredient;
import finalproject.utils.core.Smoothie;
import finalproject.utils.core.SmoothieManager;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import java.util.List;

public class SmoothieCustomizationPage extends BasicContainer {
    private final JButton backButton = new JButton("Go Back");
    private final JButton submitButton = new JButton("Save Changes!");
    private final JLabel currentSmoothieLbl = new JLabel("");
    private final List<JButton> allCustomizationButtons = new ArrayList<>();
    private List<Ingredient> newSetofIngredients;


    private Smoothie workingSmoothie;
    private static String[][] stringLayout = {
            {"BackButton", "upGap", "upGap", "upGap"},
            {"_", "_", "_", "_"},
            {"_", "_", "_", "_"},
            {"_", "_", "_", "_"},
            {"_", "_", "_", "_"},
            {"gap", "gap", "gap", "SubmitButton"},
    };

    public SmoothieCustomizationPage() {
        super();
        smoothieLayout();
        this.setStringLayout(stringLayout);

        // Set the text label up somewhere
        Font font = currentSmoothieLbl.getFont().deriveFont(Font.BOLD, 36);
        currentSmoothieLbl.setFont(font);
        this.add("upGap", currentSmoothieLbl);

        generateCustomizationButtons();
        submitNBackButtons();
    }

    public void setInformation (Smoothie workingSmoothie){
        setWorkingSmoothie(workingSmoothie);
    }

    public void setCurrentSmoothieLbl(String smoothieLbl){
        // Sets the Main Label for a Smoothie
        currentSmoothieLbl.setText(smoothieLbl);

        // The Following Block is Responsible for Data State for Ingredients
        // Reseting ingredients when transfering between smoothie objects.
        if(workingSmoothie.getIngredients() == null){
            workingSmoothie.setIngredients(new ArrayList<>());
        }
        workingSmoothie.setModifiedIngredients(workingSmoothie.getIngredients());
        newSetofIngredients = new ArrayList<>();
        newSetofIngredients.addAll(workingSmoothie.getIngredients());
    }

    public List<JButton> getAllCustomizationButtons() {
        return allCustomizationButtons;
    }

    public Smoothie getWorkingSmoothie() {
        return workingSmoothie;
    }

    private void setWorkingSmoothie(Smoothie workingSmoothie) {
        this.workingSmoothie = workingSmoothie;
    }

    public JButton getBackButton() {
        return backButton;
    }

    public JButton getSubmitButton() {
        return submitButton;
    }

    private void smoothieLayout(){
        int row = 1;
        int tab = 0;
        for (int i = 0; i < SmoothieManager.ALL_POSSIBLE_INGREDIENTS.size(); i++) {
            if(tab > 3){
                tab = 0;
                row++;
            }
            stringLayout[row][tab++] = String.valueOf(SmoothieManager.ALL_POSSIBLE_INGREDIENTS.get(i))
                    .replace(" ", "");
        }
    }

    private void generateCustomizationButtons(){
        for (int i = 0; i < SmoothieManager.ALL_POSSIBLE_INGREDIENTS.size(); i++) {
            Ingredient ingredient = SmoothieManager.ALL_POSSIBLE_INGREDIENTS.get(i);
            String btnName = ingredient.getName();

            JButton customizationBtn = new JButton(btnName);
            customizationBtn.setPreferredSize(new Dimension(150, 65));

            allCustomizationButtons.add(customizationBtn);

            String gridName = btnName.replace(" ", "");
            this.add(gridName, customizationBtn);
        }
    }

    private void submitNBackButtons(){
        backButton.setPreferredSize(new Dimension(125, 45));
        submitButton.setPreferredSize(new Dimension(125, 45));

        this.add("BackButton", backButton);
        this.add("SubmitButton", submitButton);
    }

    /**
     * The method is responsible for ensuring that buttons are added to the screen.
     */
    public void loadButtonData() {
        if(workingSmoothie.getIngredients() != null){
            for (int i = 0; i < allCustomizationButtons.size(); i++) {
                List<Ingredient> ingredients = workingSmoothie.getIngredients();
                JButton currentBtn = allCustomizationButtons.get(i);

                for (int j = 0; j < ingredients.size(); j++) {
                    if(currentBtn.getText().equals(ingredients.get(j).getName())){
                        currentBtn.setForeground(Color.RED);
                    }
                }
            }
        }
    }

    /**
     * Responsible for modifying the Smoothie Ingredients in a Smoothie.
     * @param btn
     */
    public void modifySmoothieIngredients(JButton btn){
        String text = btn.getText();

        // If a ingredient is already in the Smoothie
        if(btn.getForeground().equals(Color.RED)){
            btn.setForeground(Color.BLACK);
            for (int i = 0; i < newSetofIngredients.size(); i++) {
                if(text.equals(newSetofIngredients.get(i).getName())){
                    newSetofIngredients.remove(i);
                    break;
                }
            }
        }else {
            if(newSetofIngredients == null){
                newSetofIngredients = new ArrayList<>();
            }
            newSetofIngredients.add(new Ingredient(text));
            btn.setForeground(Color.RED);
        }

    }

    public void setNewSetofIngredients(List<Ingredient> newSetofIngredients) {
        this.newSetofIngredients = newSetofIngredients;
    }

    /**
     * Determines whether if a submission can be processed
     *
     * If it returns true, then the submission can be processed.
     * If it returns false, then the submission cannot be processed.
     */
    public boolean processSubmission(){
        if(newSetofIngredients != null && newSetofIngredients.size() != 0){
            // Set New Ingredients In the Array
            workingSmoothie.setModifiedIngredients(newSetofIngredients);
            return true;
        }else{
            JOptionPane.showMessageDialog(this, "Can't add a smoothie with no ingredients");
            return false;
        }
    }
}
