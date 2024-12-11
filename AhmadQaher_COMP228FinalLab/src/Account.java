public class Account {
    private double balance;
    private final int accountNumber;

    public Account(int accountNumber, double initialBalance) {
        this.accountNumber = accountNumber;
        this.balance = initialBalance;
    }

    public synchronized boolean withdraw(double amount) {
        if (amount <= balance) {
            try
            {
                Thread.sleep(100);
            }
            catch (InterruptedException e)
            {
                System.out.printf(e.getMessage());
            }
            balance -= amount;
            System.out.printf("Withdrawal of $%.2f was successful. New balance: $%.2f%n", amount, balance);
            return true;
        } else {
            System.out.printf("Insufficient funds for withdrawal of $%.2f. Current balance: $%.2f%n",
                    amount, balance);
            return false;
        }
    }

    public synchronized void deposit(double amount) {
        try
        {
            Thread.sleep(100);
        }
        catch (InterruptedException e)
        {
            System.out.printf(e.getMessage());
        }
        balance += amount;
        System.out.printf("Deposit of $%.2f successful. New balance: $%.2f%n", amount, balance);
    }

    public double getBalance() {
        return balance;
    }

    public int getAccountNumber() {
        return accountNumber;
    }
}
