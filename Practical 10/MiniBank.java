import java.util.LinkedList;
import java.util.Queue;

public class MiniBank {

    static class TransactionQueue {
        private final Queue<String> queue = new LinkedList<>();
        private final int capacity = 3;

        public synchronized void produce(String tx) throws InterruptedException {
            while (queue.size() == capacity) {
                wait();
            }
            queue.add(tx);
            System.out.println("[Producer] Queued transaction: " + tx);
            notifyAll();
        }

        public synchronized String consume() throws InterruptedException {
            while (queue.isEmpty()) {
                wait();
            }
            String tx = queue.poll();
            System.out.println("[Consumer] Processed transaction: " + tx);
            notifyAll();
            return tx;
        }
    }

    public static void main(String[] args) throws InterruptedException {
        System.out.println("=== MiniBank - Lab 10: Transaction Engine, Thread Pools & Deadlock Avoidance ===\n");

        Account a1 = new Account("AC0001", "Riya Sharma", 10000);
        Account a2 = new Account("AC0002", "Aarav Patel", 10000);

        System.out.println("--- 1. Submitting Batch Transactions to TransactionProcessor ---");
        TransactionProcessor processor = new TransactionProcessor();

        for (int i = 1; i <= 4; i++) {
            final int id = i;
            processor.submit(() -> {
                a1.deposit(250);
                System.out.println("[Pool Worker " + Thread.currentThread().getName() + "] Deposited Rs 250 into " + a1.getAccountNumber());
            });
            processor.submit(() -> {
                a2.withdraw(150);
                System.out.println("[Pool Worker " + Thread.currentThread().getName() + "] Withdrew Rs 150 from " + a2.getAccountNumber());
            });
        }

        System.out.println("Stopping TransactionProcessor and awaiting task completion...");
        processor.stop();
        System.out.println("Batch processing completed successfully!\nBalances -> " + a1 + " | " + a2);

        System.out.println("\n--- 2. Inter-thread Transaction Producer-Consumer Buffer ---");
        TransactionQueue txQueue = new TransactionQueue();

        Thread producer = new Thread(() -> {
            try {
                String[] batch = { "TX-101: Deposit 500", "TX-102: Transfer 300", "TX-103: Withdraw 200", "TX-104: Deposit 1000" };
                for (String tx : batch) {
                    txQueue.produce(tx);
                    Thread.sleep(40);
                }
            } catch (InterruptedException ignored) {}
        }, "TxProducer");

        Thread consumer = new Thread(() -> {
            try {
                for (int i = 0; i < 4; i++) {
                    txQueue.consume();
                    Thread.sleep(70);
                }
            } catch (InterruptedException ignored) {}
        }, "TxConsumer");

        producer.start();
        consumer.start();
        producer.join();
        consumer.join();

        System.out.println("\n--- 3. Testing Deadlock Avoidance via Ordered Locking ---");
        System.out.println("Two threads transferring concurrently: Thread-1 (A1 -> A2) and Thread-2 (A2 -> A1)");

        Thread t1 = new Thread(() -> a1.transferSafe(a2, 1000), "TransferThread-1");
        Thread t2 = new Thread(() -> a2.transferSafe(a1, 500), "TransferThread-2");

        t1.start();
        t2.start();

        t1.join();
        t2.join();

        System.out.println("\nBoth transfers completed without deadlock!");
        System.out.println("Final state: " + a1);
        System.out.println("Final state: " + a2);
    }
}
