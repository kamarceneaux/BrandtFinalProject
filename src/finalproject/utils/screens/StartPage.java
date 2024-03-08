package finalproject.utils.screens;

import basicgraphics.BasicContainer;

import javax.swing.*;

public class StartPage extends BasicContainer {

    static String[][] layoutForMainScreen = {
            {"StartButton"},
    };

    public StartPage() {
        super();
        this.setStringLayout(layoutForMainScreen);
        buttons();
    }

    private void buttons(){
        JButton startButton = new JButton("Start Game!");
        this.add("StartButton", startButton);
    }


}
