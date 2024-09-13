package task1;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.chart.PieChart;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import java.util.Arrays;
import java.util.List;

/**
 * JavaFX application that displays a pie chart that
 * shows sales data for the XYZ Supermarket chain.
 */
public class XYZSalesSummary extends Application {

    private static final int SCENE_WIDTH = 600;
    private static final int SCENE_HEIGHT = 400;
    private static final String CHART_TITLE = "Sales Summary - XYZ Supermarket Chain";
    private static final List<SalesData> SALES_DATA = Arrays.asList(
            new SalesData("ACT", 10),
            new SalesData("NSW", 20),
            new SalesData("VIC", 15),
            new SalesData("QLD", 15),
            new SalesData("SA", 15),
            new SalesData("WA", 15),
            new SalesData("NT", 5),
            new SalesData("TAS", 5));

    /**
     * Starts the JavaFX application.
     * 
     * @param primaryStage the primary stage of the application
     */
    @Override
    public void start(Stage primaryStage) {
        try {
            PieChart pieChart = createPieChart();
            VBox root = new VBox(pieChart);
            Scene scene = new Scene(root, SCENE_WIDTH, SCENE_HEIGHT);

            primaryStage.setTitle(CHART_TITLE);
            primaryStage.setScene(scene);
            primaryStage.show();
        } catch (Exception e) {
            System.err.println("Error starting application: " + e.getMessage());
        }
    }

    /**
     * Creates a pie chart representing the sales data.
     * 
     * @return the pie chart
     */
    private PieChart createPieChart() {
        PieChart pieChart = new PieChart();

        // Add data to the pie chart
        for (SalesData data : SALES_DATA) {
            pieChart.getData().add(new PieChart.Data(data.getState(), data.getPercentage()));
        }

        pieChart.setLabelsVisible(true);
        pieChart.setLegendVisible(true);

        return pieChart;
    }

    /**
     * Represents sales data for a state.
     */
    private static class SalesData {
        private final String state;
        private final double percentage;

        /**
         * Constructs a new SalesData object.
         *
         * @param state      the state
         * @param percentage the percentage of sales for the state
         */
        public SalesData(String state, double percentage) {
            this.state = state;
            this.percentage = percentage;
        }

        public String getState() {
            return state;
        }

        public double getPercentage() {
            return percentage;
        }
    }

    public static void main(String[] args) {
        launch(args);
    }
}
