public class MiniBank {
    public static void main(String[] args) {
        System.out.println("=== MiniBank - Lab 04: Validation & Command Parsing ===\n");

        System.out.println("--- 1. Testing Regular Expression Validators ---");
        System.out.println("Mobile Valid ('9876543210')  : " + Validator.isValidMobile("9876543210"));
        System.out.println("Mobile Invalid ('12345')      : " + Validator.isValidMobile("12345"));

        System.out.println("Email Valid ('abc@xyz.com')   : " + Validator.isValidEmail("abc@xyz.com"));
        System.out.println("Email Invalid ('abc.xyz')     : " + Validator.isValidEmail("abc.xyz"));

        System.out.println("PAN Valid ('ABCDE1234F')      : " + Validator.isValidPan("ABCDE1234F"));
        System.out.println("PAN Invalid ('12345ABCDE')    : " + Validator.isValidPan("12345ABCDE"));

        System.out.println("IFSC Valid ('SBIN0001234')    : " + Validator.isValidIfsc("SBIN0001234"));
        System.out.println("IFSC Invalid ('SBIN123')      : " + Validator.isValidIfsc("SBIN123"));

        System.out.println("Amount Valid ('500')          : " + Validator.isValidAmount("500"));
        System.out.println("Amount Invalid ('-50')        : " + Validator.isValidAmount("-50"));

        System.out.println("\n--- 2. Testing Command Parsing ---");
        String sampleLine = "DEPOSIT AC0001 500";
        System.out.println("Input line: \"" + sampleLine + "\"");
        try {
            Command cmd = CommandParser.parse(sampleLine);
            System.out.println("Parsed Command Object: " + cmd);
            System.out.println(" - Type           : " + cmd.type());
            System.out.println(" - Account Number : " + cmd.accountNumber());
            System.out.println(" - Amount         : " + cmd.amount());
        } catch (IllegalArgumentException e) {
            System.out.println("Parser Error: " + e.getMessage());
        }

        System.out.println("\nTesting malformed command: \"WITHDRAW AC0001\"");
        try {
            CommandParser.parse("WITHDRAW AC0001");
        } catch (IllegalArgumentException e) {
            System.out.println("Caught expected error: " + e.getMessage());
        }

        System.out.println("\n--- 3. Testing StringBuilder Statement Formatter ---");
        Account acc = new Account("AC0001", "Riya Sharma", 7500);
        String statement = StatementFormatter.buildStatement(acc);
        System.out.print(statement);
    }
}
