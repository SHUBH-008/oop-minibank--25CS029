import exception.*;
import model.Account;

class BankSession implements AutoCloseable {
    private final String sessionId;

    public BankSession(String sessionId) {
        this.sessionId = sessionId;
        System.out.println("[BankSession] Secure banking session '" + sessionId + "' OPENED.");
    }

    public void logOperation(String op) {
        System.out.println("[BankSession " + sessionId + "] Logged operation: " + op);
    }

    @Override
    public void close() {
        System.out.println("[BankSession] Secure banking session '" + sessionId + "' CLOSED safely.");
    }
}

public class MiniBank {
    public static void main(String[] args) {
        System.out.println("=== MiniBank - Lab 08: Custom Exceptions & Fault Tolerance ===\n");

        Account acc1 = new Account("AC0001", "Riya Sharma", 1000);
        Account acc2 = new Account("AC0002", "Aarav Patel", 3000);

        System.out.println("Initial State: " + acc1);
        System.out.println("Initial State: " + acc2);

        System.out.println("\n--- 1. Testing Withdrawal with Insufficient Funds ---");
        try {
            System.out.println("Attempting to withdraw Rs 5000 from " + acc1.getAccountNumber() + " (Balance: Rs " + acc1.getBalance() + ")...");
            acc1.withdraw(5000);
        } catch (InsufficientFundsException e) {
            System.out.println("Withdrawal failed: short by " + e.getShortfall());
        } catch (InvalidAmountException e) {
            System.out.println("Withdrawal failed: " + e.getMessage());
        } finally {
            System.out.println("After attempt: " + acc1.getAccountNumber() + " balance remains Rs " + acc1.getBalance());
        }

        System.out.println("\n--- 2. Testing Invalid Amount Exception ---");
        try {
            System.out.println("Attempting to deposit Rs -500 into " + acc1.getAccountNumber() + "...");
            acc1.deposit(-500);
        } catch (InvalidAmountException e) {
            System.out.println("Transaction rejected: " + e.getMessage());
        }

        System.out.println("\n--- 3. Testing Transfer inside Try-With-Resources Session ---");
        try (BankSession session = new BankSession("SESS-8891")) {
            session.logOperation("Transfer from " + acc2.getAccountNumber() + " to " + acc1.getAccountNumber());
            acc2.transfer(acc1, 1500);
            session.logOperation("Transfer successful! Verified state.");
        } catch (BankException e) {
            System.out.println("Banking operation failed: " + e.getMessage());
        }

        System.out.println("\n=== Final Account Balances ===");
        System.out.println(acc1);
        System.out.println(acc2);
    }
}
