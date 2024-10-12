package task1;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class DataManager {
    private static final String TAX_RATES_FILE = "task1/taxrates.txt";

    private DatabaseConnection databaseConnection;

    public DataManager() {
        databaseConnection = new DatabaseConnection();
    }

    public List<TaxRate> readTaxRatesFromFile() {
        List<TaxRate> taxRates = new ArrayList<>();

        try (BufferedReader reader = new BufferedReader(new FileReader(TAX_RATES_FILE))) {
            String line;
            int lineNumber = 0;
            // Skip header lines
            reader.readLine();
            reader.readLine();
            lineNumber += 2;

            while ((line = reader.readLine()) != null) {
                lineNumber++;
                try {
                    TaxRate taxRate = parseTaxRateLine(line, lineNumber);
                    if (taxRate != null) {
                        taxRates.add(taxRate);
                    }
                } catch (Exception e) {
                    System.out.println("Warning: Error parsing line " + lineNumber + ": " + line);
                    System.out.println("Error details: " + e.getMessage());
                }
            }
        } catch (IOException e) {
            System.out.println("Error: Unable to read tax rates from file.");
            e.printStackTrace();
        }

        if (taxRates.isEmpty()) {
            System.out.println("Warning: No valid tax rates were read from the file.");
        }

        return taxRates;
    }

    private TaxRate parseTaxRateLine(String line, int lineNumber) throws Exception {
        String[] parts = line.split("\\s{2,}"); // Split by two or more spaces
        if (parts.length < 2) {
            throw new Exception("Invalid format. Expected at least two parts separated by spaces.");
        }

        String incomeRange = parts[0].trim();
        String taxCalculation = parts[1].trim();

        double minIncome, maxIncome, baseTax = 0, rate;

        if (incomeRange.contains("-")) {
            String[] rangeParts = incomeRange.split("-");
            minIncome = parseAmount(rangeParts[0].trim());
            maxIncome = parseAmount(rangeParts[1].trim());
        } else if (incomeRange.contains("and over")) {
            String[] rangeParts = incomeRange.split("and over");
            minIncome = parseAmount(rangeParts[0].trim().replaceAll("[,$]", ""));
            maxIncome = Double.MAX_VALUE;
        } else {
            throw new Exception("Invalid income range format.");
        }

        if (taxCalculation.startsWith("$")) {
            String[] taxParts = taxCalculation.split("plus");
            baseTax = parseAmount(taxParts[0].trim());
            rate = parseRate(taxParts[1].trim());
        } else {
            rate = parseRate(taxCalculation.trim());
        }

        System.out.println("Parsed TaxRate: minIncome = " + minIncome +
                ", maxIncome = " + (maxIncome == Double.MAX_VALUE ? "and over" : maxIncome) +
                ", baseTax = " + baseTax +
                ", rate = " + rate);

        return new TaxRate(minIncome, maxIncome, baseTax, rate);
    }

    private double parseRate(String rateString) {
        if (rateString.contains("cents")) {
            return Double.parseDouble(rateString.split("cents")[0].trim()) / 100;
        } else {
            return Double.parseDouble(rateString.split("c")[0].trim()) / 100;
        }
    }

    private double parseAmount(String amount) {
        return Double.parseDouble(amount.replaceAll("[$,]", ""));
    }

    public void saveTaxResult(TaxResult taxResult) {
        String sql = "INSERT INTO TaxResult (ID, FinancialYear, TaxableIncome, Tax) VALUES (?, ?, ?, ?)";

        try (Connection connection = databaseConnection.getConnection();
                PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setString(1, taxResult.getId());
            statement.setString(2, taxResult.getFinancialYear());
            statement.setDouble(3, taxResult.getTaxableIncome());
            statement.setDouble(4, taxResult.getTax());
            statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public TaxResult getTaxResultByID(String id) {
        String sql = "SELECT * FROM TaxResult WHERE ID = ?";
        TaxResult taxResult = null;

        try (Connection connection = databaseConnection.getConnection();
                PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setString(1, id);
            ResultSet resultSet = statement.executeQuery();

            if (resultSet.next()) {
                String resultID = resultSet.getString("ID");
                String financialYear = resultSet.getString("FinancialYear");
                double taxableIncome = resultSet.getDouble("TaxableIncome");
                double tax = resultSet.getDouble("Tax");
                taxResult = new TaxResult(resultID, financialYear, taxableIncome, tax);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return taxResult;
    }

    public void updateTaxResult(TaxResult taxResult) {
        String sql = "UPDATE TaxResult SET FinancialYear = ?, TaxableIncome = ?, Tax = ? WHERE ID = ?";

        try (Connection connection = databaseConnection.getConnection();
                PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setString(1, taxResult.getFinancialYear());
            statement.setDouble(2, taxResult.getTaxableIncome());
            statement.setDouble(3, taxResult.getTax());
            statement.setString(4, taxResult.getId());
            statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void deleteTaxResult(String id) {
        String sql = "DELETE FROM TaxResult WHERE ID = ?";

        try (Connection connection = databaseConnection.getConnection();
                PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setString(1, id);
            statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}