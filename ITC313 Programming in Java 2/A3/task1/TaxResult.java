package task1;

/**
 * Represents the result of a tax calculation.
 */
public class TaxResult {
    private String id;
    private String financialYear;
    private double taxableIncome;
    private double tax;

    /**
     * Constructs a TaxResult object.
     * 
     * @param id            The taxpayer's ID.
     * @param financialYear The financial year.
     * @param taxableIncome The taxable income.
     * @param tax           The calculated tax.
     */
    public TaxResult(String id, String financialYear, double taxableIncome, double tax) {
        this.id = id;
        this.financialYear = financialYear;
        this.taxableIncome = taxableIncome;
        this.tax = tax;
    }

    /**
     * Returns the taxpayer's ID.
     * 
     * @return The taxpayer's ID.
     */
    public String getId() {
        return id;
    }

    /**
     * Returns the financial year.
     * 
     * @return The financial year.
     */
    public String getFinancialYear() {
        return financialYear;
    }

    /**
     * Sets the financial year.
     * 
     * @param financialYear The financial year to set.
     */
    public void setFinancialYear(String financialYear) {
        this.financialYear = financialYear;
    }

    /**
     * Returns the taxable income.
     * 
     * @return The taxable income.
     */
    public double getTaxableIncome() {
        return taxableIncome;
    }

    /**
     * Sets the taxable income.
     * 
     * @param taxableIncome The taxable income to set.
     */
    public void setTaxableIncome(double taxableIncome) {
        this.taxableIncome = taxableIncome;
    }

    /**
     * Returns the calculated tax.
     * 
     * @return The calculated tax.
     */
    public double getTax() {
        return tax;
    }

    /**
     * Sets the calculated tax.
     * 
     * @param tax The tax to set.
     */
    public void setTax(double tax) {
        this.tax = tax;
    }
}