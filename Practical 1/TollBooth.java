import java.util.Scanner;

public class TollBooth {

    public record Vehicle(String number, String type) {}

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        int totalToll = 0;
        int bikeCount = 0;
        int carCount = 0;
        int truckCount = 0;

        System.out.println("=== Toll Booth System ===");

        while (true) {
            System.out.print("Enter vehicle number (or 'done' to finish): ");
            String number = scanner.next();

            if (number.equalsIgnoreCase("done")) {
                break;
            }

            System.out.print("Enter vehicle type (bike/car/truck): ");
            String type = scanner.next();

            Vehicle vehicle = new Vehicle(number, type);

            int toll = switch (vehicle.type().toLowerCase()) {
                case "bike" -> {
                    bikeCount++;
                    yield 20;
                }
                case "car" -> {
                    carCount++;
                    yield 50;
                }
                case "truck" -> {
                    truckCount++;
                    yield 150;
                }
                default -> {
                    System.out.println("Invalid vehicle type! Toll not charged.");
                    yield 0;
                }
            };

            totalToll += toll;
            if (toll > 0) {
                System.out.println(vehicle.type() + " [" + vehicle.number() + "] passed. Toll: " + toll);
            }
        }

        System.out.println("\n=== Toll Summary ===");
        System.out.println("Total toll: " + totalToll);

        String mostFrequent;
        int maxCount = Math.max(bikeCount, Math.max(carCount, truckCount));
        if (maxCount == 0) {
            mostFrequent = "None";
        } else if (maxCount == carCount && carCount > bikeCount && carCount > truckCount) {
            mostFrequent = "car";
        } else if (maxCount == bikeCount && bikeCount > carCount && bikeCount > truckCount) {
            mostFrequent = "bike";
        } else if (maxCount == truckCount && truckCount > bikeCount && truckCount > carCount) {
            mostFrequent = "truck";
        } else {

            StringBuilder tie = new StringBuilder();
            if (carCount == maxCount) tie.append("car ");
            if (bikeCount == maxCount) tie.append("bike ");
            if (truckCount == maxCount) tie.append("truck ");
            mostFrequent = tie.toString().trim();
        }

        System.out.println("Most frequent: " + mostFrequent);
        scanner.close();
    }
}
