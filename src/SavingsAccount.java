import java.util.List;

public class SavingsAccount extends Account {
    private final double MINIMUMBALANCE = 50.00;
    private final double interestRate = 0.025;

    public SavingsAccount(String accountHolder, String accountNumber) {
        super(accountHolder, accountNumber);
    }

    public double getInterestRate() {
        return interestRate;
    }

    public void calculateInterestRate() {
        double currentBalance = getBalance();
        if (currentBalance > MINIMUMBALANCE) {
            double interest = currentBalance * (interestRate / 12); // Assuming monthly interest calculation
            deposit(interest); // Use the deposit method to add interest
            System.out.println("Interest of GHS " + String.format("%.2f", interest) + " applied to Savings Account #" + getAccountNumber());
        } else {
            System.out.println("Interest not applied to Savings Account #" + getAccountNumber() + " as balance is below the minimum.");
        }
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
