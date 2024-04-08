package finalproject.utils.screens;

import basicgraphics.BasicContainer;

public class EndGameScreen extends BasicContainer {
    private int timeConsumed;
    private int maxTimeAllowed;
    private String message;
    private boolean success;

    private static String[][] successLayout = {
            {"message", "message", "restartGame"},
            {"success", "success", "success"},
            {"success", "success", "success"}
    };

    private static String[][] failLayout = {
            {"message", "message", "restartGame"},
            {"success", "success", "success"},
            {"success", "success", "success"}
    };
}
