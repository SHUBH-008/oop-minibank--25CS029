import java.util.concurrent.atomic.AtomicLong;

public class ArraySumRaceDemo {

    static long raceSum = 0;
    static long syncSum = 0;
    static final Object lock = new Object();
    static AtomicLong atomicSum = new AtomicLong(0);

    public static void main(String[] args) throws InterruptedException {
        System.out.println("=== Parallel Array Summation: Race & Fixes Demo ===\n");

        final int ARRAY_SIZE = 1_000_000;
        final int NUM_THREADS = 4;
        final int CHUNK_SIZE = ARRAY_SIZE / NUM_THREADS;

        int[] data = new int[ARRAY_SIZE];
        for (int i = 0; i < ARRAY_SIZE; i++) {
            data[i] = 1;
        }
        final long EXPECTED_SUM = ARRAY_SIZE;

        raceSum = 0;
        Thread[] raceThreads = new Thread[NUM_THREADS];
        for (int t = 0; t < NUM_THREADS; t++) {
            final int start = t * CHUNK_SIZE;
            final int end = (t == NUM_THREADS - 1) ? ARRAY_SIZE : (start + CHUNK_SIZE);
            raceThreads[t] = new Thread(() -> {
                for (int i = start; i < end; i++) {
                    raceSum += data[i];
                }
            });
            raceThreads[t].start();
        }
        for (Thread t : raceThreads) t.join();
        System.out.println("1. Unsynchronized Sum: " + raceSum + " (Expected: " + EXPECTED_SUM + ") -> Data Lost: " + (EXPECTED_SUM - raceSum));

        syncSum = 0;
        Thread[] syncThreads = new Thread[NUM_THREADS];
        long startSync = System.currentTimeMillis();
        for (int t = 0; t < NUM_THREADS; t++) {
            final int start = t * CHUNK_SIZE;
            final int end = (t == NUM_THREADS - 1) ? ARRAY_SIZE : (start + CHUNK_SIZE);
            syncThreads[t] = new Thread(() -> {
                long partial = 0;
                for (int i = start; i < end; i++) {
                    partial += data[i];
                }
                synchronized (lock) {
                    syncSum += partial;
                }
            });
            syncThreads[t].start();
        }
        for (Thread t : syncThreads) t.join();
        long syncTime = System.currentTimeMillis() - startSync;
        System.out.println("2. Synchronized Sum  : " + syncSum + " (Expected: " + EXPECTED_SUM + ") in " + syncTime + " ms");

        atomicSum.set(0);
        Thread[] atomicThreads = new Thread[NUM_THREADS];
        long startAtomic = System.currentTimeMillis();
        for (int t = 0; t < NUM_THREADS; t++) {
            final int start = t * CHUNK_SIZE;
            final int end = (t == NUM_THREADS - 1) ? ARRAY_SIZE : (start + CHUNK_SIZE);
            atomicThreads[t] = new Thread(() -> {
                long partial = 0;
                for (int i = start; i < end; i++) {
                    partial += data[i];
                }
                atomicSum.addAndGet(partial);
            });
            atomicThreads[t].start();
        }
        for (Thread t : atomicThreads) t.join();
        long atomicTime = System.currentTimeMillis() - startAtomic;
        System.out.println("3. AtomicLong Sum    : " + atomicSum.get() + " (Expected: " + EXPECTED_SUM + ") in " + atomicTime + " ms");
    }
}
