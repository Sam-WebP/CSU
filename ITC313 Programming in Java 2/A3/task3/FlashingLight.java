package task3;

import javafx.application.Platform;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import java.util.concurrent.atomic.AtomicBoolean;

/**
 * Represents a flashing light implemented as a JavaFX Circle.
 * The light changes to a random colour every 20 milliseconds.
 * It can be started, stopped, paused, and resumed.
 */
public class FlashingLight extends Circle implements Runnable {

    private Thread flashThread;
    private final AtomicBoolean isRunning = new AtomicBoolean(false);
    private final AtomicBoolean isPaused = new AtomicBoolean(false);
    private final double radius;

    /**
     * Creates a new FlashingLight with the specified radius.
     * The initial colour of the light is black before the flashing starts.
     *
     * @param radius The radius of the circle light.
     */
    public FlashingLight(double radius) {
        super(radius);
        this.radius = radius;
        this.setFill(Color.BLACK); // Initial colour
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
        flashThread.setDaemon(true);
        flashThread.start();
    }

    /**
     * Stops the flashing animation and resets the light to its initial "off"
     * colour.
     */
    public void stopFlashing() {
        isRunning.set(false);
        isPaused.set(false); // Ensure paused state is reset after stopping
        if (flashThread != null) {
            flashThread.interrupt();
        }
        // Reset light colour to be blank
        Platform.runLater(() -> this.setFill(Color.BLACK));
    }

    /**
     * Pauses the flashing.
     */
    public void pauseFlashing() {
        isPaused.set(true);
    }

    /**
     * Resumes the flashing animation if it was previously paused.
     */
    public void resumeFlashing() {
        isPaused.set(false);
        synchronized (isPaused) {
            isPaused.notify();
        }
    }

    /**
     * The main loop for the flashing animation. Runs on a separate thread.
     * Changes the colour of the light to a random colour every 20 milliseconds.
     */
    @Override
    public void run() {
        try {
            while (isRunning.get()) {
                if (isPaused.get()) {
                    synchronized (isPaused) {
                        isPaused.wait();
                    }
                }
                // Change to a random colour
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
