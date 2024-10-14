package task1;

/**
 * Manages tax calculations and storage of tax results.
 */
public class TaxManagementService {
    private TaxResultRepository taxResultRepository;
    private TaxCalculator taxCalculator;

    /**
     * Initialises a new TaxManagementService with a TaxResultRepository and
     * TaxCalculator.
     */
    public TaxManagementService() {
        taxResultRepository = new TaxResultRepository();
        taxCalculator = new TaxCalculator();
    }

    /**
     * Calculates tax for a given taxable income and saves the result.
     * 
     * @param id            The ID of the tax payer.
     * @param financialYear The financial year.
     * @param taxableIncome The taxable income.
     * @return The calculated TaxResult.
     */
    public TaxResult calculateTax(String id, String financialYear, double taxableIncome) {
        double tax = taxCalculator.calculateTax(taxableIncome);
        TaxResult taxResult = new TaxResult(id, financialYear, taxableIncome, tax);
        taxResultRepository.saveTaxResult(taxResult);
        return taxResult;
    }

    /**
     * Retrieves a TaxResult by its ID.
     * 
     * @param id The ID of the tax result.
     * @return The TaxResult, or null if not found.
     */
    public TaxResult getTaxResultByID(String id) {
        return taxResultRepository.getTaxResultByID(id);
    }

    /**
     * Updates an existing TaxResult.
     * 
     * @param taxResult The TaxResult to update.
     */
    public void updateTaxResult(TaxResult taxResult) {
        taxResultRepository.updateTaxResult(taxResult);
    }

    /**
     * Deletes a TaxResult by its ID.
     * 
     * @param id The ID of the tax result to delete.
     */
    public void deleteTaxResult(String id) {
        taxResultRepository.deleteTaxResult(id);
    }
}