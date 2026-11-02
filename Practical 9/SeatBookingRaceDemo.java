class UnsyncCinema {
    private int seatsLeft = 5;
    private int successfulBookings = 0;

    public boolean book(String user) {
        if (seatsLeft > 0) {
            try {

                Thread.sleep(10);
            } catch (InterruptedException ignored) {}
            seatsLeft--;
            successfulBookings++;
            System.out.println("  " + user + " booked a seat. Seats left: " + seatsLeft);
            return true;
        }
        return false;
    }

    public int getSuccessfulBookings() {
        return successfulBookings;
    }
}

class SyncCinema {
    private int seatsLeft = 5;
    private int successfulBookings = 0;

    public synchronized boolean book(String user) {
        if (seatsLeft > 0) {
            try {
                Thread.sleep(5);
            } catch (InterruptedException ignored) {}
            seatsLeft--;
            successfulBookings++;
            System.out.println("  " + user + " booked a seat. Seats left: " + seatsLeft);
            return true;
        }
        return false;
    }

    public synchronized int getSuccessfulBookings() {
        return successfulBookings;
    }
}

public class SeatBookingRaceDemo {
    public static void main(String[] args) throws InterruptedException {
        System.out.println("=== Seat Booking Race Condition Demo (5 seats, 10 threads) ===");

        System.out.println("\n--- 1. Unsynchronized Booking (Overselling) ---");
        UnsyncCinema unsyncCinema = new UnsyncCinema();
        Thread[] unsyncThreads = new Thread[10];

        for (int i = 0; i < 10; i++) {
            final String user = "User-" + (i + 1);
            unsyncThreads[i] = new Thread(() -> unsyncCinema.book(user));
            unsyncThreads[i].start();
        }

        for (Thread t : unsyncThreads) {
            t.join();
        }

        System.out.println("Total successful bookings in Unsynchronized Cinema: " + unsyncCinema.getSuccessfulBookings());
        if (unsyncCinema.getSuccessfulBookings() > 5) {
            System.out.println("WARNING: OVERSOLD! More than 5 seats were booked!");
        }

        System.out.println("\n--- 2. Synchronized Booking (Thread-Safe) ---");
        SyncCinema syncCinema = new SyncCinema();
        Thread[] syncThreads = new Thread[10];

        for (int i = 0; i < 10; i++) {
            final String user = "User-" + (i + 1);
            syncThreads[i] = new Thread(() -> syncCinema.book(user));
            syncThreads[i].start();
        }

        for (Thread t : syncThreads) {
            t.join();
        }

        System.out.println("Total successful bookings in Synchronized Cinema: " + syncCinema.getSuccessfulBookings());
        System.out.println("Result: Exactly 5 seats booked, preventing oversell!");
    }
}
