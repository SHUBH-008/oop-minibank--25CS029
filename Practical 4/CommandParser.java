public class CommandParser {

    public static Command parse(String line) throws IllegalArgumentException {
        if (line == null || line.trim().isEmpty()) {
            throw new IllegalArgumentException("Error: Command line cannot be empty.");
        }

        String[] parts = line.trim().split("\\s+");
        if (parts.length != 3) {
            throw new IllegalArgumentException("Error: Invalid command format. Expected 3 tokens: <TYPE> <ACCOUNT_NO> <AMOUNT>. Got " + parts.length + " tokens.");
        }

        TransactionType type;
        try {
            type = TransactionType.valueOf(parts[0].toUpperCase());
        } catch (IllegalArgumentException e) {
            throw new IllegalArgumentException("Error: Unsupported transaction type '" + parts[0] + "'. Must be DEPOSIT, WITHDRAW, or TRANSFER.");
        }

        String accountNumber = parts[1];

        long amount;
        try {
            amount = Long.parseLong(parts[2]);
            if (amount <= 0) {
                throw new IllegalArgumentException("Error: Amount must be a positive integer.");
            }
        } catch (NumberFormatException e) {
            throw new IllegalArgumentException("Error: Invalid numeric amount '" + parts[2] + "'.");
        }

        return new Command(type, accountNumber, amount);
    }
}
