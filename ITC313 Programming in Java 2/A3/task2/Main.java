package task2;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class Main extends Application {

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

    public static void main(String[] args) {
        launch(args);
    }
}