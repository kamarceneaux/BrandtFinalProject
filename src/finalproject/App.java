package finalproject;

import basicgraphics.BasicFrame;
import finalproject.utils.core.Cart;
import finalproject.utils.core.GameInformationError;
import finalproject.utils.core.Item;
import finalproject.utils.core.Smoothie;
import finalproject.utils.core.logic.GameLogicManager;
import finalproject.utils.core.logic.Stopwatch;
import finalproject.utils.core.managers.ItemManager;
import finalproject.utils.core.managers.SmoothieManager;
import finalproject.utils.screens.*;

import javax.swing.*;
import java.awt.*;
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
    BasicFrame bf = new BasicFrame("Smooth Smoothie Spot -- Kiosk") {
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
    private DeleteItemsPage deleteItemsPage = new DeleteItemsPage(cart);
    private ItemManager itemManager = new ItemManager();
    private GameLogicManager gameLogicManager = new GameLogicManager();
    private Stopwatch stopwatch = new Stopwatch();
    private EndGameScreen summaryPage = new EndGameScreen(0, 0, "...");
    private CardLayout cards;
    private Container content;

    public void run() {
        content = bf.getContentPane();
        cards = new CardLayout();
        content.setLayout(cards);

        // Make Different Page Screens
        StartPage startPage = new StartPage();
        content.add(startPage, "StartPage");

        content.add(menuGame, "menuGame");
        content.add(viewReceipt, "viewReceipt");
        content.add(deleteItemsPage, "deleteItems");

        SmoothieCustomizationPage customizationPage = new SmoothieCustomizationPage();
        content.add(customizationPage, "customizationPage");

        BarPage barPage = new BarPage();
        content.add(barPage, "barPage");

        CookiePage cookiePage = new CookiePage();
        content.add(cookiePage, "cookiePage");

        // The button for the startpage that guides the user to the next action.
        startPage.getStartButton().addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                gameLogicManager.startLogic();
                cards.show(content, "menuGame");
                menuGame.requestFocus();
                menuGame.setInstructionsText(gameLogicManager.getInstructions());

                // Start the stopwatch
                stopwatch.start();
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

        // Listener to get to COOKIE SCREEN
        menuGame.getCookiesBTN().addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                cards.show(content, "cookiePage");
                cookiePage.requestFocus();
            }
        });

        //From MenuPage --> DeleteItem
        menuGame.getDeleteItemBtn().addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                cards.show(content, "deleteItems");
                deleteItemsPage.requestFocus();
            }
        });

        // From DeleteItem --> MenuPage without saving items.
        deleteItemsPage.getGoBackBTN().addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                cards.show(content, "menuGame");
                menuGame.requestFocus();
            }
        });

        // From DeleteItem --> process deleting items
        deleteItemsPage.getDeleteItemsBtn().addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    deleteItemsPage.deleteItems();
                } catch (Exception ex) {
                    JOptionPane.showMessageDialog(deleteItemsPage, ex.getMessage());
                } finally {
                    deleteItemsPage.getTextInput().setText("");
                    deleteItemsPage.setTextForLabel();
                    viewReceipt.setTextForLabel(cart.toString());
                }

                JOptionPane.showMessageDialog(deleteItemsPage, "Successfully removed the item.");

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
                cards.show(content, "menuGame");
                menuGame.requestFocus();
            }
        });

        // Responsible for generating the Customization Page for Smoothies
        for (JButton button : menuGame.getButtonsForSmoothies()) {
            showSpecificSmoothieCustomizationPage(button, customizationPage, cards, content);
        }


        // Modifies a Smoothie Ingredient
        for (JButton button : customizationPage.getAllCustomizationButtons()) {
            button.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    customizationPage.modifySmoothieIngredients(button);
                }
            });
        }
        returnToPrevPageFromSmoothieCustomization(customizationPage, cards, content);
        processSmoothieToCartSubmission(customizationPage, cards, content, deleteItemsPage);

        // Responsible for the go back buttons
        fromItemScreenGoToMenuScreen(barPage.getGoBackBtn(), cards, content);
        fromItemScreenGoToMenuScreen(cookiePage.getGoBackBtn(), cards, content);

        // Any Actions Involving the Protein Bars or Cookies
        actionsWithProteinBarBtns(barPage, cart, deleteItemsPage);
        actionsWithCookieBtns(cookiePage, cart, deleteItemsPage);

        // Responsibility for whenever a order is submitted
        submitOrder();

        bf.show();
    }

    /**
     * This shows the specific smoothie customization page for the smoothie.
     */
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

    private void processSmoothieToCartSubmission(SmoothieCustomizationPage customizationPage, CardLayout cards, Container content, DeleteItemsPage dp) {
        //Functionality for Submitting the customization of a smoothie
        customizationPage.getSubmitButton().addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Call the submit function on the customization page which is responsible for ensuring ingredients are right
                if (customizationPage.processSubmission()) {
                    customizationPage.getWorkingSmoothie().compareIngredients();

                    // Add items to the cart
                    if (customizationPage.getWorkingSmoothie().getIngredients() != null) {
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
                dp.setTextForLabel();


                // Return back to menu screen
                cards.show(content, "menuGame");
                menuGame.requestFocus();
            }
        });
    }

    /**
     * Update the receiptText based on an action.
     */
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

    /**
     * Responsible for submitting the current order to be processed.
     */
    private void submitOrder() {
        // Functionality for Submitting Instructions/Order
        menuGame.getSubmitButton().addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Check Game Logic
                String exception = "The customer successfully received their order! They are very happy :)";


                try {
                    // Check Game Logic
                    gameLogicManager.checkScenarioZero(cart);
                } catch (GameInformationError ex) {
                    exception = ex.getMessage();
                } finally {
                    System.out.println(exception);

                    // House cleaning and restart the game
                    cart.resetCart();
                    updateReceiptText();
                    deleteItemsPage.setTextForLabel();

                    stopwatch.stop();
                    int timeTaken = stopwatch.elapsedTimeInSeconds();
                    System.out.println(timeTaken);

                    // Show the new page
                    summaryPage = new EndGameScreen(timeTaken, gameLogicManager.getDesiredTime(), exception);
                    content.add(summaryPage, "summaryPage");
                    cards.show(content, "summaryPage");
                    summaryPage.requestFocus();

                    stopwatch.reset();

                    // Restart Button
                    restartGame();
                }
            }

        });
    }

    /**
     * Actions for dealing with protein bars
     */
    private void actionsWithProteinBarBtns(BarPage barPage, Cart cart, DeleteItemsPage dp) {
        // Responsible for the actions whenever you add a protein bar
        for (JButton button : barPage.getAllOptionsForBtns()) {
            button.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    Item currentItem = itemManager.generateProteinBar(button);
                    cart.addItem(currentItem);
                    JOptionPane.showMessageDialog(barPage, String.format("Successfully added one (1) %s", currentItem.getName()),
                            "Successfully Added The Item", 1);
                    updateReceiptText();
                    dp.setTextForLabel();
                }
            });
        }
    }

    /**
     * Actions for dealing adding cookies
     */
    private void actionsWithCookieBtns(CookiePage cookiePage, Cart cart, DeleteItemsPage dp) {
        for (JButton btn : cookiePage.getAllOptionsForBtns()) {
            btn.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    Item currentItem = itemManager.generateCookie(btn);
                    cart.addItem(currentItem);
                    JOptionPane.showMessageDialog(cookiePage, String.format("Successfully added one (1) %s", currentItem.getName()),
                            "Successfully Added The Item", 1);
                    updateReceiptText();
                    dp.setTextForLabel();
                }
            });
        }
    }

    /**
     * Responsible for restarting the game.
     */
    private void restartGame() {
        summaryPage.getRestartGame().addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Generates new logic for the next game
                gameLogicManager.getCorrectItemsCart().resetCart();
                gameLogicManager.startLogic();

                // Return back to the menuPage
                cards.show(content, "menuGame");
                menuGame.requestFocus();

                // Reshow instructions
                menuGame.setInstructionsText(gameLogicManager.getInstructions());

                // Start the stopwatch
                stopwatch.start();
            }
        });
    }

    public static void main(String[] args) {
        App app = new App();
        app.run();
    }
}
