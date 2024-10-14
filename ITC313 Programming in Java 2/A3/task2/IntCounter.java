package task2;

import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;

/**
 * The IntCounter provides counter functionality
 * with an underlying IntegerProperty for observing changes.
 */
public class IntCounter {
    private final IntegerProperty count = new SimpleIntegerProperty(0);

    /**
     * Increments the current count by one.
     */
    public void incrementCount() {
        count.set(count.get() + 1);
    }

    /**
     * Returns the current count value.
     *
     * @return The current count.
     */
    public int getCount() {
        return count.get();
    }

    /**
     * Sets the count to a specific value.
     *
     * @param value The new count value.
     */
    public void setCount(int value) {
        count.set(value);
    }

    /**
     * Returns the IntegerProperty which represents the count.
     * It can be used for binding and observing changes to the count.
     *
     * @return The IntegerProperty related to the count.
     */
    public IntegerProperty countProperty() {
        return count;
    }
}