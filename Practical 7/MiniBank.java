import model.Account;
import model.Customer;
import util.AnnotationValidator;

public class MiniBank {
    public static void main(String[] args) {
        System.out.println("=== MiniBank - Lab 07: Annotations & Reflection ===\n");

        Account invalidAccount = new Account("AC0001", "Riya Sharma", -100);
        System.out.println("Testing invalid account: " + invalidAccount);

        String[] errors = AnnotationValidator.validate(invalidAccount);
        System.out.println("Validation errors count: " + errors.length);
        for (String err : errors) {
            System.out.println("  [ERROR] " + err);
        }

        System.out.println("\nTesting valid account: ");
        Account validAccount = new Account("AC0002", "Aarav Patel", 5000);
        String[] validErrors = AnnotationValidator.validate(validAccount);
        System.out.println("Validation errors count for validAccount: " + validErrors.length);

        System.out.println("\nTesting Customer with invalid (too long) mobile number:");
        Customer invalidCustomer = new Customer("Test User", "test@domain.com", "987654321099999");
        String[] custErrors = AnnotationValidator.validate(invalidCustomer);
        for (String err : custErrors) {
            System.out.println("  [ERROR] " + err);
        }
    }
}
