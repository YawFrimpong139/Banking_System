import java.util.List;

public class SavingsAccount extends Account {
    private final double MINIMUMBALANCE = 50.00;

    public SavingsAccount(String accountHolder, String accountNumber) {
        super(accountHolder, accountNumber);
    }

    //Method to implement withdrawal functionality
    @Override
    public void withdraw(double amount) {
        if (amount > 0) {
            if (this.balance - amount >= MINIMUMBALANCE) {
                this.balance -= amount;
                addTransaction(new Transaction("Withdrawal", amount, "SUCCESS"));
                System.out.println("Withdrawal of GHS " + amount + " has been successful");
                System.out.println("You balance is: GHS" + getBalance());
            } else {
                addTransaction(new Transaction("Withdrawal", amount, "FAILED"));
                System.out.println("Insufficient Amount. Minimum balance of GHS " + MINIMUMBALANCE + " must be maintained.");
            }
        } else {
            System.out.println("Invalid Withdrawal amount");
        }
    }
}
