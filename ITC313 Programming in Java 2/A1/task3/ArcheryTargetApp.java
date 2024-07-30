package task3;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.stage.Stage;

/**
 * This is the main entry point for the Archery Target application.
 * Sets up the primary stage with an ArcheryTargetView.
 */
public class ArcheryTargetApp extends Application {

    private static final int WINDOW_WIDTH = 400;
    private static final int WINDOW_HEIGHT = 400;

    /**
     * Creates the ArcheryTargetView, the scene is set up, and primary stage
     * configured.
     *
     * @param primaryStage the primary stage for this application, where
     *                     the application scene can be set.
     */
    @Override
    public void start(Stage primaryStage) {
        ArcheryTargetView targetView = new ArcheryTargetView(WINDOW_WIDTH, WINDOW_HEIGHT);

        Scene scene = new Scene(targetView, WINDOW_WIDTH, WINDOW_HEIGHT);

        primaryStage.setTitle("Archery Target");
        primaryStage.setScene(scene);
        primaryStage.setResizable(false);
        primaryStage.show();
    }

    /**
     * Entry point of the Java application.
     * Where the JavaFX application is launched.
     *
     * @param args command line arguments
     */
    public static void main(String[] args) {
        launch(args);
    }
}