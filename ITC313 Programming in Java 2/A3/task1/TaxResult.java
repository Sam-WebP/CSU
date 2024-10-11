package task1;

public class TaxResult {
    private String id;
    private String financialYear;
    private double taxableIncome;
    private double tax;

    public TaxResult(String id, String financialYear, double taxableIncome, double tax) {
        this.id = id;
        this.financialYear = financialYear;
        this.taxableIncome = taxableIncome;
        this.tax = tax;
    }

    public String getId() {
        return id;
    }

    public String getFinancialYear() {
        return financialYear;
    }

    public void setFinancialYear(String financialYear) {
        this.financialYear = financialYear;
    }

    public double getTaxableIncome() {
        return taxableIncome;
    }

    public void setTaxableIncome(double taxableIncome) {
        this.taxableIncome = taxableIncome;
    }

    public double getTax() {
        return tax;
    }

    public void setTax(double tax) {
        this.tax = tax;
    }
}