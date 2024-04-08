package finalproject.utils.core.logic;

public class Stopwatch {
    private static long startTime;
    private static long stopTime;
    private static boolean running;

    public void start() {
        if (!running) {
            startTime = System.currentTimeMillis();
            running = true;
            System.out.println("Stopwatch started.");
        } else {
            System.out.println("Stopwatch is already running.");
        }
    }

    public void stop() {
        if (running) {
            stopTime = System.currentTimeMillis();
            running = false;
            System.out.println("Stopwatch stopped.");
        } else {
            System.out.println("Stopwatch is not running.");
        }
    }

    public void reset() {
        startTime = 0;
        stopTime = 0;
        running = false;
    }

    private long elapsedTime() {
        if (running) {
            return System.currentTimeMillis() - startTime;
        } else {
            return stopTime - startTime;
        }
    }

    public int elapsedTimeInSeconds() {
        long elapsedMilliseconds = elapsedTime();
        return (int) (elapsedMilliseconds / 1000);
    }

}
