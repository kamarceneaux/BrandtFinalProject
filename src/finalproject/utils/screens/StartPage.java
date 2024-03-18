package finalproject.utils.screens;

import basicgraphics.BasicContainer;

import javax.swing.*;
import java.awt.*;

public class StartPage extends BasicContainer {
    private JButton startButton = new JButton("Start Game!");

    static String[][] layoutForMainScreen = {
            {"StartButton", "headingInstructions"},
            {"StartButton", "trailingInstructions"}
    };

    /**
     * Code for the splash bar.
     */
    public StartPage() {
        super();
        this.setStringLayout(layoutForMainScreen);
        buttons();
        instructions();
    }

    /**
     * Generate buttons for the home screen.
     */
    private void buttons(){
        startButton.setPreferredSize(new Dimension(150, 75));
        startButton.setFont(startButton.getFont().deriveFont(Font.BOLD, 16));
        this.add("StartButton", startButton);
    }

    /**
     * Generate instructions sidebar for the screen.
     */
    private void instructions(){
        JLabel instructions = new JLabel("Welcome to Smooth Smootheria!");
        instructions.setFont(instructions.getFont().deriveFont(Font.BOLD, 24));

        JLabel moreInstructions = new JLabel(
                "<html>Do <b>YOU</b> have what it takes to be the quickest at " +
                        "<br> taking orders and ensuring customer accuracy??!" +
                        "<br><br><br> If you do, smooth smoothie is looking for employees to try!"+
                        "<br><br><br> <b>Note</b>: Ice is assumed in all smoothies.</html>"
        );
        moreInstructions.setVerticalAlignment(SwingConstants.TOP);

        this.add("headingInstructions", instructions);
        this.add("trailingInstructions", moreInstructions);
    }

    public JButton getStartButton() {
        return startButton;
    }
}
