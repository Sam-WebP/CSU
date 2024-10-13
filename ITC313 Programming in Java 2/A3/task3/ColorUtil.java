package task3;

import javafx.scene.paint.Color;

import java.util.Random;

public class ColorUtil {

    private static final Random random = new Random();

    /**
     * Generates a random Color.
     *
     * @return a random Color
     */
    public static Color generateRandomColor() {
        return Color.color(random.nextDouble(), random.nextDouble(), random.nextDouble());
    }
}
