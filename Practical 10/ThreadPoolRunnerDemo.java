import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

public class ThreadPoolRunnerDemo {
    public static void main(String[] args) {
        System.out.println("=== Fixed Thread Pool Executor Demo (Pool of 3) ===\n");

        ExecutorService pool = Executors.newFixedThreadPool(3);

        for (int i = 1; i <= 10; i++) {
            final int taskId = i;
            pool.submit(() -> {
                String threadName = Thread.currentThread().getName();
                System.out.println("[Task " + taskId + "] STARTING on " + threadName);
                try {
                    Thread.sleep(150);
                } catch (InterruptedException e) {
                    Thread.currentThread().interrupt();
                }
                System.out.println("[Task " + taskId + "] FINISHED on " + threadName);
            });
        }

        System.out.println("All 10 tasks submitted to thread pool. Initiating shutdown...");
        pool.shutdown();

        try {
            if (pool.awaitTermination(5, TimeUnit.SECONDS)) {
                System.out.println("\nAll tasks completed successfully within timeout!");
                System.out.println("Observation: Notice how all 10 tasks were executed by the SAME 3 worker threads (pool-1-thread-1, 2, 3) being reused.");
            } else {
                pool.shutdownNow();
            }
        } catch (InterruptedException e) {
            pool.shutdownNow();
        }
    }
}
