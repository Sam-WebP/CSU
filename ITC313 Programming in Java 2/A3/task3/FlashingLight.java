package task3;

import javafx.application.Platform;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import java.util.concurrent.atomic.AtomicBoolean;

public class FlashingLight extends Circle implements Runnable {

    private Thread flashThread;
    private final AtomicBoolean isRunning = new AtomicBoolean(false);
    private final AtomicBoolean isPaused = new AtomicBoolean(false);
    private final double radius;

    public FlashingLight(double radius) {
        super(radius);
        this.radius = radius;
        this.setFill(Color.BLACK); // Initial color
    }

    /**
     * Starts the flashing thread.
     */
    public void startFlashing() {
        if (isRunning.get()) {
            return; // Already running
        }
        isRunning.set(true);
        flashThread = new Thread(this);
        flashThread.setDaemon(true); // Daemon thread exits when the application exits
        flashThread.start();
    }

    /**
     * Stops the flashing thread.
     */
    public void stopFlashing() {
        isRunning.set(false);
        isPaused.set(false); // Ensure paused state is reset after stopping
        if (flashThread != null) {
            flashThread.interrupt();
        }
        // Reset light colour to be blank
        Platform.runLater(() -> this.setFill(Color.BLACK)); // Or any initial state
    }

    /**
     * Pauses the flashing.
     */
    public void pauseFlashing() {
        isPaused.set(true);
    }

    /**
     * Resumes the flashing.
     */
    public void resumeFlashing() {
        isPaused.set(false);
        synchronized (isPaused) {
            isPaused.notify();
        }
    }

    @Override
    public void run() {
        try {
            while (isRunning.get()) {
                if (isPaused.get()) {
                    synchronized (isPaused) {
                        isPaused.wait();
                    }
                }
                // Change to a random color
                Color randomColor = ColorUtil.generateRandomColor();
                Platform.runLater(() -> this.setFill(randomColor));

                Thread.sleep(20);
            }
        } catch (InterruptedException e) {
            // Thread interrupted, exit gracefully
            Thread.currentThread().interrupt();
        }
    }
}
