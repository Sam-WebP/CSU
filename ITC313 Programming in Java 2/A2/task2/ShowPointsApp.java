package task2;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.ContextMenu;
import javafx.scene.control.MenuItem;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseButton;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;
import javafx.scene.text.Font;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

/**
 * JavaFX application that displays points from a file, allows interaction with
 * them,
 * and saves their updated positions upon exit.
 */
public class ShowPointsApp extends Application {

    private final List<Point> points = new ArrayList<>();
    private final List<PointCircle> circles = new ArrayList<>();
    private Pane pane;
    private TextField coordinateField;
    private String pointsFileName;
    private static final double POINT_RADIUS = 5.0;

    @Override
    public void start(Stage primaryStage) {
        // Get the points file name from command line arguments
        List<String> args = getParameters().getRaw();
        if (args.isEmpty()) {
            System.err.println("Error: No points file specified.");
            System.exit(1);
        }
        pointsFileName = args.get(0);
        readPointsFromFile(pointsFileName);

        // Set up the UI components
        pane = new Pane();
        coordinateField = new TextField();
        coordinateField.setEditable(false);
        coordinateField.setFont(Font.font("Arial", 12));

        BorderPane root = new BorderPane();
        root.setCenter(pane);
        root.setBottom(coordinateField);

        Scene scene = new Scene(root, 800, 600);
        primaryStage.setScene(scene);
        primaryStage.setTitle("Show Points");
        primaryStage.show();

        drawPoints();

        // Set up the window close request handler to save points
        primaryStage.setOnCloseRequest(event -> savePointsToFile(pointsFileName));
    }

    /**
     * Reads points from the specified file.
     *
     * @param fileName The name of the points file.
     */
    private void readPointsFromFile(String fileName) {
        try (BufferedReader br = new BufferedReader(new FileReader(fileName))) {
            String line;
            points.clear();

            while ((line = br.readLine()) != null) {
                line = line.trim();
                if (!line.isEmpty()) {
                    String[] parts = line.split(";");
                    Float x = null;
                    Float y = null;
                    for (String part : parts) {
                        part = part.trim();
                        if (part.startsWith("x=")) {
                            x = Float.parseFloat(part.substring(2).trim());
                        } else if (part.startsWith("y=")) {
                            y = Float.parseFloat(part.substring(2).trim());
                        }
                    }
                    if (x != null && y != null) {
                        points.add(new Point(x, y));
                    } else {
                        System.err.println("Warning: Invalid line format: " + line);
                    }
                }
            }
        } catch (IOException | NumberFormatException e) {
            System.err.println("Error reading points from file: " + e.getMessage());
            System.exit(1);
        }
    }

    /**
     * Draws the points as circles on the pane.
     */
    private void drawPoints() {
        pane.getChildren().clear();
        circles.clear();

        for (Point point : points) {
            PointCircle circle = new PointCircle(point);
            addEventHandlers(circle);
            pane.getChildren().add(circle);
            circles.add(circle);
        }
    }

    /**
     * Adds event handlers to the circle for interactivity.
     *
     * @param circle The circle to add event handlers to.
     */
    private void addEventHandlers(PointCircle circle) {
        // Handle mouse hover to display coordinates
        circle.setOnMouseEntered(event -> {
            Point point = circle.getPoint();
            coordinateField.setText(String.format("x=%.2f; y=%.2f", point.getX(), point.getY()));
        });

        circle.setOnMouseExited(event -> coordinateField.clear());

        // Handle drag and drop
        final Delta dragDelta = new Delta();
        circle.setOnMousePressed(event -> {
            if (event.isPrimaryButtonDown()) {
                dragDelta.x = circle.getCenterX() - event.getX();
                dragDelta.y = circle.getCenterY() - event.getY();
                circle.setCursor(javafx.scene.Cursor.MOVE);
            }
        });

        circle.setOnMouseDragged(event -> {
            if (event.isPrimaryButtonDown()) {
                circle.setCenterX(event.getX() + dragDelta.x);
                circle.setCenterY(event.getY() + dragDelta.y);
            }
        });

        circle.setOnMouseReleased(event -> {
            if (event.getButton() == MouseButton.PRIMARY) {
                circle.setCursor(javafx.scene.Cursor.HAND);
                Point point = circle.getPoint();
                coordinateField.setText(String.format("x=%.2f; y=%.2f", point.getX(), point.getY()));
            }
        });

        // Handle right-click context menu for deletion
        circle.setOnContextMenuRequested(event -> {
            ContextMenu contextMenu = new ContextMenu();
            MenuItem deleteItem = new MenuItem("Delete");
            deleteItem.setOnAction(e -> {
                pane.getChildren().remove(circle);
                circles.remove(circle);
                points.remove(circle.getPoint());
            });
            contextMenu.getItems().add(deleteItem);
            contextMenu.show(circle, event.getScreenX(), event.getScreenY());
        });

        // Set the cursor to hand when over the circle
        circle.setCursor(javafx.scene.Cursor.HAND);
    }

    /**
     * Saves the current points to the specified file.
     *
     * @param fileName The name of the file to save the points to.
     */
    private void savePointsToFile(String fileName) {
        try (BufferedWriter bw = new BufferedWriter(new FileWriter(fileName))) {
            for (Point point : points) {
                bw.write(String.format("x=%.2f; y=%.2f%n", point.getX(), point.getY()));
            }
        } catch (IOException e) {
            System.err.println("Error saving points to file: " + e.getMessage());
        }
    }

    /**
     * Launches the JavaFX application.
     *
     * @param args Command line arguments (should include the points file path).
     */
    public static void main(String[] args) {
        launch(args);
    }

    /**
     * Helper class to store delta values for dragging.
     */
    private static class Delta {
        double x, y;
    }
}