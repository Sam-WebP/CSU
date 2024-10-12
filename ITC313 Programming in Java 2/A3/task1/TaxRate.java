package task1;

public class TaxRate {
    private double minIncome;
    private double maxIncome;
    private double baseTax;
    private double rate;

    public TaxRate(double minIncome, double maxIncome, double baseTax, double rate) {
        this.minIncome = minIncome;
        this.maxIncome = maxIncome;
        this.baseTax = baseTax;
        this.rate = rate;
    }

    public double getMinIncome() {
        return minIncome;
    }

    public double getMaxIncome() {
        return maxIncome;
    }

    public double getBaseTax() {
        return baseTax;
    }

    public double getRate() {
        return rate;
    }

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