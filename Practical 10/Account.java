public class Account {
    private final String accountNumber;
    private String ownerName;
    private long balance;

    public Account(String accountNumber, String ownerName, long balance) {
        this.accountNumber = accountNumber;
        this.ownerName = ownerName;
        this.balance = balance;
    }

    public synchronized void deposit(long amount) {
        if (amount > 0) {
            balance += amount;
        }
    }

    public synchronized boolean withdraw(long amount) {
        if (amount > 0 && balance >= amount) {
            balance -= amount;
            return true;
        }
        return false;
    }

    public void transferVulnerable(Account target, long amount) {
        synchronized (this) {
            System.out.println(Thread.currentThread().getName() + " locked source " + this.accountNumber);
            try { Thread.sleep(50); } catch (InterruptedException ignored) {}

            synchronized (target) {
                System.out.println(Thread.currentThread().getName() + " locked destination " + target.accountNumber);
                if (this.balance >= amount) {
                    this.balance -= amount;
                    target.balance += amount;
                    System.out.println(Thread.currentThread().getName() + " transferred Rs " + amount + " from " + this.accountNumber + " to " + target.accountNumber);
                }
            }
        }
    }

    public void transferSafe(Account target, long amount) {
        Account firstLock = this.accountNumber.compareTo(target.accountNumber) < 0 ? this : target;
        Account secondLock = (firstLock == this) ? target : this;

        synchronized (firstLock) {
            try { Thread.sleep(20); } catch (InterruptedException ignored) {}
            synchronized (secondLock) {
                if (this.balance >= amount) {
                    this.balance -= amount;
                    target.balance += amount;
                    System.out.println(Thread.currentThread().getName() + " [SAFE TRANSFER] Rs " + amount +
                            " from " + this.accountNumber + " to " + target.accountNumber);
                }
            }
        }
    }

    public String getAccountNumber() {
        return accountNumber;
    }

    public String getOwnerName() {
        return ownerName;
    }

    public synchronized long getBalance() {
        return balance;
    }

    @Override
    public String toString() {
        return accountNumber + " (" + ownerName + "): Rs " + balance;
    }
}
