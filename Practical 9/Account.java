public class Account {
    private final String accountNumber;
    private String ownerName;
    private long balance;

    public Account(String accountNumber, String ownerName, long initialBalance) {
        this.accountNumber = accountNumber;
        this.ownerName = ownerName;
        this.balance = initialBalance;
    }

    public void unsyncDeposit(long amount) {
        long current = this.balance;
        try {

            Thread.sleep(0, 100);
        } catch (InterruptedException ignored) {}
        this.balance = current + amount;
    }

    public synchronized void deposit(long amount) {
        this.balance += amount;
    }

    public synchronized boolean withdraw(long amount) {
        if (amount > 0 && this.balance >= amount) {
            this.balance -= amount;
            return true;
        }
        return false;
    }

    public synchronized long getBalance() {
        return balance;
    }

    public void setBalance(long balance) {
        this.balance = balance;
    }

    public String getAccountNumber() {
        return accountNumber;
    }

    public String getOwnerName() {
        return ownerName;
    }

    @Override
    public String toString() {
        return accountNumber + " (" + ownerName + ") | Balance: Rs " + balance;
    }
}
