package task2;

import javafx.beans.property.FloatProperty;
import javafx.beans.property.SimpleFloatProperty;

/**
 * Represents a point on a two-dimensional plane with x and y coordinates.
 */
public class Point {
    private final FloatProperty x;
    private final FloatProperty y;

    /**
     * Constructs a Point with specified x and y coordinates.
     *
     * @param x The x-coordinate.
     * @param y The y-coordinate.
     */
    public Point(float x, float y) {
        this.x = new SimpleFloatProperty(x);
        this.y = new SimpleFloatProperty(y);
    }

    public float getX() {
        return x.get();
    }

    public void setX(float x) {
        this.x.set(x);
    }

    public FloatProperty xProperty() {
        return x;
    }

    public float getY() {
        return y.get();
    }

    public void setY(float y) {
        this.y.set(y);
    }

    public FloatProperty yProperty() {
        return y;
    }
}
