package task1;

import java.util.List;

/**
 * Calculates income tax based on provided tax rates.
 */
public class TaxCalculator {
    private TaxRateRepository taxRateRepository;

    /**
     * Initialises the TaxCalculator with a default TaxRateRepository.
     */
    public TaxCalculator() {
        taxRateRepository = new TaxRateRepository();
    }

    /**
     * Calculates the income tax for a given taxable income.
     * 
     * @param taxableIncome The taxable income.
     * @return The calculated tax amount.
     */
    public double calculateTax(double taxableIncome) {
        List<TaxRate> taxRates = taxRateRepository.getTaxRates();
        double tax = 0.0;

        System.out.println("Calculating Tax for income: " + taxableIncome);

        for (TaxRate taxRate : taxRates) {
            System.out.println("Checking TaxRate: " + taxRate);

            if (taxableIncome > taxRate.getMinIncome()) {
                double adjustedMinIncome = taxRate.getMinIncome() - 1;
                double taxableAmount;
                if (taxableIncome <= taxRate.getMaxIncome()) {
                    taxableAmount = taxableIncome - adjustedMinIncome;
                } else {
                    taxableAmount = taxRate.getMaxIncome() - adjustedMinIncome;
                }

                double bracketTax = taxRate.getBaseTax() + (taxRate.getRate() * taxableAmount);

                System.out.println("Applied TaxRate: " + taxRate);
                System.out.println("Taxable Amount in this bracket: " + taxableAmount);
                System.out.println("Tax in this bracket: " + bracketTax);

                tax = bracketTax;

                if (taxableIncome <= taxRate.getMaxIncome()) {
                    break;
                }
            }
        }

        System.out.println("Total Calculated Tax: " + tax);
        return tax;
    }
}