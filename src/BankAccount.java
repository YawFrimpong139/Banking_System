import java.util.List;

public interface BankAccount {
    void deposit(double amount);
    void withdraw(double amount);
    double getBalance();
    List<Transaction> getTransactionHistory(int n); // Consistent return type
    String getAccountNumber();
    String getAccountHolderName();
}

