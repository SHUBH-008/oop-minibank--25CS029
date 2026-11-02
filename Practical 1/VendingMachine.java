import java.util.Scanner;

public class VendingMachine {

    public enum Coin {
        ONE, TWO, FIVE, TEN
    }

    public static void main(String[] args) {
        final int SNACK_PRICE = 15;
        int runningTotal = 0;
        Scanner scanner = new Scanner(System.in);

        System.out.println("=== Vending Machine ===");
        System.out.println("Snack price: " + SNACK_PRICE);
        System.out.println("Accepted coins: ONE, TWO, FIVE, TEN");

        while (runningTotal < SNACK_PRICE) {
            System.out.print("Enter coin (ONE, TWO, FIVE, TEN): ");
            String input = scanner.next();

            Coin coin;
            try {
                coin = Coin.valueOf(input.toUpperCase().trim());
            } catch (IllegalArgumentException e) {
                System.out.println("Invalid coin! Please enter ONE, TWO, FIVE, or TEN.");
                continue;
            }

            int coinValue = switch (coin) {
                case ONE -> 1;
                case TWO -> 2;
                case FIVE -> 5;
                case TEN -> 10;
            };

            runningTotal += coinValue;
            System.out.println("Total so far: " + runningTotal);
        }

        int change = runningTotal - SNACK_PRICE;
        System.out.println("Paid. Change: " + change);
        scanner.close();
    }
}
