package task1;

/**
 * Repo for managing TaxResult objects.
 * Handles saving, retrieving, updating, and deleting tax results
 * through a DataManager instance.
 */
public class TaxResultRepository {
    private DataManager dataManager;

    /**
     * Constructs a TaxResultRepository and initialises the DataManager.
     */
    public TaxResultRepository() {
        dataManager = new DataManager();
    }

    /**
     * Saves a TaxResult.
     * 
     * @param taxResult The TaxResult to save.
     */
    public void saveTaxResult(TaxResult taxResult) {
        dataManager.saveTaxResult(taxResult);
    }

    /**
     * Retrieves a TaxResult by its ID.
     * 
     * @param id The ID of the TaxResult to retrieve.
     * @return The TaxResult with the given ID, or null if not found.
     */
    public TaxResult getTaxResultByID(String id) {
        return dataManager.getTaxResultByID(id);
    }

    /**
     * Updates an existing TaxResult.
     * 
     * @param taxResult The updated TaxResult.
     */
    public void updateTaxResult(TaxResult taxResult) {
        dataManager.updateTaxResult(taxResult);
    }

    /**
     * Deletes a TaxResult by its ID.
     * 
     * @param id The ID of the TaxResult to delete.
     */
    public void deleteTaxResult(String id) {
        dataManager.deleteTaxResult(id);
    }
}