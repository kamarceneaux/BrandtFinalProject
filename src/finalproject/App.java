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
    BasicFrame bf = new BasicFrame("Smooth Smoothie Spot -- Kiosk");

    static String[][] layoutForMenu = {
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
        BasicContainer startPage = new StartPage();
        content.add(startPage, "StartPage");

        BasicContainer bc1 = new BasicContainer();
        content.add(bc1,"MainPage");

        final BasicContainer bc2 = new BasicContainer();
        content.add(bc2,"Game");

        bc1.setStringLayout(layoutForMenu);

        JButton button1 = new JButton("Berry Blastoff Smoothie");
        JButton button2 = new JButton("Peanut Butter Jelly Time");
        JButton button3 = new JButton("Button3");

        JLabel receiptTitle = new JLabel("VIEW RECIEPT: ");
        Font bigFont = receiptTitle.getFont().deriveFont(Font.BOLD, 24f);
        receiptTitle.setFont(bigFont);
        receiptTitle.setVerticalAlignment(SwingConstants.TOP);

        bc1.add("item1", button1);
        bc1.add("item2", button2);
        bc1.add("item3", button3);
        bc1.add("item4", new JButton("4"));
        bc1.add("item5", new JButton("5"));
        bc1.add("item6", new JButton("Button6"));
        bc1.add("item7", new JButton("Button7"));
        bc1.add("item8", new JButton("Button8"));
        bc1.add("receipt_text", receiptTitle);

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
