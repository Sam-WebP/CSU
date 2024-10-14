package task3;

import javafx.scene.paint.Color;

import java.util.Random;

/**
 * A utility class for generating random colors.
 */
public class ColorUtil {

    private static final Random random = new Random();

    /**
     * Generates a random Colour.
     *
     * @return a random Colour
     */
    public static Color generateRandomColor() {
        return Color.color(random.nextDouble(), random.nextDouble(), random.nextDouble());
    }
}
