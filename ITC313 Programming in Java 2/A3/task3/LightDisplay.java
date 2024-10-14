package task3;

import javafx.application.Application;
import javafx.application.Platform;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.*;
import javafx.stage.Stage;

import java.util.ArrayList;
import java.util.List;

/**
 * An application that displays flashing lights.
 * The user can control the lights with Start, Stop, Pause, and Resume buttons.
 */
public class LightDisplay extends Application {

    private List<FlashingLight> flashingLights = new ArrayList<>();

    // UI Components
    private Button startButton;
    private Button stopButton;
    private Button pauseButton;
    private Button resumeButton;

    private MenuBar menuBar;
    private Menu fileMenu;
    private Menu helpMenu;
    private MenuItem exitMenuItem;
    private MenuItem aboutMenuItem;

    /**
     * Starts the JavaFX application.
     *
     * @param primaryStage the primary stage for this application, onto which
     *                     the application scene is set.
     */
    @Override
    public void start(Stage primaryStage) {
        initializeUI();

        BorderPane mainLayout = createMainLayout();
        Scene scene = new Scene(mainLayout, 400, 300);

        primaryStage.setTitle("Light Display Application");
        primaryStage.setScene(scene);
        primaryStage.show();

        // Ensure all threads are stopped when the application is closed
        primaryStage.setOnCloseRequest(event -> {
            stopAllFlashingLights();
            Platform.exit();
            System.exit(0);
        });
    }

    /**
     * Initialises all UI components.
     */
    private void initializeUI() {
        createMenuBar();
        createButtons();
        createFlashingLights();
    }

    /**
     * Creates the MenuBar with File and Help menus.
     */
    private void createMenuBar() {
        menuBar = new MenuBar();

        // File Menu
        fileMenu = new Menu("File");
        exitMenuItem = new MenuItem("Exit");
        exitMenuItem.setOnAction(e -> {
            stopAllFlashingLights();
            Platform.exit();
            System.exit(0);
        });
        fileMenu.getItems().add(exitMenuItem);

        // Help Menu
        helpMenu = new Menu("Help");
        aboutMenuItem = new MenuItem("About");
        aboutMenuItem.setOnAction(e -> showAboutDialog());
        helpMenu.getItems().add(aboutMenuItem);

        menuBar.getMenus().addAll(fileMenu, helpMenu);
    }

    /**
     * Creates the control buttons and sets their event handlers.
     */
    private void createButtons() {
        startButton = new Button("Start");
        stopButton = new Button("Stop");
        pauseButton = new Button("Pause");
        resumeButton = new Button("Resume");

        startButton.setOnAction(e -> handleStartButtonAction());
        stopButton.setOnAction(e -> handleStopButtonAction());
        pauseButton.setOnAction(e -> handlePauseButtonAction());
        resumeButton.setOnAction(e -> handleResumeButtonAction());

        // Initial button states
        startButton.setDisable(false);
        stopButton.setDisable(true);
        pauseButton.setDisable(true);
        resumeButton.setDisable(true);
    }

    /**
     * Creates six instances of FlashingLight and adds them to the list.
     */
    private void createFlashingLights() {
        for (int i = 0; i < 6; i++) {
            FlashingLight light = new FlashingLight(10);
            flashingLights.add(light);
        }
    }

    /**
     * Arranges the UI components in the main layout.
     *
     * @return the main layout pane
     */
    private BorderPane createMainLayout() {
        BorderPane root = new BorderPane();

        // Top: MenuBar
        root.setTop(menuBar);

        // Center: FlashingLights arranged in a grid
        GridPane lightsGrid = new GridPane();
        lightsGrid.setHgap(5);
        lightsGrid.setVgap(5);
        lightsGrid.setPadding(new Insets(2));
        lightsGrid.setAlignment(Pos.CENTER);

        int columns = 6;
        int rows = 1;
        for (int i = 0; i < flashingLights.size(); i++) {
            int row = i / columns;
            int col = i % columns;
            lightsGrid.add(flashingLights.get(i), col, row);
        }

        root.setCenter(lightsGrid);

        // Bottom: Control Buttons
        HBox buttonBox = new HBox(5);
        buttonBox.setPadding(new Insets(5));
        buttonBox.setAlignment(Pos.CENTER);
        buttonBox.getChildren().addAll(startButton, stopButton, pauseButton, resumeButton);

        root.setBottom(buttonBox);

        return root;
    }

    /**
     * Handles the Start button action.
     */
    private void handleStartButtonAction() {
        flashingLights.forEach(FlashingLight::startFlashing);
        updateButtonStatesOnStart();
    }

    /**
     * Handles the Stop button action.
     */
    private void handleStopButtonAction() {
        stopAllFlashingLights();
        updateButtonStatesOnStop();
    }

    /**
     * Handles the Pause button action.
     */
    private void handlePauseButtonAction() {
        flashingLights.forEach(FlashingLight::pauseFlashing);
        updateButtonStatesOnPause();
    }

    /**
     * Handles the Resume button action.
     */
    private void handleResumeButtonAction() {
        flashingLights.forEach(FlashingLight::resumeFlashing);
        updateButtonStatesOnResume();
    }

    /**
     * Stops all flashing lights.
     */
    private void stopAllFlashingLights() {
        flashingLights.forEach(FlashingLight::stopFlashing);
    }

    /**
     * Updates the button states when Start is pressed.
     */
    private void updateButtonStatesOnStart() {
        startButton.setDisable(true);
        stopButton.setDisable(false);
        pauseButton.setDisable(false);
        resumeButton.setDisable(true);
    }

    /**
     * Updates the button states when Stop is pressed.
     */
    private void updateButtonStatesOnStop() {
        startButton.setDisable(false);
        stopButton.setDisable(true);
        pauseButton.setDisable(true);
        resumeButton.setDisable(true);
    }

    /**
     * Updates the button states when Pause is pressed.
     */
    private void updateButtonStatesOnPause() {
        startButton.setDisable(true);
        stopButton.setDisable(false);
        pauseButton.setDisable(true);
        resumeButton.setDisable(false);
    }

    /**
     * Updates the button states when Resume is pressed.
     */
    private void updateButtonStatesOnResume() {
        startButton.setDisable(true);
        stopButton.setDisable(false);
        pauseButton.setDisable(false);
        resumeButton.setDisable(true);
    }

    /**
     * Displays the About dialog with program information.
     */
    private void showAboutDialog() {
        Alert aboutAlert = new Alert(Alert.AlertType.INFORMATION);
        aboutAlert.setTitle("About Light Display");
        aboutAlert.setHeaderText("Light Display Application");
        aboutAlert.setContentText(
                "Version 1.0\n\nThis application displays six flashing lights.\n\nDeveloped by Samuel Moran.");
        aboutAlert.showAndWait();
    }
}
