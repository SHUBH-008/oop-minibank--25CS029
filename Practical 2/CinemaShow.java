public class CinemaShow {
    private String title;
    private int seatsAvailable;
    private final int capacity;
    private static int totalBooked = 0;

    public CinemaShow(String title, int capacity) {
        this.title = title;
        this.capacity = capacity;
        this.seatsAvailable = capacity;
    }

    public CinemaShow(String title) {
        this(title, 100);
    }

    public boolean book(int n) {
        if (n <= seatsAvailable) {
            seatsAvailable -= n;
            totalBooked += n;
            return true;
        }
        return false;
    }

    public void cancel(int n) {
        seatsAvailable = Math.min(capacity, seatsAvailable + n);
    }

    public int getSeatsAvailable() {
        return seatsAvailable;
    }

    public int getCapacity() {
        return capacity;
    }

    public String getTitle() {
        return title;
    }

    public static int getTotalBooked() {
        return totalBooked;
    }

    public static void main(String[] args) {
        System.out.println("=== Cinema Show Booking System ===");
        CinemaShow show = new CinemaShow("Inception: IMAX", 50);

        System.out.println("Show: " + show.getTitle() + " | Capacity: " + show.getCapacity() + " seats\n");

        int[] bookingRequests = {20, 25, 10, 5};
        for (int req : bookingRequests) {
            boolean success = show.book(req);
            System.out.println("Book " + req + " seats: Success = " + success +
                    " | Remaining seats: " + show.getSeatsAvailable());
        }

        System.out.println("\nCanceling 8 seats...");
        show.cancel(8);
        System.out.println("After cancellation | Remaining seats: " + show.getSeatsAvailable());

        System.out.println("\nAttempting to book 60 seats (exceeding availability)...");
        boolean overbook = show.book(60);
        System.out.println("Book 60 seats: Success = " + overbook +
                " | Remaining seats: " + show.getSeatsAvailable());

        System.out.println("\nTotal seats booked across all shows: " + CinemaShow.getTotalBooked());
    }
}
