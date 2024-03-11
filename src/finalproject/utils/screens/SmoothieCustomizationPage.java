package finalproject.utils.screens;

import basicgraphics.BasicContainer;
import finalproject.utils.core.Smoothie;

import javax.swing.*;

public class SmoothieCustomizationPage extends BasicContainer {
    private final JButton backButton = new JButton("Go Back");
    private Smoothie workingSmoothie;
    private static String[][] stringLayout = {
            {"BackButton", "Gap", "Filler"},
            // code for customization buttons
    };

    public SmoothieCustomizationPage() {
        super();
        this.setStringLayout(stringLayout);
        backButton();
    }

    public Smoothie getWorkingSmoothie() {
        return workingSmoothie;
    }

    public void setWorkingSmoothie(Smoothie workingSmoothie) {
        this.workingSmoothie = workingSmoothie;
    }

    public JButton getBackButton() {
        return backButton;
    }

    private void backButton(){
        this.add("BackButton", backButton);
    }
}
