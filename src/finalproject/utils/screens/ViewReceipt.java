package finalproject.utils.screens;

import basicgraphics.BasicContainer;

import javax.swing.*;

public class ViewReceipt extends BasicContainer {

    private final JButton goBackBTN = new JButton("Return to Menu Page");

    private final static String[][] layout = {
            {"back"},
            {"currentReciept"}
    };

    public ViewReceipt() {
        super();
        setStringLayout(layout);
        this.add("back", goBackBTN);
    }

    public JButton getGoBackBTN() {
        return goBackBTN;
    }

    //TODO: Implement method for retrieving recieptText

}
