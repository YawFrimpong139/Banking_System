import java.util.List;

public class CurrentAccount extends Account {
    private final double OVERDRAFTLIMIT = 1000.00;

    public CurrentAccount(String accountHolder, String accountNumber) {
        super(accountHolder, accountNumber);
    }

    //Method to implement withdrawal functionality
    @Override
    public void withdraw(double amount) {
        if (amount > 0) {
            if (balance - amount >= -OVERDRAFTLIMIT) {
                balance -= amount;
                addTransaction(new Transaction("Withdrawal", amount, "SUCCESS"));
                System.out.println("Withdrawal of GHS " + amount + " has been successful");
                System.out.println("You balance is: GHS " + getBalance());
            } else {
                addTransaction(new Transaction("Withdrawal", amount, "FAILED"));
                System.out.println("Withdrawal failed. Exceeded overdraft limit of GHS " + OVERDRAFTLIMIT + ".");
            }
        } else {
            System.out.println("Invalid Withdrawal amount");
        }
    }

    public void calculateInterestRate(){
        System.out.println("No interest to calculate");
    }

}
