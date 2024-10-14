package task2;

import javafx.application.Platform;

/**
 * Implements a runnable task that increments a counter at a specified interval.
 */
public class CounterTask implements Runnable {
    private final IntCounter intCounter;
    private volatile long delay;
    private volatile boolean running = true;
    private volatile boolean paused = false;
    private final Object pauseLock = new Object();

    /**
     * Constructs a new CounterTask
     *
     * @param intCounter The {@link IntCounter} object to increment.
     * @param delay      The delay in milliseconds between counter increments.
     */
    public CounterTask(IntCounter intCounter, long delay) {
        this.intCounter = intCounter;
        this.delay = delay;
    }

    /**
     * Runs the counter task.
     * Increments the associated IntCounter on a Thread at the specified delay
     * Handles pausing and resuming of the task.
     */
    @Override
    public void run() {
        try {
            while (running) {
                // Handle pausing
                synchronized (pauseLock) {
                    while (paused) {
                        pauseLock.wait();
                    }
                }

                // Update the counter on the JavaFX Application Thread
                Platform.runLater(intCounter::incrementCount);

                // Sleep for the specified delay
                Thread.sleep(delay);
            }
        } catch (InterruptedException e) {
            // Thread interrupted; exit gracefully
            Thread.currentThread().interrupt();
        }
    }

    /**
     * Pauses the counter task.
     */
    public void pause() {
        paused = true;
    }

    /**
     * Resumes the counter task after it has been paused.
     */
    public void resumeTask() {
        synchronized (pauseLock) {
            paused = false;
            pauseLock.notifyAll();
        }
    }

    /**
     * Stops the counter task.
     */
    public void stop() {
        running = false;
        resumeTask(); // In case it's paused, to allow the loop to exit
    }

    /**
     * Sets the delay between counter increments.
     *
     * @param delay The delay in milliseconds. Must be greater than 0.
     */
    public void setDelay(long delay) {
        if (delay > 0) {
            this.delay = delay;
        }
    }

    /**
     * Returns the current delay between counter increments.
     *
     * @return The delay in milliseconds.
     */
    public long getDelay() {
        return delay;
    }
}