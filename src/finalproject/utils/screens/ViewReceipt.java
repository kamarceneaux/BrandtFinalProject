package finalproject.utils.screens;

import basicgraphics.BasicContainer;

import javax.swing.*;
import java.awt.*;

public class ViewReceipt extends BasicContainer {

    private final JButton goBackBTN = new JButton("Return to Menu Page");
    private String textForLabel = " ";
    private JLabel text = new JLabel(textForLabel);
    private JScrollPane scrollPaneText = new JScrollPane(text);

    private final static String[][] layout = {
            {"back"},
            {"currentReceipt"}
    };

    public ViewReceipt() {
        super();
        setStringLayout(layout);
        initializeReceiptTab();
        this.add("back", goBackBTN);
    }

    private void initializeReceiptTab() {
        // Makes sure it can cover most of the space.
        scrollPaneText.setPreferredSize(new Dimension(725, 450));

        // Makes the scrollable only vertically scrollable.
        scrollPaneText.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
        this.add("currentReceipt", scrollPaneText);
    }

    /**
     * Responsible for updating the receipt
     * @param textForLabel
     */
    public void setTextForLabel(String textForLabel) {
        this.textForLabel = textForLabel;
        text.setText(textForLabel);
    }

    public JButton getGoBackBTN() {
        return goBackBTN;
    }

    //TODO: Implement method for retrieving recieptText

}
