import java.util.Scanner;

public class MiniBank {

    public record BankInfo(String name, String branch) {}

    public enum MenuOption {
        OPEN_ACCOUNT,
        DEPOSIT,
        WITHDRAW,
        TRANSFER,
        WORKING_HOURS,
        EXIT
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        BankInfo bankInfo = new BankInfo("Apex National Bank", "Central Downtown Branch");

        System.out.println("==================================================");
        System.out.println("       " + bankInfo.name() + " (" + bankInfo.branch() + ")");
        System.out.println("==================================================");

        boolean running = true;
        while (running) {
            System.out.println("\nSelect an action:");
            System.out.println("1. Open Account");
            System.out.println("2. Deposit");
            System.out.println("3. Withdraw");
            System.out.println("4. Transfer");
            System.out.println("5. View Working Hours");
            System.out.println("6. Exit");
            System.out.print("Enter your choice (1-6): ");

            if (!scanner.hasNextInt()) {
                System.out.println("Invalid input! Please enter a number between 1 and 6.");
                scanner.next();
                continue;
            }

            int choice = scanner.nextInt();

            MenuOption option = switch (choice) {
                case 1 -> MenuOption.OPEN_ACCOUNT;
                case 2 -> MenuOption.DEPOSIT;
                case 3 -> MenuOption.WITHDRAW;
                case 4 -> MenuOption.TRANSFER;
                case 5 -> MenuOption.WORKING_HOURS;
                case 6 -> MenuOption.EXIT;
                default -> null;
            };

            if (option == null) {
                System.out.println("Invalid option! Please enter a number from 1 to 6.");
                continue;
            }

            String response = switch (option) {
                case OPEN_ACCOUNT -> "Open Account — to be implemented in a later lab.";
                case DEPOSIT -> "Deposit — to be implemented in a later lab.";
                case WITHDRAW -> "Withdraw — to be implemented in a later lab.";
                case TRANSFER -> "Transfer — to be implemented in a later lab.";
                case WORKING_HOURS -> "Bank Working Hours: Monday to Friday 09:30 AM – 04:30 PM, Saturday 09:30 AM – 01:30 PM.";
                case EXIT -> {
                    running = false;
                    yield "Thank you for using " + bankInfo.name() + ". Have a great day!";
                }
            };

            System.out.println("\n>>> " + response);
        }

        scanner.close();
    }
}
