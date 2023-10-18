package Exam;
import java.util.Date;

public class Account {

    private String accountNumber;
    private double balance;
    private double annualIntRate;
    private Date dateCreated;

    public Account() {
        this.accountNumber = "AC000";
        this.balance = 0;
        this.annualIntRate = 0;
        this.dateCreated = new Date();
    }

    public Account(String accountNumber, double balance) {
        this.accountNumber = accountNumber;
        this.balance = balance;
        this.annualIntRate = 0;
        this.dateCreated = new Date();
    }

    // Accessor methods (getters)
    public String getAccountNumber() {
        return accountNumber;
    }

    public double getBalance() {
        return balance;
    }

    public double getAnnualIntRate() {
        return annualIntRate;
    }

    public Date getDateCreated() {
        return dateCreated;
    }

    // Mutator methods (setters)
    public void setAccountNumber(String accountNumber) {
        this.accountNumber = accountNumber;
    }

    public void setBalance(double balance) {
        this.balance = balance;
    }

    public void setAnnualIntRate(double annualIntRate) {
        this.annualIntRate = annualIntRate;
    }

    public double getMonthlyIntRate() {
        return annualIntRate / 12;
    }

    public double getMonthlyInt() {
        return balance * getMonthlyIntRate();
    }

    public void withdraw(double amount) {
        if (amount > balance) {
            System.out.println("The amount your trying to withdraw is greater than your balance.");
        } else {
            balance -= amount;
        }
    }

    public void deposit(double amount) {
        if (amount < 0) {
            System.out.println("You can't deposit a negative amount.");
        } else {
            balance += amount;
        }
    }
}
