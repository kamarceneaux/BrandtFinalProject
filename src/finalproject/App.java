package finalproject;

import basicgraphics.BasicFrame;
import basicgraphics.SpriteComponent;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * Goal of this app is to be a menu ordering application for a smoothie place. Users can place their order from various of
 * items and then see a receipt and pricing of the order.
 */
public class App {
    final public static Dimension BOARD_SIZE = new Dimension(800,400);
    BasicFrame bf = new BasicFrame("Smooth Smoothie Spot -- Kiosk");

    static String[][] layout = {
            {"item1", "item2", "receipt_text"},
            {"item3", "item4", "receipt"},
            {"item5", "item6", "receipt"},
            {"item7", "item8", "total_bar"}
    };

    public void run(){
        bf.setStringLayout(layout);

        JButton button1 = new JButton("Button1");
        JLabel receiptTitle = new JLabel("VIEW RECIEPT: ");
        Font bigFont = receiptTitle.getFont().deriveFont(Font.BOLD, 24f);
        receiptTitle.setFont(bigFont);
        receiptTitle.setVerticalAlignment(SwingConstants.TOP);

        bf.add("item1", button1);
        bf.add("item2", new JButton("Button2"));
        bf.add("item3", new JButton("Button3"));
        bf.add("item4", new JButton("Button4"));
        bf.add("item5", new JButton("Button5"));
        bf.add("item6", new JButton("Button6"));
        bf.add("item7", new JButton("Button7"));
        bf.add("item8", new JButton("Button8"));
        bf.add("receipt_text", receiptTitle);

        button1.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JOptionPane.showMessageDialog(button1, "Button 1 was pressed!");
            }
        });

        bf.show();
    }

    public static void main(String[] args) {
        App app = new App();
        app.run();

    }

}
