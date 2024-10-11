package task1;

import java.util.List;

public class TaxRateRepository {
    private DataManager dataManager;

    public TaxRateRepository() {
        dataManager = new DataManager();
    }

    public List<TaxRate> getTaxRates() {
        return dataManager.readTaxRatesFromFile();
    }
}