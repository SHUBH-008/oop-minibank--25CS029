public class MiniBank {
    public static void main(String[] args) {
        System.out.println("=== MiniBank - Lab 02: Accounts & Encapsulation ===\n");

        Customer c1 = new Customer("Riya Sharma", "riya@example.com", "9876543210");
        Customer c2 = new Customer("Aarav Patel", "aarav@example.com", "9123456780");
        Customer c3 = new Customer("Diya Mehta", "diya@example.com", "9988776655");

        System.out.println("Created Customer: " + c1.getCustomerId() + " (" + c1.getName() + ")");
        System.out.println("Created Customer: " + c2.getCustomerId() + " (" + c2.getName() + ")");
        System.out.println("Created Customer: " + c3.getCustomerId() + " (" + c3.getName() + ")\n");

        Account[] accounts = new Account[3];
        accounts[0] = new Account(c1.getName(), 5000);
        accounts[1] = new Account(c2.getName(), 10000);
        accounts[2] = new Account(c3.getName());

        System.out.println("Initial Account States:");
        for (Account acc : accounts) {
            System.out.println("Account: " + acc.getAccountNumber() + " | Owner: " + acc.getOwnerName() + " | Balance: " + acc.getBalance());
        }

        System.out.println("\n--- Performing Transactions on " + accounts[0].getAccountNumber() + " ---");
        System.out.println("Depositing 2000...");
        accounts[0].deposit(2000);
        System.out.println("Balance after deposit: " + accounts[0].getBalance());

        System.out.println("Withdrawing 3000...");
        boolean w1 = accounts[0].withdraw(3000);
        System.out.println("Withdrawal successful: " + w1 + " | Balance: " + accounts[0].getBalance());

        System.out.println("Attempting to withdraw 10000 (exceeding balance)...");
        boolean w2 = accounts[0].withdraw(10000);
        System.out.println("Withdrawal successful: " + w2 + " | Balance: " + accounts[0].getBalance());

        System.out.println("\n--- Testing Transfer Helper ---");
        accounts[1].transferTo(accounts[2], 2500);

        System.out.println("\n=== Final Balances ===");
        for (Account acc : accounts) {
            System.out.println(acc.getAccountNumber() + " (" + acc.getOwnerName() + "): Rs " + acc.getBalance());
        }
    }
}
