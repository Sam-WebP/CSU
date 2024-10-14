package task1;

import java.util.List;

/**
 * Repository for accessing tax rate information.
 */
public class TaxRateRepository {
    private DataManager dataManager;

    /**
     * Creates a new TaxRateRepository.
     */
    public TaxRateRepository() {
        dataManager = new DataManager();
    }

    /**
     * Retrieves a list of tax rates.
     * 
     * @return A list of TaxRate objects.
     */
    public List<TaxRate> getTaxRates() {
        return dataManager.readTaxRatesFromFile();
    }
}