package task1;

/**
 * Tax rate bracket with a minimum income, maximum income, base
 * tax, and tax rate.
 */
public class TaxRate {
    private double minIncome;
    private double maxIncome;
    private double baseTax;
    private double rate;

    /**
     * Constructs a TaxRate object.
     * 
     * @param minIncome The minimum income for this tax bracket.
     * @param maxIncome The maximum income for this tax bracket.
     * @param baseTax   The base tax amount for this bracket.
     * @param rate      The tax rate for this bracket.
     */
    public TaxRate(double minIncome, double maxIncome, double baseTax, double rate) {
        this.minIncome = minIncome;
        this.maxIncome = maxIncome;
        this.baseTax = baseTax;
        this.rate = rate;
    }

    /**
     * Returns the minimum income for this tax bracket.
     * 
     * @return The minimum income.
     */
    public double getMinIncome() {
        return minIncome;
    }

    /**
     * Returns the maximum income for this tax bracket.
     * 
     * @return The maximum income.
     */
    public double getMaxIncome() {
        return maxIncome;
    }

    /**
     * Returns the base tax for this bracket.
     * 
     * @return The base tax.
     */
    public double getBaseTax() {
        return baseTax;
    }

    /**
     * Returns the tax rate for this bracket.
     * 
     * @return The tax rate.
     */
    public double getRate() {
        return rate;
    }

    /**
     * Returns a string representation of the TaxRate object.
     * 
     * @return A string representation of the TaxRate object.
     */
    @Override
    public String toString() {
        return "TaxRate{" +
                "minIncome=" + minIncome +
                ", maxIncome=" + (maxIncome == Double.MAX_VALUE ? "and over" : maxIncome) +
                ", baseTax=" + baseTax +
                ", rate=" + rate +
                '}';
    }
}