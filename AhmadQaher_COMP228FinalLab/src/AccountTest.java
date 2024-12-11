import java.util.ArrayList;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

public class AccountTest {
    public static void main(String[] args) {
        Account account = new Account(1, 1000.00);
        ArrayList<Transaction> transactions = new ArrayList<>();
        transactions.add(new Transaction(account, "withdraw", 300.00));
        transactions.add(new Transaction(account, "deposit", 500.00));
        transactions.add(new Transaction(account, "withdraw", 400.00));
        transactions.add(new Transaction(account, "deposit", 200.00));
        transactions.add(new Transaction(account, "withdraw", 600.00));
        ExecutorService executorService = Executors.newFixedThreadPool(3);

        System.out.println("\nStarting balance: $" + account.getBalance());
        System.out.println("Transactions: \n");

        for (Transaction transaction : transactions) {
            executorService.execute(transaction);
        }
        executorService.shutdown();

        try {
            if (!executorService.awaitTermination(5, TimeUnit.SECONDS)) {
                System.out.println("Some tasks did not complete before timeout");
            }
        } catch (InterruptedException e) {
            System.err.println("Waiting for tasks to complete was interrupted. Error message: " + e.getMessage() );
        }

        System.out.println("\nFinal balance: $" + account.getBalance());
    }
}