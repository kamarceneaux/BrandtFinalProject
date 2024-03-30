package finalproject.utils.screens;

import basicgraphics.BasicContainer;

import javax.swing.*;
import java.awt.Dimension;
import java.util.ArrayList;

public class BarPage extends BasicContainer {
    private final static String[][] layout = {
            {"title"},
            {"mochaBtn", "peanutBtn", "bananaBtn"},
            {"goBack"}
    };

    private final JLabel title = new JLabel("Other Items To Be Added");
    private final JButton mochaBtn = new JButton("Mocha Madness Muscle Mender");
    private final JButton peanutBtn = new JButton("Peanut Butter Powerhouse");
    private final JButton bananaBtn = new JButton("Banana Bread Buff Bar");
    private final JButton goBackBtn = new JButton("Go Back");
    private final ArrayList<JButton> allOptionsForBtns = new ArrayList<>();

    public BarPage(){
        super();
        setStringLayout(layout);
        title.setHorizontalAlignment(SwingConstants.CENTER);
        add("title", title);
        buttons();
    }

    private void buttons(){
        // Resize buttons for Java
        mochaBtn.setPreferredSize(new Dimension(250, 75));
        peanutBtn.setPreferredSize(new Dimension(250, 75));
        bananaBtn.setPreferredSize(new Dimension(250, 75));

        // Setup Buttons for the Item Customization Page
        allOptionsForBtns.add(mochaBtn);
        add("mochaBtn", mochaBtn);
        allOptionsForBtns.add(peanutBtn);
        add("peanutBtn", peanutBtn);
        allOptionsForBtns.add(bananaBtn);
        add("bananaBtn", bananaBtn);

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
