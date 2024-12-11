public class Transaction implements Runnable {
    private final Account account;
    private final String transactionType;
    private final double amount;

    public Transaction(Account account, String transactionType, double amount) {
        this.account = account;
        this.transactionType = transactionType;
        this.amount = amount;
    }

    @Override
    public void run() {
        if (transactionType.equalsIgnoreCase("withdraw")) {
            account.withdraw(amount);
        } else if (transactionType.equalsIgnoreCase("deposit")) {
            account.deposit(amount);
        }
    }
}