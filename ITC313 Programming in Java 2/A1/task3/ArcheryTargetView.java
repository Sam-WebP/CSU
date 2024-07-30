package task3;

import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;

/**
 * Represents the visual component of the Archery Target.
 * Draws circles to form the target.
 */
public class ArcheryTargetView extends Pane {

    /**
     * Array of colours used for the circles of the archery target,
     * from outermost to innermost.
     */
    private static final Color[] CIRCLE_COLORS = {
            Color.WHITE, Color.BLACK, Color.CYAN, Color.RED, Color.GOLD
    };

    /**
     * Constructor with the specified width and height.
     * The background is initialised and draws the circles of the target.
     *
     * @param width  width of the view
     * @param height height of the view
     */
    public ArcheryTargetView(double width, double height) {
        setStyle("-fx-background-color: darkseagreen;");

        double centerX = width / 2;
        double centerY = height / 2;
        double maxRadius = Math.min(width, height) * 0.45;

        for (int i = 0; i < CIRCLE_COLORS.length; i++) {
            double radius = maxRadius * (CIRCLE_COLORS.length - i) / CIRCLE_COLORS.length;
            Circle circle = new Circle(centerX, centerY, radius, CIRCLE_COLORS[i]);
            getChildren().add(circle);
        }
    }
}