package finalproject;

import basicgraphics.BasicContainer;
import basicgraphics.BasicFrame;
import finalproject.utils.screens.StartPage;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * Goal of this app is to be a menu ordering application for a smoothie place. Users can place their order from various of
 * items and then see a receipt and pricing of the order.
 */
public class App {
    BasicFrame bf = new BasicFrame("Smooth Smoothie Spot -- Kiosk"){
        @Override
        public void show() {
            super.show();
            bf.jf.setSize(800, 600);
        }
    };

    static String[][] layoutForMenu = {
            {"instructions", "instructions", "instructions"},
            {"item1", "item2", "receipt_text"},
            {"item3", "item4", "receipt"},
            {"item5", "item6", "receipt"},
            {"item7", "item8", "total_bar"}
    };

    static String[][] generalLayout = {
            {""}
    };

    public void run(){
//        bf.jf.setResizable(false);
        final Container content = bf.getContentPane();
        final CardLayout cards = new CardLayout();
        content.setLayout(cards);

        // Make Different Page Screens
        StartPage startPage = new StartPage();
        content.add(startPage, "StartPage");

        BasicContainer menuGame = new BasicContainer();
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


        menuGame.setStringLayout(layoutForMenu);

        JButton button1 = new JButton("Berry Blastoff Smoothie");
        JButton button2 = new JButton("Peanut Butter Jelly Time");
        JButton button3 = new JButton("Button3");

        JLabel receiptTitle = new JLabel("VIEW RECIEPT: ");
        Font bigFont = receiptTitle.getFont().deriveFont(Font.BOLD, 24f);
        receiptTitle.setFont(bigFont);
        receiptTitle.setVerticalAlignment(SwingConstants.TOP);

        menuGame.add("item1", button1);
        menuGame.add("item2", button2);
        menuGame.add("item3", button3);
        menuGame.add("item4", new JButton("4"));
        menuGame.add("item5", new JButton("5"));
        menuGame.add("item6", new JButton("Button6"));
        menuGame.add("item7", new JButton("Button7"));
        menuGame.add("item8", new JButton("Button8"));
        menuGame.add("receipt_text", receiptTitle);

        button1.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JOptionPane.showMessageDialog(button1, "Button 1 was pressed!");
            }
        });

        button2.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                cards.show(content, "Game");
            }
        });

        bf.jf.setSize(new Dimension(1200, 1200));
        bf.show();
    }

    public static void main(String[] args) {
        App app = new App();
        app.run();

    }

}
