package finalproject.utils.screens;

import basicgraphics.BasicContainer;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;

public class CookiePage extends BasicContainer {
    private final static String[][] layout = {
            {"title"},
            {"sugarBtn", "chocBtn"},
            {"goBack"}
    };

    private final JLabel title = new JLabel("Cookies Page");
    private final JButton sugarBtn = new JButton("Sugar Cookie");
    private final JButton chocChipBtn = new JButton("Chocolate Chip");
    private final JButton goBackBtn = new JButton("Go Back");
    private final ArrayList<JButton> allOptionsForBtns = new ArrayList<>();

    public CookiePage(){
        super();
        setStringLayout(layout);
        title.setHorizontalAlignment(SwingConstants.CENTER);
        add("title", title);
        buttons();
    }

    private void buttons(){
        // Resize buttons for Java
        sugarBtn.setPreferredSize(new Dimension(250, 75));
        chocChipBtn.setPreferredSize(new Dimension(250, 75));

        // Setup Buttons for the Item Customization Page
        allOptionsForBtns.add(sugarBtn);
        add("sugarBtn", sugarBtn);
        allOptionsForBtns.add(chocChipBtn);
        add("chocBtn", chocChipBtn);

        // Adds the goBack Button
        add("goBack", goBackBtn);
    }

    public JButton getGoBackBtn() {
        return goBackBtn;
    }

    public ArrayList<JButton> getAllOptionsForBtns() {
        return allOptionsForBtns;
    }
}
