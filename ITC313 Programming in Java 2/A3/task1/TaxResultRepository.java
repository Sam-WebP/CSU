package task1;

public class TaxResultRepository {
    private DataManager dataManager;

    public TaxResultRepository() {
        dataManager = new DataManager();
    }

    public void saveTaxResult(TaxResult taxResult) {
        dataManager.saveTaxResult(taxResult);
    }

    public TaxResult getTaxResultByID(String id) {
        return dataManager.getTaxResultByID(id);
    }

    public void updateTaxResult(TaxResult taxResult) {
        dataManager.updateTaxResult(taxResult);
    }

    public void deleteTaxResult(String id) {
        dataManager.deleteTaxResult(id);
    }
}