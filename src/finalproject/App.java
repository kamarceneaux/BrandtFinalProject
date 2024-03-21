package finalproject;

import basicgraphics.BasicFrame;
import basicgraphics.ClockWorker;
import finalproject.utils.core.Cart;
import finalproject.utils.core.Item;
import finalproject.utils.core.Smoothie;
import finalproject.utils.core.managers.ItemManager;
import finalproject.utils.core.managers.SmoothieManager;
import finalproject.utils.screens.*;

import javax.swing.*;
import java.awt.CardLayout;
import java.awt.Color;
import java.awt.Container;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * Goal of this app is to be a menu ordering application for a smoothie place. Users can place their order from various of
 * items and then see a receipt and pricing of the order.
 * Users will be assigned a series of instructions and have to follow the instructions.
 *
 * @author Kameron Arceneaux
 */
public class App {
    BasicFrame bf = new BasicFrame("Smooth Smoothie Spot -- Kiosk"){
        @Override
        public void show() {
            super.show();
            bf.jf.setSize(800, 600);
            bf.jf.setResizable(false);
        }
    };

    private final SmoothieManager smoothieManager = new SmoothieManager();
    private Cart cart = new Cart();
    private MenuScreen menuGame = new MenuScreen();
    private ViewReceipt viewReceipt = new ViewReceipt();
    private ItemManager itemManager = new ItemManager();

    public void run(){
        final Container content = bf.getContentPane();
        final CardLayout cards = new CardLayout();
        content.setLayout(cards);

        // Make Different Page Screens
        StartPage startPage = new StartPage();
        content.add(startPage, "StartPage");

        content.add(menuGame,"menuGame");
        content.add(viewReceipt, "viewReceipt");

        SmoothieCustomizationPage customizationPage = new SmoothieCustomizationPage();
        content.add(customizationPage,"customizationPage");

        BarPage barPage = new BarPage();
        content.add(barPage, "barPage");

        // The button for the startpage that guides the user to the next action.
        startPage.getStartButton().addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                cards.show(content, "menuGame");
                menuGame.requestFocus();
            }
        });

        // Listener to get to all the possible protein bars to pick from.
        menuGame.getProteinBarsBTN().addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                cards.show(content, "barPage");
                barPage.requestFocus();
            }
        });


        // View the receipt of an order
        menuGame.getViewReceiptBtn().addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                cards.show(content, "viewReceipt");
                viewReceipt.requestFocus();
            }
        });


        // From the receipt page, go back to the main page
        viewReceipt.getGoBackBTN().addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                cards.show(content,"menuGame");
                menuGame.requestFocus();
            }
        });

        // Responsible for generating the Customization Page for Smoothies
        for (JButton button: menuGame.getButtonsForSmoothies()){
            showSpecificSmoothieCustomizationPage(button, customizationPage, cards, content);
        }


        // Modifies a Smoothie Ingredient
        for(JButton button: customizationPage.getAllCustomizationButtons()){
            button.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    customizationPage.modifySmoothieIngredients(button);
                }
            });
        }
        returnToPrevPageFromSmoothieCustomization(customizationPage, cards, content);
        processSmoothieToCartSubmission(customizationPage, cards, content);
        fromItemScreenGoToMenuScreen(barPage.getGoBackBtn(), cards, content);

        // Any Actions Involving the Protein Bars or Cookies
        actionsWithProteinBarBtns(barPage, cart);
        submitOrder();

        bf.show();
    }

    private void showSpecificSmoothieCustomizationPage(JButton button, SmoothieCustomizationPage customizationPage, CardLayout cards, Container content) {
        button.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Smoothie currentSmoothie = smoothieManager.generateSmoothie(button);
                // Passes the smoothie corresponding to each button.
                customizationPage.setInformation(currentSmoothie);

                // Resets the ingredients for the project
                customizationPage.setNewSetofIngredients(null);
                customizationPage.setCurrentSmoothieLbl(currentSmoothie.getName());

                // Loads data about a button
                customizationPage.loadButtonData();
                cards.show(content, "customizationPage");
                customizationPage.requestFocus();
            }
        });
    }

    private void returnToPrevPageFromSmoothieCustomization(SmoothieCustomizationPage customizationPage, CardLayout cards, Container content) {
        // Responsible for going back to previous page
        customizationPage.getBackButton().addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Empty/Delete Previous Data
                customizationPage.setNewSetofIngredients(null);
                customizationPage.getWorkingSmoothie().setModifiedIngredients(null);

                // Resets Colors for Buttons
                for (int i = 0; i < customizationPage.getAllCustomizationButtons().size(); i++) {
                    JButton btn = customizationPage.getAllCustomizationButtons().get(i);
                    btn.setForeground(Color.black);
                }

                cards.show(content, "menuGame");
                menuGame.requestFocus();
            }
        });
    }

    private void processSmoothieToCartSubmission(SmoothieCustomizationPage customizationPage, CardLayout cards, Container content) {
        //Functionality for Submitting the customization of a smoothie
        customizationPage.getSubmitButton().addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Call the submit function on the customization page which is responsible for ensuring ingredients are right
                if(customizationPage.processSubmission()){
                    customizationPage.getWorkingSmoothie().compareIngredients();

                    // Add items to the cart
                    if(customizationPage.getWorkingSmoothie().getIngredients() != null){
                        cart.addItem(customizationPage.getWorkingSmoothie());
                    }

                    // Reset Buttons
                    for (int i = 0; i < customizationPage.getAllCustomizationButtons().size(); i++) {
                        JButton btn = customizationPage.getAllCustomizationButtons().get(i);
                        btn.setForeground(Color.black);
                    }
                }

                // Update the text for the label
                updateReceiptText();

                // Return back to menu screen
                cards.show(content, "menuGame");
                menuGame.requestFocus();
            }
        });
    }

    private void updateReceiptText() {
        viewReceipt.setTextForLabel(cart.toString());
    }

    private void fromItemScreenGoToMenuScreen(JButton barPage, CardLayout cards, Container content) {
        // Functionality for Going back to menuscreen from item screen
        barPage.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                cards.show(content, "menuGame");
                menuGame.requestFocus();
            }
        });
    }

    private void submitOrder() {
        // Functionality for Submitting Instructions/Order
        menuGame.getSubmitButton().addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                System.out.println(cart);
                System.out.println(cart.getTotal());
            }
        });
    }

    private void actionsWithProteinBarBtns(BarPage barPage, Cart cart) {
        // Responsible for the actions whenever you add a protein bar
        for(JButton button: barPage.getAllOptionsForBtns()){
            button.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    Item currentItem = itemManager.generateProteinBar(button);
                    cart.addItem(currentItem);
                    JOptionPane.showMessageDialog(barPage, String.format("Successfully added one (1) %s", currentItem.getName()),
                            "Successfully Added The Item", 1);
                    updateReceiptText();
                }
            });
        }
    }

    public static void main(String[] args) {
        App app = new App();
        app.run();
    }
}
