package task1;

import java.util.List;

public class TaxCalculator {
    private TaxRateRepository taxRateRepository;

    public TaxCalculator() {
        taxRateRepository = new TaxRateRepository();
    }

    public double calculateTax(double taxableIncome) {
        List<TaxRate> taxRates = taxRateRepository.getTaxRates();
        double tax = 0.0;

        for (TaxRate taxRate : taxRates) {
            if (taxableIncome > taxRate.getMinIncome() && taxableIncome <= taxRate.getMaxIncome()) {
                tax = taxRate.getBaseTax() + (taxRate.getRate() * (taxableIncome - taxRate.getMinIncome()));
                break;
            }
        }

        return tax;
    }
}