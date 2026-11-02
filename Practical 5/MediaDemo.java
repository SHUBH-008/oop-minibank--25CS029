abstract class MediaItem {
    private String title;
    private int daysLate;

    public MediaItem(String title, int daysLate) {
        this.title = title;
        this.daysLate = Math.max(0, daysLate);
    }

    public String getTitle() {
        return title;
    }

    public int getDaysLate() {
        return daysLate;
    }

    abstract double calculateLateFee();
}

class Book extends MediaItem {
    public Book(String title, int daysLate) {
        super(title, daysLate);
    }

    @Override
    double calculateLateFee() {

        return getDaysLate() * 2.0;
    }
}

class Dvd extends MediaItem {
    public Dvd(String title, int daysLate) {
        super(title, daysLate);
    }

    @Override
    double calculateLateFee() {

        return getDaysLate() > 0 ? (getDaysLate() * 10.0 + 20.0) : 0.0;
    }
}

class Magazine extends MediaItem {
    public Magazine(String title, int daysLate) {
        super(title, daysLate);
    }

    @Override
    double calculateLateFee() {

        int chargeableDays = Math.max(0, getDaysLate() - 2);
        return chargeableDays * 5.0;
    }
}

public class MediaDemo {
    public static void main(String[] args) {
        System.out.println("=== Media Late Fee Polymorphism Demo ===");

        MediaItem[] batch = {
            new Book("Clean Code", 5),
            new Dvd("The Matrix", 3),
            new Magazine("National Geographic", 6),
            new Book("Design Patterns", 0),
            new Dvd("Inception", 7),
            new Magazine("IEEE Software", 1)
        };

        double totalFees = 0.0;

        for (MediaItem item : batch) {
            double fee = item.calculateLateFee();
            totalFees += fee;
            System.out.printf("%-10s: %-25s Overdue: %2d days | Fee: Rs %6.2f%n",
                    item.getClass().getSimpleName(), item.getTitle(), item.getDaysLate(), fee);
        }

        System.out.println("----------------------------------------------------------------");
        System.out.printf("Total Late Fees for Returned Batch: Rs %.2f%n", totalFees);
    }
}
