import java.util.LinkedList;
import java.util.Queue;

class SharedBuffer {
    private final Queue<Integer> queue = new LinkedList<>();
    private final int capacity;

    public SharedBuffer(int capacity) {
        this.capacity = capacity;
    }

    public synchronized void produce(int item) throws InterruptedException {
        while (queue.size() == capacity) {
            wait();
        }
        queue.add(item);
        System.out.println("[Producer] Produced: " + item + " (Buffer size: " + queue.size() + "/" + capacity + ")");
        notifyAll();
    }

    public synchronized int consume() throws InterruptedException {
        while (queue.isEmpty()) {
            wait();
        }
        int item = queue.poll();
        System.out.println("[Consumer] Consumed: " + item + " (Buffer size: " + queue.size() + "/" + capacity + ")");
        notifyAll();
        return item;
    }
}

public class ProducerConsumerDemo {
    public static void main(String[] args) throws InterruptedException {
        System.out.println("=== Producer-Consumer Demo with wait() & notify() ===\n");

        final int TOTAL_ITEMS = 10;
        final int BUFFER_CAPACITY = 3;
        SharedBuffer buffer = new SharedBuffer(BUFFER_CAPACITY);

        Thread producerThread = new Thread(() -> {
            try {
                for (int i = 1; i <= TOTAL_ITEMS; i++) {
                    buffer.produce(i);
                    Thread.sleep(50);
                }
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            }
        }, "Producer");

        Thread consumerThread = new Thread(() -> {
            try {
                for (int i = 1; i <= TOTAL_ITEMS; i++) {
                    buffer.consume();
                    Thread.sleep(100);
                }
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            }
        }, "Consumer");

        producerThread.start();
        consumerThread.start();

        producerThread.join();
        consumerThread.join();

        System.out.println("\nSuccessfully produced and consumed all " + TOTAL_ITEMS + " items in order with zero loss!");
    }
}
