import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class Transaction {
    private final String type;
    private final double amount;
    private final String timeStamp;
    private String status;
    private final LocalDate maturityDate;

    public Transaction(String type, double amount) {
        this.type = type;
        this.amount = amount;
        this.timeStamp = LocalDate.now().toString();
        this.maturityDate = null; // Call the more general constructor
    }

    public Transaction(String type, double amount, String status) {
        this.type = type;
        this.amount = amount;
        this.timeStamp = LocalDate.now().toString();
        this.maturityDate = null;
        this.status = status;
    }

    public Transaction(String type, double amount, LocalDate maturityDate) {
        this.type = type;
        this.amount = amount;
        this.timeStamp = LocalDate.now().toString();
        this.maturityDate = maturityDate;
        this.status = "PENDING"; // Default status
    }

    public double getAmount() {
        return amount;
    }

    public String getTimeStamp() {
        return timeStamp;
    }

    public String getStatus() {
        return status;
    }

    public LocalDate getMaturityDate() {
        return maturityDate;
    }

    public String getType() {
        return type;
    }

    @Override
    public String toString() {
        String output = String.format("%s - GHS %.2f (%s)", type, amount, timeStamp);
        if (status != null) {
            output += " - Status: " + status;
        }
        if (maturityDate != null) {
            output += " - Maturity: " + maturityDate.format(DateTimeFormatter.ISO_DATE);
        }
        return output;
    }
}

