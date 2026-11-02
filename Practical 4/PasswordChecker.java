public class PasswordChecker {

    public static boolean hasLength(String pw) {
        return pw != null && pw.length() >= 8;
    }

    public static boolean hasUpperCase(String pw) {
        return pw != null && pw.matches(".*[A-Z].*");
    }

    public static boolean hasDigit(String pw) {
        return pw != null && pw.matches(".*[0-9].*");
    }

    public static boolean hasSpecialChar(String pw) {
        return pw != null && pw.matches(".*[!@#$%^&*()_+\\-=\\[\\]{};':\"\\\\|,.<>\\/?].*");
    }

    public static String strength(String pw) {
        int count = 0;
        if (hasLength(pw)) count++;
        if (hasUpperCase(pw)) count++;
        if (hasDigit(pw)) count++;
        if (hasSpecialChar(pw)) count++;

        return switch (count) {
            case 0, 1 -> "Weak";
            case 2, 3 -> "Medium";
            case 4 -> "Strong";
            default -> "Weak";
        };
    }

    public static void printAnalysis(String pw) {
        System.out.println("Password: \"" + pw + "\"");
        System.out.println(" - Length >= 8     : " + hasLength(pw));
        System.out.println(" - Has Uppercase   : " + hasUpperCase(pw));
        System.out.println(" - Has Digit       : " + hasDigit(pw));
        System.out.println(" - Has Special Char: " + hasSpecialChar(pw));
        System.out.println("Result: " + strength(pw) + "\n");
    }

    public static void main(String[] args) {
        System.out.println("=== Password Strength Checker ===");
        printAnalysis("abc");
        printAnalysis("Secret12");
        printAnalysis("Abcd1234!");
    }
}
