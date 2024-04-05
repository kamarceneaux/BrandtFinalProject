package finalproject.utils.screens;

import basicgraphics.BasicContainer;

import javax.swing.*;
import java.awt.Dimension;
import java.awt.Font;
import java.util.ArrayList;
import java.util.List;

public class MenuScreen extends BasicContainer {

    private static String[][] layoutForMenu = {
            {"instructions", "instructions", "submit"},
            {"item1", "item2", "receipt_text"},
            {"item3", "item4", "viewReceipt"},
            {"item5", "item6", "deleteItems"},
            {"item7", "item8", "receipt"},
            {"proteinBars", "cookies", "receipt"},
    };
    private List<JButton> buttonsForSmoothies = new ArrayList<>();
    private final JButton viewReceiptBtn = new JButton("View Receipt");
    private final JButton deleteItemBtn = new JButton("Delete Items");
    private final JButton blastoffSmoothieBTN = new JButton("Berry Blastoff Smoothie");
    private final JButton butterJellyTimeBTN = new JButton("Peanut Butter Jelly Time");
    private final JButton powerPotionBTN = new JButton("Power Potion");
    private final JButton cucumberSqueezeBTN = new JButton("Cucumber Squeeze");
    private final JButton oatBerryBTN = new JButton("Berry Oat-a-Pult");
    private final JButton hulkBTN = new JButton("Hulk's Secret Garden");
    private final JButton monkeyBusiness = new JButton("Monkey Business");
    private final JButton cyoSmoothieBTN = new JButton("Build a Smoothie!");
    private final JButton proteinBarsBTN = new JButton("View Protein Bars");
    private final JButton cookiesBTN = new JButton("View Cookies");
    private JButton submitButton = new JButton("Submit");

    // Smoothie Instructions panel
    private JLabel instructionsText = new JLabel("");
    JTextArea textArea = new JTextArea();
    private JScrollPane scrollPaneText = new JScrollPane(textArea);

    public MenuScreen() {
        super();
        this.setStringLayout(layoutForMenu);
        buttons();
        setupInstructions();

        this.add("viewReceipt", viewReceiptBtn);
    }

    private void setupInstructions() {
        textArea.setLineWrap(true);
        textArea.setWrapStyleWord(true);

        // Add the scroll pane
        scrollPaneText.setPreferredSize(new Dimension(450, 100));
        // Makes the scrollable only vertically scrollable.
        scrollPaneText.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
        this.add("instructions", scrollPaneText);
    }

    private void buttons(){
        JLabel receiptTitle = new JLabel("Additional Options");
        Font bigFont = receiptTitle.getFont().deriveFont(Font.BOLD, 24f);
        receiptTitle.setFont(bigFont);
        receiptTitle.setVerticalAlignment(SwingConstants.TOP);

        this.add("item1", blastoffSmoothieBTN);
        buttonsForSmoothies.add(blastoffSmoothieBTN);

        this.add("item2", butterJellyTimeBTN);
        buttonsForSmoothies.add(butterJellyTimeBTN);

        this.add("item3", powerPotionBTN);
        buttonsForSmoothies.add(powerPotionBTN);

        this.add("item4", cucumberSqueezeBTN);
        buttonsForSmoothies.add(cucumberSqueezeBTN);

        this.add("item5", oatBerryBTN);
        buttonsForSmoothies.add(oatBerryBTN);

        this.add("item6", hulkBTN);
        buttonsForSmoothies.add(hulkBTN);

        this.add("item7", monkeyBusiness);
        buttonsForSmoothies.add(monkeyBusiness);

        this.add("item8", cyoSmoothieBTN);
        buttonsForSmoothies.add(cyoSmoothieBTN);

        this.add("receipt_text", receiptTitle);

        this.add("proteinBars", proteinBarsBTN);
        this.add("cookies", cookiesBTN);

        for (int i = 0; i < buttonsForSmoothies.size(); i++) {
            JButton eachButton = buttonsForSmoothies.get(i);
            eachButton.setPreferredSize(new Dimension(200, 65));
        }

        submitButton.setPreferredSize(new Dimension(225, 40));
        this.add("submit", submitButton);
        this.add("deleteItems", deleteItemBtn);
    }

    public JButton getSubmitButton() {
        return submitButton;
    }

    public List<JButton> getButtonsForSmoothies() {
        return buttonsForSmoothies;
    }

    public JButton getViewReceiptBtn() {
        return viewReceiptBtn;
    }

    public JButton getProteinBarsBTN() {
        return proteinBarsBTN;
    }

    public JButton getCookiesBTN() {
        return cookiesBTN;
    }

    public JButton getDeleteItemBtn() {
        return deleteItemBtn;
    }
}
