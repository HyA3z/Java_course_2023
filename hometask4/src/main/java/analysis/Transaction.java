package analysis;

public class Transaction {
    private final long transactionId;
    private final String date;
    private final double amount;
    private final String type;
    private final long userId;

    public Transaction(long transactionId, String date, double amount, String type, long userId) {
        this.transactionId = transactionId;
        this.date = date;
        this.amount = amount;
        this.type = type;
        this.userId = userId;
    }

    public long getTransactionId() {
        return transactionId;
    }

    public String getDate() {
        return date;
    }

    public double getAmount() {
        return amount;
    }

    public String getType() {
        return type;
    }

    public long getUserId() {
        return userId;
    }

    @Override
    public String toString() {
        return "Transaction{" +
                "transactionId=" + transactionId +
                ", date='" + date + '\'' +
                ", amount=" + amount +
                ", type='" + type + '\'' +
                ", userId=" + userId +
                '}';
    }
}
