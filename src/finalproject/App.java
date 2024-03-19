package finalproject;

import basicgraphics.BasicFrame;
import finalproject.utils.core.Cart;
import finalproject.utils.core.Smoothie;
import finalproject.utils.core.SmoothieManager;
import finalproject.utils.screens.MenuScreen;
import finalproject.utils.screens.SmoothieCustomizationPage;
import finalproject.utils.screens.StartPage;
import finalproject.utils.screens.ViewReceipt;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * Goal of this app is to be a menu ordering application for a smoothie place. Users can place their order from various of
 * items and then see a receipt and pricing of the order.
 *
 * @author Kameron Arceneaux
 */
public class App {
    BasicFrame bf = new BasicFrame("Smooth Smoothie Spot -- Kiosk"){
        @Override
        public void show() {
            super.show();
            bf.jf.setSize(800, 600);
        }
    };

    private final SmoothieManager smoothieManager = new SmoothieManager();
    private Cart cart = new Cart();
    private MenuScreen menuGame = new MenuScreen();
    private ViewReceipt viewReceipt = new ViewReceipt();

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


        // The button for the startpage that guides the user to the next action.
        startPage.getStartButton().addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                cards.show(content, "menuGame");
                menuGame.requestFocus();
            }
        });

        menuGame.getViewReceiptBtn().addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                cards.show(content, "viewReceipt");
                viewReceipt.requestFocus();
            }
        });

        viewReceipt.getGoBackBTN().addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                cards.show(content,"menuGame");
                menuGame.requestFocus();
            }
        });

        // Responsible for generating the Customization Page for Smoothies
        for (JButton button: menuGame.getButtonsForSmoothies()){
            button.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    Smoothie currentSmoothie = smoothieManager.generateSmoothie(button);
                    // Passes the smoothie corresponding to each button.
                    customizationPage.setInformation(currentSmoothie);
                    customizationPage.setNewSetofIngredients(null);
                    customizationPage.setCurrentSmoothieLbl(currentSmoothie.getName());
                    customizationPage.loadButtonData();
                    cards.show(content, "customizationPage");
                    customizationPage.requestFocus();
                }
            });
        }

        // Modifies a Smoothie Element
        for(JButton button: customizationPage.getAllCustomizationButtons()){
            button.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    customizationPage.modifySmoothieIngredients(button);
                }
            });
        }



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

                //TODO: Add functionality for the reciept text to be shown.
                viewReceipt.setTextForLabel(cart.toString());

                // Return back to menu screen
                cards.show(content, "menuGame");
                menuGame.requestFocus();
            }
        });

        // Functionality for Submitting Instructions/Order
        menuGame.getSubmitButton().addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                System.out.println(cart);
                System.out.println(cart.getTotal());
            }
        });

        bf.show();
    }

    public static void main(String[] args) {
        App app = new App();
        app.run();
    }

}
