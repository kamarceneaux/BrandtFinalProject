package finalproject.utils.screens;

import basicgraphics.BasicContainer;

import javax.swing.*;
import java.awt.*;

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

    public BarPage(){
        super();
        setStringLayout(layout);
        add("title", title);
        buttons();
    }

    private void buttons(){
        // Resize buttons for Java
        mochaBtn.setPreferredSize(new Dimension(250, 75));
        peanutBtn.setPreferredSize(new Dimension(250, 75));
        bananaBtn.setPreferredSize(new Dimension(250, 75));

        // Setup Buttons for the Item Customization Page
        add("mochaBtn", mochaBtn);
        add("peanutBtn", peanutBtn);
        add("bananaBtn", bananaBtn);
        add("goBack", goBackBtn);
    }

    public JButton getGoBackBtn() {
        return goBackBtn;
    }
}
