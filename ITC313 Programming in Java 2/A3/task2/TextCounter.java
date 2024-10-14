package task2;

import javafx.scene.text.Text;

/**
 * Text control that displays the value of an IntCounter.
 * The text of this control is bound to the count property of the provided
 * IntCounter.
 */
public class TextCounter extends Text {

    /**
     * Creates a new TextCounter.
     *
     * @param intCounter The IntCounter whose value will be displayed.
     */
    public TextCounter(IntCounter intCounter) {
        super();
        textProperty().bind(intCounter.countProperty().asString());
        setStyle("-fx-font-size: 48px;");
    }
}