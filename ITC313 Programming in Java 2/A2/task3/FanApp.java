package task3;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.layout.Pane;
import javafx.scene.layout.HBox;
import javafx.scene.shape.Arc;
import javafx.scene.shape.ArcType;
import javafx.scene.shape.Circle;
import javafx.stage.Stage;
import javafx.scene.paint.Color;
import javafx.animation.Animation;
import javafx.animation.Timeline;
import javafx.animation.KeyFrame;
import javafx.util.Duration;
import javafx.scene.control.Button;
import javafx.geometry.Pos;
import javafx.geometry.Insets;
import javafx.scene.layout.BorderPane;
import javafx.scene.media.AudioClip;
import javafx.beans.binding.Bindings;
import javafx.beans.binding.DoubleBinding;
import javafx.beans.binding.NumberBinding;
import java.net.URL;

/**
 * A JavaFX application that simulates a fan with Start/Stop and Reverse
 * controls.
 */
public class FanApp extends Application {

    private Timeline animation;
    private boolean isClockwise = true;
    private AudioClip fanSound;
    private AudioClip clickSound;
    private Button startStopButton;
    private Pane fanPane; // Declare fanPane at the class level

    @Override
    public void start(Stage primaryStage) {
        fanPane = createFan(); // Initialize fanPane
        loadSounds();
        startStopButton = new Button("Start");
        Button reverseButton = new Button("Reverse");

        startStopButton.setOnAction(e -> toggleFan(false));
        reverseButton.setOnAction(e -> toggleFan(true));

        BorderPane root = new BorderPane();
        root.setCenter(fanPane);

        HBox buttonBox = new HBox(10, startStopButton, reverseButton);
        buttonBox.setAlignment(Pos.CENTER);
        buttonBox.setPadding(new Insets(10));
        root.setBottom(buttonBox);

        Scene scene = new Scene(root, 400, 450);
        primaryStage.setTitle("Fan Application");
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    private void loadSounds() {
        try {
            URL fanSoundURL = getClass().getResource("/task3/resources/fan.wav");
            URL clickSoundURL = getClass().getResource("/task3/resources/click.wav");
            fanSound = new AudioClip(fanSoundURL.toExternalForm());
            clickSound = new AudioClip(clickSoundURL.toExternalForm());
            fanSound.setCycleCount(AudioClip.INDEFINITE);
            fanSound.setVolume(0.1);
            clickSound.setVolume(0.5);
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    private Pane createFan() {
        Pane pane = new Pane();
        pane.setPrefSize(400, 400);

        DoubleBinding centerX = pane.widthProperty().divide(2.0);
        DoubleBinding centerY = pane.heightProperty().divide(2.0);
        NumberBinding radius = Bindings.min(pane.widthProperty(), pane.heightProperty()).divide(2.0).subtract(20);

        for (int i = 0; i < 6; i++) {
            Arc blade = createBlade(centerX, centerY, radius, i * 60, i % 2 == 0 ? Color.PURPLE : Color.BLACK);
            pane.getChildren().add(blade);
        }

        Circle outerCircle = new Circle();
        outerCircle.centerXProperty().bind(centerX);
        outerCircle.centerYProperty().bind(centerY);
        outerCircle.radiusProperty().bind(radius.add(5));
        outerCircle.setFill(Color.TRANSPARENT);
        outerCircle.setStroke(Color.BLACK);
        outerCircle.setStrokeWidth(2);

        pane.getChildren().add(outerCircle);
        return pane;
    }

    private Arc createBlade(DoubleBinding centerX, DoubleBinding centerY, NumberBinding radius, double startAngle,
            Color color) {
        Arc blade = new Arc();
        blade.centerXProperty().bind(centerX);
        blade.centerYProperty().bind(centerY);
        blade.radiusXProperty().bind(radius);
        blade.radiusYProperty().bind(radius);
        blade.setStartAngle(startAngle);
        blade.setLength(40);
        blade.setFill(color);
        blade.setType(ArcType.ROUND);
        return blade;
    }

    private void toggleFan(boolean changeDirection) {
        if (animation == null) {
            startFan();
        } else if (changeDirection) {
            reverseFanDirection();
        } else {
            if (animation.getStatus() == Animation.Status.RUNNING) {
                stopFan();
            } else {
                startFan();
            }
        }
    }

    private void startFan() {
        createAnimation();
        animation.play();
        playFanSound();
        startStopButton.setText("Stop");
    }

    private void stopFan() {
        if (animation != null) {
            animation.stop();
        }
        if (fanSound != null && fanSound.isPlaying()) {
            fanSound.stop();
        }
        playClickSound();
        startStopButton.setText("Start");
    }

    private void reverseFanDirection() {
        isClockwise = !isClockwise;
        // Adjust the existing animation's direction without stopping it
        if (animation != null) {
            animation.getKeyFrames().setAll(new KeyFrame(Duration.millis(5),
                    e -> fanPane.setRotate(fanPane.getRotate() + (isClockwise ? 1 : -1))));
        }
    }

    private void createAnimation() {
        if (animation != null) {
            animation.stop(); // Ensure any existing animation is stopped before creating a new one
        }
        double rotationIncrement = isClockwise ? 1 : -1;
        animation = new Timeline(
                new KeyFrame(Duration.millis(5), e -> fanPane.setRotate(fanPane.getRotate() + rotationIncrement)));
        animation.setCycleCount(Timeline.INDEFINITE);
    }

    private void playFanSound() {
        if (fanSound != null && !fanSound.isPlaying()) {
            fanSound.play();
        }
    }

    private void playClickSound() {
        if (clickSound != null) {
            clickSound.play();
        }
    }

    public static void main(String[] args) {
        launch(args);
    }
}
