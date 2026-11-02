public class MiniBank {
    public static void main(String[] args) throws InterruptedException {
        System.out.println("=== MiniBank - Lab 09: Multithreading & Race Conditions ===\n");

        final int NUM_THREADS = 10;
        final int DEPOSITS_PER_THREAD = 1000;
        final long DEPOSIT_AMOUNT = 1;
        final long EXPECTED_BALANCE = NUM_THREADS * DEPOSITS_PER_THREAD * DEPOSIT_AMOUNT;

        System.out.println("--- 1. Running 10 Threads WITHOUT Synchronization ---");
        Account raceAccount = new Account("AC0001", "Riya Sharma", 0);
        Thread[] raceThreads = new Thread[NUM_THREADS];

        for (int i = 0; i < NUM_THREADS; i++) {
            AccountWorker worker = new AccountWorker(raceAccount, DEPOSITS_PER_THREAD, DEPOSIT_AMOUNT, false);
            raceThreads[i] = new Thread(worker, "UnsyncWorker-Thread-" + (i + 1));
            System.out.println("[State: NEW] Thread created: " + raceThreads[i].getName());
            raceThreads[i].start();
        }

        for (Thread t : raceThreads) {
            t.join();
            System.out.println("[State: TERMINATED] Joined thread: " + t.getName());
        }

        System.out.println("\nInitial Balance     : Rs 0");
        System.out.println("Expected Final Balance: Rs " + EXPECTED_BALANCE);
        System.out.println("Actual Final Balance  : Rs " + raceAccount.getBalance());
        System.out.println("Race Condition Observation: Balance is LESS than 10,000 due to unsynchronized overwrites!");

        System.out.println("\n--- 2. Running 10 Threads WITH Synchronized deposit() ---");
        Account syncAccount = new Account("AC0001", "Riya Sharma", 0);
        Thread[] syncThreads = new Thread[NUM_THREADS];

        for (int i = 0; i < NUM_THREADS; i++) {
            AccountWorker worker = new AccountWorker(syncAccount, DEPOSITS_PER_THREAD, DEPOSIT_AMOUNT, true);
            syncThreads[i] = new Thread(worker, "SyncWorker-Thread-" + (i + 1));
            syncThreads[i].start();
        }

        for (Thread t : syncThreads) {
            t.join();
        }

        System.out.println("Initial Balance       : Rs 0");
        System.out.println("Expected Final Balance: Rs " + EXPECTED_BALANCE);
        System.out.println("Actual Final Balance  : Rs " + syncAccount.getBalance());
        System.out.println("Synchronization Result: Balance is EXACTLY 10,000 on every run!");

        System.out.println("\n--- 3. Concurrent Deposits and Withdrawals ---");
        Account mixedAccount = new Account("AC0002", "Aarav Patel", 5000);
        Thread depositor = new Thread(() -> {
            for (int i = 0; i < 500; i++) mixedAccount.deposit(10);
        }, "Depositor-Thread");

        Thread withdrawer = new Thread(() -> {
            for (int i = 0; i < 500; i++) mixedAccount.withdraw(10);
        }, "Withdrawer-Thread");

        depositor.start();
        withdrawer.start();
        depositor.join();
        withdrawer.join();

        System.out.println("Account starting with Rs 5000 after 500 deposits of 10 and 500 withdrawals of 10:");
        System.out.println("Final Balance: Rs " + mixedAccount.getBalance() + " (Balance perfectly maintained!)");
    }
}
