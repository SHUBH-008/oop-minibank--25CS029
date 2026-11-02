public class Account {
    private final String accountNumber;
    private String ownerName;
    private long balance;
    private boolean active;

    private static int accountCounter = 0;

    private static String generateAccountNumber() {
        return String.format("AC%04d", ++accountCounter);
    }

    public Account(String ownerName, long balance) {
        this.accountNumber = generateAccountNumber();
        this.ownerName = ownerName;
        this.balance = Math.max(0, balance);
        this.active = true;
    }

    public Account(String ownerName) {
        this(ownerName, 0);
    }

    public void deposit(long amount) {
        if (amount <= 0) {
            System.out.println("Deposit rejected: Amount must be positive.");
            return;
        }
        balance += amount;
    }

    public boolean withdraw(long amount) {
        if (amount <= 0) {
            System.out.println("Withdrawal rejected: Amount must be positive.");
            return false;
        }
        if (balance >= amount) {
            balance -= amount;
            return true;
        }
        return false;
    }

    public boolean transferTo(Account target, long amount) {
        if (target == null) {
            System.out.println("Transfer rejected: Target account cannot be null.");
            return false;
        }
        if (this.withdraw(amount)) {
            target.deposit(amount);
            System.out.println("Successfully transferred Rs " + amount + " to " + target.getAccountNumber());
            return true;
        } else {
            System.out.println("Transfer failed: Insufficient funds in " + this.accountNumber);
            return false;
        }
    }

    public String getAccountNumber() {
        return accountNumber;
    }

    public String getOwnerName() {
        return ownerName;
    }

    public long getBalance() {
        return balance;
    }

    public boolean isActive() {
        return active;
    }

    public void setActive(boolean active) {
        this.active = active;
    }
}
