import java.util.Scanner;

@FunctionalInterface
interface DiscountRule {
    double apply(double price);
}

public class DiscountDemo {
    public static void main(String[] args) {
        System.out.println("=== Lambda Discount Engine ===");

        double[] prices = { 500.0, 1200.0, 3500.0, 80.0, 15000.0 };

        DiscountRule festiveDiscount = price -> price * 0.85;
        DiscountRule flatHundredOff = price -> Math.max(0, price - 100);
        DiscountRule tieredDiscount = price -> (price > 2000) ? (price * 0.80) : price;

        System.out.println("Original item prices:");
        for (double p : prices) {
            System.out.printf("  Rs %.2f%n", p);
        }

        System.out.println("\nSelect discount rule to apply:");
        System.out.println("1. Festive Season (15% off everything)");
        System.out.println("2. Flat Discount (Rs 100 off each item)");
        System.out.println("3. High Value Tier (20% off items above Rs 2000)");

        int choice = 1;
        if (args.length > 0) {
            try {
                choice = Integer.parseInt(args[0]);
            } catch (NumberFormatException ignored) {}
        }

        DiscountRule selectedRule = switch (choice) {
            case 2 -> flatHundredOff;
            case 3 -> tieredDiscount;
            default -> festiveDiscount;
        };

        String ruleName = switch (choice) {
            case 2 -> "Flat Rs 100 Off";
            case 3 -> "High Value Tier (20% off > 2000)";
            default -> "Festive Season (15% Off)";
        };

        System.out.println("\nApplied Rule: " + ruleName);
        double totalOriginal = 0;
        double totalDiscounted = 0;

        for (double p : prices) {
            double discounted = selectedRule.apply(p);
            totalOriginal += p;
            totalDiscounted += discounted;
            System.out.printf("Original: Rs %8.2f  -->  Discounted: Rs %8.2f%n", p, discounted);
        }

        System.out.println("-----------------------------------------------------");
        System.out.printf("Total Before: Rs %8.2f%n", totalOriginal);
        System.out.printf("Total After:  Rs %8.2f%n", totalDiscounted);
        System.out.printf("Total Saved:  Rs %8.2f%n", (totalOriginal - totalDiscounted));
    }
}
