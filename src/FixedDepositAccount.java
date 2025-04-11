import java.time.LocalDate;

public class FixedDepositAccount extends Account {
    private final double depositAmount;
    private final double INTERESTRATE = 0.15;
    private boolean canWithdraw = false;
    private final LocalDate maturityDate;

    public FixedDepositAccount(String accountHolder, String accountNumber, LocalDate maturityDate, double depositAmount) {
        super(accountHolder, accountNumber);
        if (depositAmount <= 0) {
            throw new IllegalArgumentException("Deposit amount must be positive for Fixed Deposit.");
        }
        if (maturityDate.isBefore(LocalDate.now().plusDays(1))) {
            throw new IllegalArgumentException("Maturity date must be in the future.");
        }
        this.maturityDate = maturityDate;
        this.depositAmount = depositAmount;
        addTransaction(new Transaction("Deposit", depositAmount, maturityDate));
        System.out.println("Fixed Deposit Account created with amount: GHS " + depositAmount + ", maturity date: " + maturityDate);
    }

    @Override
    public void deposit(double amount) {
        System.out.println("Cannot deposit additional funds into a Fixed Deposit account.");
    }

    //Method to implement withdrawal functionality
    @Override
    public void withdraw(double amount) {
        if (LocalDate.now().isAfter(this.maturityDate) || LocalDate.now().isEqual(this.maturityDate)) {
            if (amount > 0 && amount <= this.balance) {
                this.balance -= amount;
                addTransaction(new Transaction("Withdrawal", amount, "SUCCESS"));
                System.out.println("Withdrawal of GHS " + amount + " has been made successfully.");
                System.out.println("You balance is: GHS" + getBalance());
            } else if (amount < 0) {
                addTransaction(new Transaction("Withdrawal", amount, "FAILED"));
                System.out.println("Invalid withdrawal amount.");
            } else {
                addTransaction(new Transaction("Withdrawal", amount, "FAILED"));
                System.out.println("Insufficient amount to withdraw.");
            }
        } else {
            System.out.println("Cannot withdraw from account because account is not fully matured. Maturity date: " + this.maturityDate);
        }
    }

    public double calculateInterestRate() {
        if (LocalDate.now().isAfter(maturityDate) || LocalDate.now().isEqual(maturityDate)) {
            double interest = this.depositAmount * INTERESTRATE;
            this.balance += interest;
            addTransaction(new Transaction("Interest earned", interest));
            System.out.println("Interest of GHS " + String.format("%.2f", interest) + " added to Fixed Deposit. New balance: GHS " + String.format("%.2f", this.balance));
            return interest;
        } else {
            System.out.println("Interest will be calculated and added upon maturity on " + maturityDate.format(java.time.format.DateTimeFormatter.ISO_DATE) + ".");
            return 0.0;
        }
    }

    public LocalDate getMaturityDate() {
        return maturityDate;
    }
}
