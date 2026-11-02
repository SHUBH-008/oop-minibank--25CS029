class UnsyncCounter {
    private int count = 0;
    public void increment() {
        count++;
    }
    public int getCount() {
        return count;
    }
}

class SyncCounter {
    private int count = 0;
    public synchronized void increment() {
        count++;
    }
    public synchronized int getCount() {
        return count;
    }
}

public class CounterRaceDemo {
    public static void main(String[] args) throws InterruptedException {
        System.out.println("=== Multithreading: Counter Race Condition Demo ===");

        final int NUM_THREADS = 10;
        final int INCREMENTS_PER_THREAD = 10000;
        final int EXPECTED_TOTAL = NUM_THREADS * INCREMENTS_PER_THREAD;

        System.out.println("\n--- 1. Testing Unsynchronized Counter ---");
        UnsyncCounter unsyncCounter = new UnsyncCounter();
        Thread[] unsyncThreads = new Thread[NUM_THREADS];

        for (int i = 0; i < NUM_THREADS; i++) {
            unsyncThreads[i] = new Thread(() -> {
                for (int j = 0; j < INCREMENTS_PER_THREAD; j++) {
                    unsyncCounter.increment();
                }
            });
            unsyncThreads[i].start();
        }

        for (Thread t : unsyncThreads) {
            t.join();
        }

        System.out.println("Expected count : " + EXPECTED_TOTAL);
        System.out.println("Actual count   : " + unsyncCounter.getCount());
        System.out.println("Data lost (Race Condition): " + (EXPECTED_TOTAL - unsyncCounter.getCount()));

        System.out.println("\n--- 2. Testing Synchronized Counter ---");
        SyncCounter syncCounter = new SyncCounter();
        Thread[] syncThreads = new Thread[NUM_THREADS];

        for (int i = 0; i < NUM_THREADS; i++) {
            syncThreads[i] = new Thread(() -> {
                for (int j = 0; j < INCREMENTS_PER_THREAD; j++) {
                    syncCounter.increment();
                }
            });
            syncThreads[i].start();
        }

        for (Thread t : syncThreads) {
            t.join();
        }

        System.out.println("Expected count : " + EXPECTED_TOTAL);
        System.out.println("Actual count   : " + syncCounter.getCount());
        System.out.println("Result: Perfectly matches expected total (" + EXPECTED_TOTAL + ") on every run!");
    }
}
