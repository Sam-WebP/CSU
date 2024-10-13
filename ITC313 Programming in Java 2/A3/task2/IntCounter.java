package task2;

import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;

public class IntCounter {
    private final IntegerProperty count = new SimpleIntegerProperty(0);

    public void incrementCount() {
        count.set(count.get() + 1);
    }

    public int getCount() {
        return count.get();
    }

    public void setCount(int value) {
        count.set(value);
    }

    public IntegerProperty countProperty() {
        return count;
    }
}