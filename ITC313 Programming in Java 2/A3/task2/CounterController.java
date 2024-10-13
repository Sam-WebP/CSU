package task2;

import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.scene.control.*;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.geometry.Insets;
import javafx.geometry.Pos;

import java.util.Optional;

public class CounterController {
    private IntCounter intCounter;
    private TextCounter textCounter;
    private CounterTask counterTask;
    private Thread counterThread;
    private CounterState state = CounterState.STOPPED;
    private long delay = 1000; // Default delay in milliseconds

    // UI Components
    private Button startButton;
    private Button pauseButton;
    private Button resumeButton;
    private MenuItem delayMenuItem;
    private MenuItem exitMenuItem;

    private BorderPane root;

    public CounterController() {
        intCounter = new IntCounter();
        textCounter = new TextCounter(intCounter);
        initializeUI();
    }

    private void initializeUI() {
        root = new BorderPane();

        // MenuBar
        MenuBar menuBar = new MenuBar();
        Menu fileMenu = new Menu("File");
        delayMenuItem = new MenuItem("Delay");
        exitMenuItem = new MenuItem("Exit");
        fileMenu.getItems().addAll(delayMenuItem, exitMenuItem);
        menuBar.getMenus().add(fileMenu);
        root.setTop(menuBar);

        // Center (TextCounter)
        BorderPane.setAlignment(textCounter, Pos.CENTER);
        root.setCenter(textCounter);

        // Bottom (Buttons)
        HBox buttonBox = new HBox(10);
        buttonBox.setPadding(new Insets(15));
        buttonBox.setAlignment(Pos.CENTER);

        startButton = new Button("Start");
        pauseButton = new Button("Pause");
        resumeButton = new Button("Resume");

        buttonBox.getChildren().addAll(startButton, pauseButton, resumeButton);
        root.setBottom(buttonBox);

        // Initialize Button States
        updateButtonStates();

        // Event Handlers
        startButton.setOnAction(this::handleStart);
        pauseButton.setOnAction(this::handlePause);
        resumeButton.setOnAction(this::handleResume);
        delayMenuItem.setOnAction(this::handleDelay);
        exitMenuItem.setOnAction(this::handleExit);
    }

    public BorderPane getView() {
        return root;
    }

    // Event Handlers
    private void handleStart(ActionEvent event) {
        if (state == CounterState.STOPPED) {
            intCounter.setCount(0);
            counterTask = new CounterTask(intCounter, delay);
            counterThread = new Thread(counterTask);
            counterThread.setDaemon(true); // Ensures the thread exits when the application closes
            counterThread.start();
            state = CounterState.RUNNING;
            updateButtonStates();
        }
    }

    private void handlePause(ActionEvent event) {
        if (state == CounterState.RUNNING) {
            counterTask.pause();
            state = CounterState.PAUSED;
            updateButtonStates();
        }
    }

    private void handleResume(ActionEvent event) {
        if (state == CounterState.PAUSED) {
            counterTask.resumeTask();
            state = CounterState.RUNNING;
            updateButtonStates();
        }
    }

    private void handleDelay(ActionEvent event) {
        boolean wasRunning = (state == CounterState.RUNNING);
        if (wasRunning) {
            handlePause(event);
        }

        TextInputDialog dialog = new TextInputDialog(Long.toString(delay));
        dialog.setTitle("Set Delay");
        dialog.setHeaderText("Set the new delay (in milliseconds):");
        dialog.setContentText("Delay:");

        Optional<String> result = dialog.showAndWait();
        result.ifPresent(input -> {
            try {
                long newDelay = Long.parseLong(input);
                if (newDelay <= 0) {
                    throw new NumberFormatException("Delay must be positive.");
                }
                delay = newDelay;
                if (counterTask != null) {
                    counterTask.setDelay(newDelay);
                }
            } catch (NumberFormatException e) {
                showAlert("Invalid Input", "Please enter a positive integer for the delay.");
            }
        });

        if (wasRunning) {
            handleResume(event);
        }
    }

    public void handleExit(ActionEvent event) {
        if (counterTask != null) {
            counterTask.stop();
        }

        // Wait for the thread to finish gracefully
        if (counterThread != null && counterThread.isAlive()) {
            try {
                counterThread.join();
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            }
        }

        Platform.exit();
    }

    // Helper Methods
    private void updateButtonStates() {
        switch (state) {
            case STOPPED:
                startButton.setDisable(false);
                pauseButton.setDisable(true);
                resumeButton.setDisable(true);
                break;
            case RUNNING:
                startButton.setDisable(true);
                pauseButton.setDisable(false);
                resumeButton.setDisable(true);
                break;
            case PAUSED:
                startButton.setDisable(true);
                pauseButton.setDisable(true);
                resumeButton.setDisable(false);
                break;
        }
    }

    private void showAlert(String title, String message) {
        Alert alert = new Alert(Alert.AlertType.ERROR, message, ButtonType.OK);
        alert.setHeaderText(null);
        alert.setTitle(title);
        alert.showAndWait();
    }
}
