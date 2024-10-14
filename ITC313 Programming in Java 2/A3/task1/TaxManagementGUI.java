package task1;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

/**
 * The main GUI application for Tax Management.
 * that launches and loads the FXML layout.
 */
public class TaxManagementGUI extends Application {

    /**
     * Starts the JavaFX application.
     * Loads the FXML file, sets the scene, and displays the primary stage.
     * 
     * @param primaryStage The main application stage.
     * @throws Exception If an error occurs during loading or initialisation.
     */
    @Override
    public void start(Stage primaryStage) throws Exception {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("tax_management.fxml"));
        Parent root = loader.load();
        TaxManagementController controller = loader.getController();

        Scene scene = new Scene(root);
        primaryStage.setScene(scene);
        primaryStage.setTitle("Tax Management Application");
        primaryStage.show();
    }

    /**
     * Main method to launch the application.
     * 
     * @param args Command line arguments (not used).
     */
    public static void main(String[] args) {
        launch(args);
    }
}