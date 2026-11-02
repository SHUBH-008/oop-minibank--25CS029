import java.util.Scanner;

class DivideByZeroException extends Exception {
    public DivideByZeroException(String message) {
        super(message);
    }
}

public class GuardedCalculator {

    public static double calculate(double a, double b, String op) throws DivideByZeroException {
        return switch (op) {
            case "+" -> a + b;
            case "-" -> a - b;
            case "*" -> a * b;
            case "/" -> {
                if (b == 0) {
                    throw new DivideByZeroException("Cannot divide by zero!");
                }
                yield a / b;
            }
            default -> throw new IllegalArgumentException("Unknown operator: " + op);
        };
    }

    public static void main(String[] args) {
        System.out.println("=== Guarded Calculator with Exception Handling ===");
        Scanner scanner = new Scanner(System.in);
        int attempts = 0;
        boolean success = false;

        String[][] testInputs = {
            {"10", "abc", "+"},
            {"10", "0", "/"},
            {"50", "4", "*"}
        };

        int inputIndex = 0;

        while (!success && inputIndex < testInputs.length) {
            attempts++;
            String[] test = testInputs[inputIndex++];
            System.out.println("\nAttempt " + attempts + ": Evaluating input [" + test[0] + " " + test[2] + " " + test[1] + "]");

            try {
                double num1 = Double.parseDouble(test[0]);
                double num2 = Double.parseDouble(test[1]);
                String op = test[2];

                double result = calculate(num1, num2, op);
                System.out.printf("Result: %.2f %s %.2f = %.2f%n", num1, op, num2, result);
                success = true;

            } catch (NumberFormatException e) {
                System.out.println("Input Error: One or both operands are not valid numbers!");
            } catch (DivideByZeroException e) {
                System.out.println("Math Error: " + e.getMessage());
            } catch (IllegalArgumentException e) {
                System.out.println("Operator Error: " + e.getMessage());
            } finally {
                System.out.println("[Log] Attempt " + attempts + " completed. Status: " + (success ? "SUCCESS" : "FAILED"));
            }
        }

        scanner.close();
    }
}
