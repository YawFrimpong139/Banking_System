import java.time.LocalDate;
import java.util.List;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);
        Account currentAccount = null;

        System.out.println("Enter your account name: ");
        String myAccountName = input.nextLine().trim();
        System.out.println();

        System.out.println("Enter your account number (10 digits): ");
        String myAccountNumber = input.nextLine().trim();

        System.out.println("What is your Account type (Savings Account/Current Account/Fixed Deposit Account): ");
        String accountType = input.nextLine().trim().toLowerCase();

        switch (accountType) {
            case "savings account":
            case "savings":
                currentAccount = new SavingsAccount(myAccountName, myAccountNumber);
                System.out.println("How much are you depositing: ");
                double depoAmountSavings = getPositiveDoubleInput(input);
                currentAccount.deposit(depoAmountSavings);
                System.out.println("Savings Account Balance: GHS " + currentAccount.getBalance());
                break;
            case "current account":
            case "current":
                currentAccount = new CurrentAccount(myAccountName, myAccountNumber);
                System.out.println("How much are you depositing: ");
                double depoAmountCurrent = getPositiveDoubleInput(input);
                currentAccount.deposit(depoAmountCurrent);
                System.out.println("Current Account Balance: GHS " + currentAccount.getBalance());
                break;
            case "fixed deposit account":
            case "fixed deposit":
            case "fixed":
                System.out.println("How much are you depositing: ");
                double fixDepoAmount = getPositiveDoubleInput(input);

                System.out.println("Enter the maturity period in months: ");
                int maturityMonths = getPositiveIntInput(input);
                LocalDate maturityDate = LocalDate.now().plusMonths(maturityMonths);

                currentAccount = new FixedDepositAccount(myAccountName, myAccountNumber, maturityDate, fixDepoAmount);
                System.out.println("Fixed Deposit Account created. Maturity Date: " + ((FixedDepositAccount) currentAccount).getMaturityDate());
                break;
            default:
                System.out.println("Invalid account type entered: " + accountType);
                System.out.println("Please enter 'Savings Account', 'Current Account', or 'Fixed Deposit Account'.");
                break;
        }

        if (currentAccount != null) {
            handleAccountActions(currentAccount, input);
        }

        input.close();
    }

    private static void handleAccountActions(Account account, Scanner input) {
        boolean continueActions = true;
        while (continueActions) {
            System.out.println("\nWhat would you like to do with Account #" + account.getAccountNumber() + "?");
            System.out.println("1. Deposit");
            System.out.println("2. Withdraw");
            System.out.println("3. View Transaction History");
            System.out.println("4. Exit");
            System.out.print("Enter your choice: ");
            int choice = getIntInput(input);

            switch (choice) {
                case 1:
                    System.out.print("Enter deposit amount: ");
                    double depositAmount = getPositiveDoubleInput(input);
                    account.deposit(depositAmount);
                    break;
                case 2:
                    System.out.print("Enter withdrawal amount: ");
                    double withdrawAmount = getPositiveDoubleInput(input);
                    account.withdraw(withdrawAmount);
                    break;
                case 3:
                    viewTransactionHistory(account, input);
                    break;
                case 4:
                    continueActions = false;
                    System.out.println("Exiting account actions for Account #" + account.getAccountNumber() + ".");
                    break;
                default:
                    System.out.println("Invalid choice. Please try again.");
            }
        }
    }

    private static void viewTransactionHistory(Account account, Scanner input) {
        System.out.print("Enter the number of recent transactions to view (0 for all): ");
        int n = getIntInput(input);
        List<Transaction> history = account.getTransactionHistory(n == 0 ? Integer.MAX_VALUE : n);
        if (history.isEmpty()) {
            System.out.println("No transaction history available for Account #" + account.getAccountNumber() + ".");
        } else {
            System.out.println("\n--- Transaction History for Account #" + account.getAccountNumber() + " ---");
            for (Transaction transaction : history) {
                System.out.println(transaction);
            }
            System.out.println("--------------------------------------------------");
        }
    }

    // Helper method to get positive double input
    private static double getPositiveDoubleInput(Scanner scanner) {
        double amount;
        do {
            while (!scanner.hasNextDouble()) {
                System.out.println("Invalid input. Please enter a number.");
                scanner.next();
            }
            amount = scanner.nextDouble();
            if (amount <= 0) {
                System.out.println("Amount must be positive. Please enter again.");
            }
        } while (amount <= 0);
        scanner.nextLine();
        return amount;
    }

    // Helper method to get positive integer input
    private static int getPositiveIntInput(Scanner scanner) {
        int amount;
        do {
            while (!scanner.hasNextInt()) {
                System.out.println("Invalid input. Please enter a whole number.");
                scanner.next();
            }
            amount = scanner.nextInt();
            if (amount <= 0) {
                System.out.println("Amount must be positive. Please enter again.");
            }
        } while (amount <= 0);
        scanner.nextLine();
        return amount;
    }

    private static int getIntInput(Scanner scanner) {
        while (!scanner.hasNextInt()) {
            System.out.println("Invalid input. Please enter a whole number.");
            scanner.next();
        }
        return scanner.nextInt();
    }
}
