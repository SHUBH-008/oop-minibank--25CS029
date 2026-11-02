public class MiniBank {
    public static void main(String[] args) {
        System.out.println("=== MiniBank - Lab 05: Abstract Classes & Polymorphism ===\n");

        Account[] accounts = {
            new SavingsAccount("Riya Sharma", 50000, 2000),
            new CurrentAccount("Apex Traders", 20000, 15000),
            new FixedDepositAccount("Deepak Verma", 100000, 24)
        };

        System.out.println("--- 1. Polymorphic Invocation of interestRate() ---");
        for (Account acc : accounts) {
            System.out.printf("Account: %s | Type: %-20s | Interest Rate: %.1f%%%n",
                    acc.getAccountNumber(), acc.getClass().getSimpleName(), acc.interestRate());
        }

        System.out.println("\n--- 2. Pattern Matching instanceof for Type-Specific Logic ---");
        for (Account acc : accounts) {
            if (acc instanceof SavingsAccount sa) {
                System.out.println("[SAVINGS] " + sa.getAccountNumber() +
                        " has minimum balance requirement of Rs " + sa.getMinBalance());
                System.out.printf("  Monthly Interest Earned: Rs %.2f%n", sa.monthlyInterest());
            } else if (acc instanceof CurrentAccount ca) {
                System.out.println("[CURRENT] " + ca.getAccountNumber() +
                        " allows overdraft up to Rs " + ca.getOverdraftLimit());
                System.out.println("  Testing withdrawal of Rs 30,000 (within overdraft): " + ca.withdraw(30000));
                System.out.println("  Balance after overdraft withdrawal: Rs " + ca.getBalance());
            } else if (acc instanceof FixedDepositAccount fda) {
                System.out.println("[FIXED DEPOSIT] " + fda.getAccountNumber() +
                        " is locked for " + fda.getTermMonths() + " months. canWithdraw(5000): " + fda.canWithdraw(5000));
                System.out.printf("  Monthly Interest Earned: Rs %.2f%n", fda.monthlyInterest());
            }
        }

        System.out.println("\n=== All Accounts Summary ===");
        for (Account acc : accounts) {
            System.out.println(acc);
        }
    }
}
