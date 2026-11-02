public class DeadlockDemo {

    static class Resource {
        private final String name;
        public Resource(String name) { this.name = name; }
        public String getName() { return name; }
    }

    public static void main(String[] args) throws InterruptedException {
        System.out.println("=== Deadlock Simulation & Resolution Demo ===\n");

        Resource resA = new Resource("Resource-A");
        Resource resB = new Resource("Resource-B");

        System.out.println("Running Deadlock-Free Execution using Consistent Lock Ordering:");

        Thread t1 = new Thread(() -> {

            synchronized (resA) {
                System.out.println("Thread 1: Acquired " + resA.getName());
                try { Thread.sleep(50); } catch (InterruptedException ignored) {}

                synchronized (resB) {
                    System.out.println("Thread 1: Acquired " + resB.getName() + " -> Task Completed!");
                }
            }
        }, "Thread-1");

        Thread t2 = new Thread(() -> {

            synchronized (resA) {
                System.out.println("Thread 2: Acquired " + resA.getName());
                try { Thread.sleep(50); } catch (InterruptedException ignored) {}

                synchronized (resB) {
                    System.out.println("Thread 2: Acquired " + resB.getName() + " -> Task Completed!");
                }
            }
        }, "Thread-2");

        t1.start();
        t2.start();

        t1.join();
        t2.join();

        System.out.println("\nBoth threads finished safely! Consistent ordering completely prevented deadlock.");
    }
}
