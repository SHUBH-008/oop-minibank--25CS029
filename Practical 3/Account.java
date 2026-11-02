import java.util.Objects;

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

    public Account(String accountNumber, String ownerName, long balance) {
        this.accountNumber = accountNumber;
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

    @Override
    public String toString() {
        return accountNumber + " | " + ownerName + " | " + balance + " | Active: " + active;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Account account = (Account) o;
        return Objects.equals(accountNumber, account.accountNumber);
    }

    @Override
    public int hashCode() {
        return Objects.hash(accountNumber);
    }

    public String getFormattedStatement() {
        StringBuilder sb = new StringBuilder();
        sb.append("----------------------------------------\n");
        sb.append("       ACCOUNT STATEMENT\n");
        sb.append("----------------------------------------\n");
        sb.append("Account Number : ").append(accountNumber).append("\n");
        sb.append("Account Holder : ").append(ownerName).append("\n");
        sb.append("Status         : ").append(active ? "ACTIVE" : "INACTIVE").append("\n");
        sb.append("Current Balance: Rs ").append(balance).append("\n");
        sb.append("----------------------------------------\n");
        return sb.toString();
    }
}
