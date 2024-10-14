package task2;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.stage.Stage;

/**
 * Initialises the JavaFX environment, creates the main window, sets up
 * the CounterController and handles the application's shutdown process.
 */
public class Main extends Application {

    /**
     * Called after the init method has returned and after the system
     * is ready for the application to begin running.
     *
     * @param primaryStage the primary stage for this application.
     */
    @Override
    public void start(Stage primaryStage) {
        CounterController controller = new CounterController();
        Scene scene = new Scene(controller.getView(), 400, 300);

        primaryStage.setTitle("JavaFX Counter Application");
        primaryStage.setScene(scene);
        primaryStage.show();

        // Ensure proper shutdown when the window is closed
        primaryStage.setOnCloseRequest(event -> {
            controller.handleExit(null);
        });
    }

    /**
     * The main entry point for application.
     * The launch method blocks until the application has exited.
     *
     * @param args the command line arguments passed to the application.
     */
    public static void main(String[] args) {
        launch(args);
    }
}