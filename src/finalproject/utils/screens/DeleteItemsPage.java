package finalproject.utils.screens;

import basicgraphics.BasicContainer;
import finalproject.utils.core.Cart;

import javax.swing.*;
import java.awt.*;

public class DeleteItemsPage extends BasicContainer {
    private Cart cart;
    private final JButton goBackBTN = new JButton("Return to Menu Page");
    private final JLabel instructionsLbl = new JLabel();
    private final JTextField textInput = new JTextField(15);
    private final JButton deleteItemsBtn = new JButton("Delete Items");
    private String textForLabel = " ";
    private JLabel text = new JLabel(textForLabel);
    private JScrollPane scrollPaneText = new JScrollPane(text);
    private int itemIndex;


    private String[][] layout = {
            {"backBtn", "instructions"},
            {"currentReceipt", "currentReceipt"},
            {"typeNumbers", "submitRequest"}
    };

    public DeleteItemsPage(Cart cart) {
        super();
        setStringLayout(layout);
        initalizeBtns();
        initializeReceiptTab();
        this.cart = cart;
    }

    private void initalizeBtns(){
        // Add buttons and Labels to the screen
        add("backBtn", goBackBTN);

        //Instructions Label
        instructionsLbl.setText("<html> To delete a item, enter the item number corresponding the item.<br>" +
                "To delete multiple items, enter the items in a comma delimited list <br>" +
                "ex: 1,2,3 --> would delete items one, two and three from a cart with three items.</html>");
        add("instructions", instructionsLbl);

        // Add input label
        add("typeNumbers", textInput);

        // Delete Items Button
        add("submitRequest", deleteItemsBtn);

    }

    /**
     * Generate the scrollable receipt page view.
     */
    private void initializeReceiptTab() {
        // Makes sure it can cover most of the space.
        scrollPaneText.setPreferredSize(new Dimension(450, 300));

        // Makes the scrollable only vertically scrollable.
        scrollPaneText.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
        this.add("currentReceipt", scrollPaneText);
    }

    public JButton getGoBackBTN() {
        return goBackBTN;
    }

    public JButton getDeleteItemsBtn() {
        return deleteItemsBtn;
    }

    public JTextField getTextInput() {
        return textInput;
    }

    public void deleteItems() throws Exception{
        // Separate the comma delimited list
        String textAsString = textInput.getText().strip();
        String[] itemIds = textAsString.split("\\s*,\\s*");

        // Gets all the indexes
        int[] nums = new int[itemIds.length];
        for (int i = 0; i < itemIds.length; i++) {
            try{
                nums[i] = Integer.parseInt(itemIds[i]);
            }catch (NumberFormatException e){
                throw new Exception("Either text was passed in OR there is one of more empty pieces of text.");
            }
        }

        // Loop through all the indexes and see if it possible to delete the item
        for (int i = 0; i < nums.length; i++) {
            int actIndex = nums[i] - 1;
            if(actIndex < 0 || actIndex >= cart.getItems().size()){
                if(cart.getItems().size() == 0){
                    throw new Exception("Cannot remove items from a empty cart.");
                }
                throw new Exception("One of the item ids are invalid. Cannot be a negative number or a id not on the list.");
            }
            cart.setTotal(cart.getTotal() - cart.getItems().get(actIndex).getPrice());
            cart.getItems().remove(actIndex);
        }

    }

    /**
     * Responsible for updating the delete page receipt.
     */
    public void setTextForLabel() {
        StringBuilder sb = new StringBuilder();
        sb.append("<html>");
        itemIndex = 0;
        for (int i = 0; i < cart.getItems().size(); i++) {
            sb.append(String.format("<i>Item Code</i>: <em>%d</em><br>", ++itemIndex));
            sb.append(cart.getItems().get(i));
            sb.append("___________________________<br>");
        }
        sb.append("</html>");
        text.setText(sb.toString());
    }
}
