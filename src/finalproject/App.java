package finalproject;

import basicgraphics.BasicContainer;
import basicgraphics.BasicFrame;
import finalproject.utils.screens.MenuScreen;
import finalproject.utils.screens.StartPage;

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

    public void run(){
//        bf.jf.setResizable(false);
        final Container content = bf.getContentPane();
        final CardLayout cards = new CardLayout();
        content.setLayout(cards);

        // Make Different Page Screens
        StartPage startPage = new StartPage();
        content.add(startPage, "StartPage");

        MenuScreen menuGame = new MenuScreen();
        content.add(menuGame,"menuGame");

        final BasicContainer bc2 = new BasicContainer();
        content.add(bc2,"Game");

        // The button for the startpage that guides the user to the next action.
        startPage.getStartButton().addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                cards.show(content, "menuGame");
                menuGame.requestFocus();
            }
        });

        bf.show();
    }

    public static void main(String[] args) {
        App app = new App();
        app.run();
    }

}
