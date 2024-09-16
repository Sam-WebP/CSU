package task3;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.layout.Pane;
import javafx.scene.layout.HBox;
import javafx.scene.shape.Arc;
import javafx.scene.shape.ArcType;
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
 * A JavaFX application that simulates a fan with Start and Reverse controls.
 * The fan blades are arcs, and the fan emits sounds when starting and stopping.
 */
public class FanApp extends Application {

    private Timeline animation;
    private boolean isClockwise = true;
    private AudioClip fanSound;
    private AudioClip clickSound;

    @Override
    public void start(Stage primaryStage) {
        // Create the fan pane
        Pane fanPane = createFan();

        // Load sounds
        loadSounds();

        // Create buttons
        Button startButton = new Button("Start");
        Button reverseButton = new Button("Reverse");

        // Set button actions
        startButton.setOnAction(e -> toggleFan(fanPane, true));
        reverseButton.setOnAction(e -> toggleFan(fanPane, false));

        // Create layout using BorderPane
        BorderPane root = new BorderPane();
        root.setCenter(fanPane);

        // Create an HBox for buttons
        HBox buttonBox = new HBox(10, startButton, reverseButton);
        buttonBox.setAlignment(Pos.CENTER);
        buttonBox.setPadding(new Insets(10));

        root.setBottom(buttonBox);

        Scene scene = new Scene(root, 400, 450);

        primaryStage.setTitle("Fan Application");
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    /**
     * Loads the sound files for the fan and click sounds.
     */
    private void loadSounds() {
        try {
            URL fanSoundURL = getClass().getResource("/task3/resources/fan.wav");
            URL clickSoundURL = getClass().getResource("/task3/resources/click.wav");

            if (fanSoundURL != null && clickSoundURL != null) {
                fanSound = new AudioClip(fanSoundURL.toExternalForm());
                clickSound = new AudioClip(clickSoundURL.toExternalForm());

                // Set volume levels
                fanSound.setCycleCount(AudioClip.INDEFINITE);
                fanSound.setVolume(0.5); // Adjust volume as needed
                clickSound.setVolume(0.5);
            } else {
                System.err.println(
                        "Audio files not found. Please ensure fan.wav and click.wav are in the resources folder.");
            }

        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    /**
     * Creates the fan composed of four arcs as blades.
     *
     * @return Pane containing the fan blades.
     */
    private Pane createFan() {
        Pane pane = new Pane();
        pane.setPrefSize(400, 400);

        // Bindings for center and radius
        DoubleBinding centerX = pane.widthProperty().divide(2.0);
        DoubleBinding centerY = pane.heightProperty().divide(2.0);
        NumberBinding radius = Bindings.min(pane.widthProperty(), pane.heightProperty()).divide(2.0).subtract(20);

        // Create blades with different colors
        Arc blade1 = createBlade(centerX, centerY, radius, 0, Color.BLUE);
        Arc blade2 = createBlade(centerX, centerY, radius, 90, Color.RED);
        Arc blade3 = createBlade(centerX, centerY, radius, 180, Color.GREEN);
        Arc blade4 = createBlade(centerX, centerY, radius, 270, Color.ORANGE);

        pane.getChildren().addAll(blade1, blade2, blade3, blade4);

        return pane;
    }

    /**
     * Creates an individual fan blade as an Arc.
     *
     * @param centerX    Binding for the centerX property.
     * @param centerY    Binding for the centerY property.
     * @param radius     Binding for the radius properties.
     * @param startAngle Starting angle of the arc.
     * @param color      Color of the blade.
     * @return Configured Arc representing a blade.
     */
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

    /**
     * Toggles the fan's running state and direction.
     *
     * @param fanPane   The Pane containing the fan blades.
     * @param clockwise true to run clockwise, false to run counter-clockwise.
     */
    private void toggleFan(Pane fanPane, boolean clockwise) {
        if (animation != null && animation.getStatus() == Animation.Status.RUNNING) {
            stopFan();
        } else {
            isClockwise = clockwise;
            startFan(fanPane);
        }
    }

    /**
     * Starts the fan animation.
     *
     * @param fanPane The Pane containing the fan blades.
     */
    private void startFan(Pane fanPane) {
        createAnimation(fanPane);
        animation.play();
        if (fanSound != null && !fanSound.isPlaying()) {
            fanSound.play();
        }
    }

    /**
     * Stops the fan animation and plays the click sound.
     */
    private void stopFan() {
        if (animation != null) {
            animation.stop();
        }
        if (fanSound != null && fanSound.isPlaying()) {
            fanSound.stop();
        }
        if (clickSound != null) {
            clickSound.play();
        }
    }

    /**
     * Creates the animation Timeline for rotating the fan.
     *
     * @param fanPane The Pane containing the fan blades.
     */
    private void createAnimation(Pane fanPane) {
        if (animation != null) {
            animation.stop();
        }

        animation = new Timeline(
                new KeyFrame(Duration.millis(10), e -> {
                    fanPane.setRotate(fanPane.getRotate() + (isClockwise ? 1 : -1));
                }));
        animation.setCycleCount(Timeline.INDEFINITE);
    }

    public static void main(String[] args) {
        launch(args);
    }
}
