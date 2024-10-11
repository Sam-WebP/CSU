package task1;

public class TaxManagementService {
    private TaxResultRepository taxResultRepository;
    private TaxCalculator taxCalculator;

    public TaxManagementService() {
        taxResultRepository = new TaxResultRepository();
        taxCalculator = new TaxCalculator();
    }

    public TaxResult calculateTax(String id, String financialYear, double taxableIncome) {
        double tax = taxCalculator.calculateTax(taxableIncome);
        TaxResult taxResult = new TaxResult(id, financialYear, taxableIncome, tax);
        taxResultRepository.saveTaxResult(taxResult);
        return taxResult;
    }

    public TaxResult getTaxResultByID(String id) {
        return taxResultRepository.getTaxResultByID(id);
    }

    public void updateTaxResult(TaxResult taxResult) {
        taxResultRepository.updateTaxResult(taxResult);
    }

    public void deleteTaxResult(String id) {
        taxResultRepository.deleteTaxResult(id);
    }
}