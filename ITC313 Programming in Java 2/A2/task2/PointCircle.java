package task2;

import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;

/**
 * A custom Circle that represents a Point and binds its position to the Point's
 * coordinates.
 */
public class PointCircle extends Circle {
    private static final double RADIUS = 5.0;
    private final Point point;

    /**
     * Constructs a PointCircle associated with a Point.
     *
     * @param point The Point to associate with this circle.
     */
    public PointCircle(Point point) {
        super(RADIUS, Color.BLUE);
        this.point = point;
        // Bind centerX and centerY to the point's x and y properties
        centerXProperty().bindBidirectional(point.xProperty());
        centerYProperty().bindBidirectional(point.yProperty());
    }

    /**
     * Gets the associated Point.
     *
     * @return The associated Point.
     */
    public Point getPoint() {
        return point;
    }
}