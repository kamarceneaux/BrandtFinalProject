package finalproject.utils.screens;

import basicgraphics.BasicContainer;
import basicgraphics.ClockWorker;
import basicgraphics.SpriteComponent;

import javax.swing.*;
import java.awt.*;
import java.util.Random;

public class EndGameScreen extends BasicContainer {
    private int timeConsumed;
    private int maxTimeAllowed;
    private String message;
    private boolean success;
    private JButton restartGame = new JButton("Restart Game");
    private SpriteComponent sc;
    private Random rand = new Random();
    private JTextArea textArea = new JTextArea();
    private JScrollPane scrollPaneText = new JScrollPane(textArea);

    private static String[][] successLayout = {
            {"message", "message", "restartGame"},
            {"content", "content", "content"},
            {"content", "content", "content"}
    };

    private static String[][] failLayout = {
            {"message", "message", "restartGame"},
            {"content", "content", "content"},
            {"content", "content", "content"}
    };

    public EndGameScreen(int timeConsumed, int maxTimeAllowed, String message) {
        super();
        this.timeConsumed = timeConsumed;
        this.maxTimeAllowed = maxTimeAllowed;
        this.message = message;
        String successGame = "The customer successfully received their order! They are very happy :)";

        // Determine which layout to use
        if(timeConsumed > maxTimeAllowed && this.message.equalsIgnoreCase(successGame)){
            this.message = "Customer felt like you took too long placing their order. Remember our business relies on speed!!";
        }

        // Content Screen
        if(this.message.equalsIgnoreCase(successGame)){
            setStringLayout(successLayout);
            sc = new SpriteComponent(){
                @Override
                public void paintBackground(Graphics g) {
                    Dimension d = getSize();
                    g.setColor(new Color(9, 214, 255));
                    g.fillRect(0, 0, d.width, d.height);
                }
            };
            sc.setSize(new Dimension(500, 400));
        }else{
            setStringLayout(failLayout);
            sc = new SpriteComponent(){
                @Override
                public void paintBackground(Graphics g) {
                    Dimension d = getSize();
                    g.setColor(new Color(65, 72, 73));
                    g.fillRect(0, 0, d.width, d.height);

                    g.setColor(new Color(18, 112, 236));
                    for (int i = 0; i < 30; i++) {
                        int diameter = 6;
                        int xpos = rand.nextInt(d.width);
                        int ypos = rand.nextInt(d.height);
                        g.fillOval(xpos, ypos, diameter, diameter);
                    }

                }
            };
        }

        sc.setPreferredSize(new Dimension(750, 450));


        buttons();
        textArea.setText(this.message);
        this.add("content", sc);
        ClockWorker.addTask(sc.moveSprites());
    }

    // Generate Buttons
    private void buttons(){
        this.add("restartGame", restartGame);

        // Responsible for message to be displayed
        textArea.setLineWrap(true);
        textArea.setWrapStyleWord(true);

        // Add the scroll pane
        scrollPaneText.setPreferredSize(new Dimension(350, 100));
        // Makes the scrollable only vertically scrollable.
        scrollPaneText.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
        this.add("message", scrollPaneText);
    }

    public JButton getRestartGame() {
        return restartGame;
    }
}
