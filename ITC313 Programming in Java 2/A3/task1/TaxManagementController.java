package task1;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;

/**
 * Controller class for managing tax calculations and database operations.
 */
public class TaxManagementController {
    @FXML
    private TextField idField;
    @FXML
    private TextField financialYearField;
    @FXML
    private TextField taxableIncomeField;
    @FXML
    private TextField taxField;
    @FXML
    private Button calculateButton;
    @FXML
    private Button searchButton;
    @FXML
    private Button updateButton;
    @FXML
    private Button deleteButton;

    private TaxManagementService taxManagementService;
    private TaxCalculator taxCalculator;

    /**
     * Initialises the controller, instantiating the service and calculator.
     */
    public void initialize() {
        taxManagementService = new TaxManagementService();
        taxCalculator = new TaxCalculator();
    }

    /**
     * Handles the calculate button click, calculating and saving tax results.
     * 
     * @param event The button click event.
     */
    @FXML
    private void handleCalculateButton(ActionEvent event) {
        String id = idField.getText();
        String financialYear = financialYearField.getText();
        String taxableIncomeText = taxableIncomeField.getText();

        if (id.isEmpty() || financialYear.isEmpty() || taxableIncomeText.isEmpty()) {
            showAlert("Warning", "ID, Financial Year, and Taxable Income cannot be empty.");
            return;
        }

        double taxableIncome;
        try {
            taxableIncome = Double.parseDouble(taxableIncomeText);
            if (taxableIncome <= 0) {
                showAlert("Warning", "Taxable Income must be greater than zero.");
                return;
            }
        } catch (NumberFormatException e) {
            showAlert("Warning", "Taxable Income must be a valid number.");
            return;
        }

        // Check if the ID already exists in the database
        TaxResult existingTaxResult = taxManagementService.getTaxResultByID(id);
        if (existingTaxResult != null) {
            showAlert("Warning", "A tax result already exists for the given ID.");
            return;
        }

        TaxResult taxResult = taxManagementService.calculateTax(id, financialYear, taxableIncome);
        taxField.setText(String.format("$%.2f", taxResult.getTax()));
    }

    /**
     * Handles the search button click, retrieving tax results by ID.
     * 
     * @param event The button click event.
     */
    @FXML
    private void handleSearchButton(ActionEvent event) {
        String id = idField.getText();

        if (id.isEmpty()) {
            showAlert("Warning", "ID cannot be empty.");
            return;
        }

        TaxResult taxResult = taxManagementService.getTaxResultByID(id);
        if (taxResult == null) {
            showAlert("Warning", "Tax result not found for the given ID.");
            return;
        }

        financialYearField.setText(taxResult.getFinancialYear());
        taxableIncomeField.setText(String.format("%.2f", taxResult.getTaxableIncome()));
        taxField.setText(String.format("$%.2f", taxResult.getTax()));
    }

    /**
     * Handles the update button click, updating existing tax results.
     * 
     * @param event The button click event.
     */
    @FXML
    private void handleUpdateButton(ActionEvent event) {
        String id = idField.getText();
        String financialYear = financialYearField.getText();
        double taxableIncome = Double.parseDouble(taxableIncomeField.getText());

        if (id.isEmpty() || financialYear.isEmpty() || taxableIncome <= 0) {
            showAlert("Warning", "ID, Financial Year, and Taxable Income cannot be empty or negative.");
            return;
        }

        TaxResult taxResult = taxManagementService.getTaxResultByID(id);
        if (taxResult == null) {
            showAlert("Warning", "Tax result not found for the given ID.");
            return;
        }

        taxResult.setFinancialYear(financialYear);
        taxResult.setTaxableIncome(taxableIncome);
        taxResult.setTax(taxCalculator.calculateTax(taxableIncome));
        taxManagementService.updateTaxResult(taxResult);
    }

    /**
     * Handles the delete button click, deleting tax results by ID.
     * 
     * @param event The button click event.
     */
    @FXML
    private void handleDeleteButton(ActionEvent event) {
        String id = idField.getText();

        if (id.isEmpty()) {
            showAlert("Warning", "ID cannot be empty.");
            return;
        }

        TaxResult taxResult = taxManagementService.getTaxResultByID(id);
        if (taxResult == null) {
            showAlert("Warning", "Tax result not found for the given ID.");
            return;
        }

        taxManagementService.deleteTaxResult(id);
    }

    /**
     * Displays an alert dialog with a warning message.
     * 
     * @param title   The title of the alert.
     * @param message The message to display.
     */
    private void showAlert(String title, String message) {
        Alert alert = new Alert(AlertType.WARNING);
        alert.setTitle(title);
        alert.setHeaderText(null);
        alert.setContentText(message);
        alert.showAndWait();
    }
}