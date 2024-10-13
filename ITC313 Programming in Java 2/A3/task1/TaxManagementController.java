package task1;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;

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

    public void initialize() {
        taxManagementService = new TaxManagementService();
        taxCalculator = new TaxCalculator();
    }

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

    private void showAlert(String title, String message) {
        Alert alert = new Alert(AlertType.WARNING);
        alert.setTitle(title);
        alert.setHeaderText(null);
        alert.setContentText(message);
        alert.showAndWait();
    }
}