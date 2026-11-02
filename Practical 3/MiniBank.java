public class MiniBank {
    public static void main(String[] args) {
        System.out.println("=== MiniBank - Lab 03: Object Methods & Nested Classes ===\n");

        Customer.Address addr = new Customer.Address("42 MG Road", "Ahmedabad", "380001");
        Customer cust1 = new Customer("Riya", "riya@example.com", "9876543210", addr);

        System.out.println("Customer 1: " + cust1);

        Customer custClone = cust1.clone();
        System.out.println("Cloned Customer: " + custClone);
        System.out.println("cust1 == custClone: " + (cust1 == custClone) + " (shallow check - should be false)");
        System.out.println("Address cloned: " + (cust1.getAddress() != custClone.getAddress()) + "\n");

        Account a1 = new Account("AC0001", "Riya", 4000);
        Account a2 = new Account("AC0001", "Riya Duplicate", 5000);
        Account a3 = new Account("AC0002", "Aarav", 8000);

        System.out.println("Account 1 toString(): " + a1);
        System.out.println("Account 2 toString(): " + a2);
        System.out.println("Account 3 toString(): " + a3);

        System.out.println("\n--- Testing Equality Contract ---");
        System.out.println("a1.equals(a2) (same account number 'AC0001'): " + a1.equals(a2));
        System.out.println("a1.hashCode() == a2.hashCode(): " + (a1.hashCode() == a2.hashCode()));
        System.out.println("a1.equals(a3) (different account number): " + a1.equals(a3));

        System.out.println("\n--- Testing instanceof Operator ---");
        Object obj = a1;
        if (obj instanceof Account) {
            Account verifiedAcc = (Account) obj;
            System.out.println("Verified object is an instance of Account: " + verifiedAcc.getAccountNumber());
        }

        if (cust1 instanceof Cloneable) {
            System.out.println("Customer implements Cloneable interface verified.");
        }

        System.out.println("\n" + a1.getFormattedStatement());
    }
}
