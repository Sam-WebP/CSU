package task2;

import javafx.scene.text.Text;

public class TextCounter extends Text {
    public TextCounter(IntCounter intCounter) {
        super();
        textProperty().bind(intCounter.countProperty().asString());
        setStyle("-fx-font-size: 48px;"); // Optional: Enhance visibility
    }
}