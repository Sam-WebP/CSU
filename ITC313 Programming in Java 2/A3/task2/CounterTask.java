package task2;

import javafx.application.Platform;

public class CounterTask implements Runnable {
    private final IntCounter intCounter;
    private volatile long delay;
    private volatile boolean running = true;
    private volatile boolean paused = false;
    private final Object pauseLock = new Object();

    public CounterTask(IntCounter intCounter, long delay) {
        this.intCounter = intCounter;
        this.delay = delay;
    }

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

    public void pause() {
        paused = true;
    }

    public void resumeTask() {
        synchronized (pauseLock) {
            paused = false;
            pauseLock.notifyAll();
        }
    }

    public void stop() {
        running = false;
        resumeTask(); // In case it's paused, to allow the loop to exit
    }

    public void setDelay(long delay) {
        if (delay > 0) {
            this.delay = delay;
        }
    }

    public long getDelay() {
        return delay;
    }
}