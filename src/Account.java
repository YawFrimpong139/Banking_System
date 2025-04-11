import java.util.LinkedList;
import java.util.List;

public abstract class Account implements BankAccount {
    protected String accountHolder;
    protected String accountNumber;
    protected double balance;
    protected LinkedList<Transaction> transactionHistory;

    public Account(String accountHolder, String accountNumber) {
        this(accountHolder, accountNumber, 0.0);
    }

    public Account(String accountHolder, String accountNumber, double initialBalance) {
        if (accountNumber == null || accountNumber.trim().isEmpty() || accountHolder == null || accountHolder.trim().isEmpty()) {
            throw new IllegalArgumentException("Account Number and account holder cannot be empty");
        } else if (accountNumber.length() != 10) {
            throw new IllegalArgumentException("Account Number must be exactly 10 characters");
        } else {
            this.accountHolder = accountHolder;
            this.accountNumber = accountNumber;
            this.balance = initialBalance;
            this.transactionHistory = new LinkedList<>();
            System.out.println("Creating account for: " + accountHolder + " (Account #" + accountNumber + ")");
        }
    }

    //Method to implement deposit functionality
    @Override
    public void deposit(double amount) {
        if (amount > 0) {
            this.balance += amount;
            addTransaction(new Transaction("Deposit", amount, "SUCCESS"));
            System.out.println("Deposit of GHS " + amount + " has been successfully made. Credit balance: " + this.balance);
        } else {
            System.out.println("Deposit of GHS " + amount + " has not been successful. Credit balance: " + balance);
            addTransaction(new Transaction("Deposit", amount, "FAILED"));
        }
    }

    //Method to implement withdrawal functionality
    @Override
    public void withdraw(double amount) {
        if (amount > 0) {
            if (this.balance >= amount) {
                this.balance -= amount;
                addTransaction(new Transaction("Withdrawal", amount, "SUCCESS"));
                System.out.println("Withdrawal of GHS " + amount + " has been successfully made. Current balance: " + this.balance);
            } else {
                System.out.println("Insufficient funds. Balance: " + this.balance);
                addTransaction(new Transaction("Withdrawal", amount, "FAILED"));
            }
        } else {
            System.out.println("Invalid Withdrawal amount");
        }
    }

    @Override
    public double getBalance() {
        return balance;
    }

    @Override
    public List<Transaction> getTransactionHistory(int n) {
        int size = transactionHistory.size();
        int start = Math.max(0, size - n);
        return new LinkedList<>(transactionHistory.subList(start, size));
    }

    protected void addTransaction(Transaction transaction) {
        transactionHistory.add(transaction);
    }

    @Override
    public String getAccountHolderName() {
        return accountHolder;
    }

    @Override
    public String getAccountNumber() {
        return accountNumber;
    }
}
