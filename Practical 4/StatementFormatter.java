public class StatementFormatter {

    public static String buildStatement(Account account) {
        StringBuilder sb = new StringBuilder();
        sb.append("========================================\n");
        sb.append("         MINIBANK ACCOUNT STATEMENT     \n");
        sb.append("========================================\n");
        sb.append(String.format("Account Number : %s%n", account.getAccountNumber()));
        sb.append(String.format("Account Holder : %s%n", account.getOwnerName()));
        sb.append(String.format("Account Status : %s%n", account.isActive() ? "ACTIVE" : "INACTIVE"));
        sb.append(String.format("Current Balance: Rs %,d%n", account.getBalance()));
        sb.append("----------------------------------------\n");
        sb.append("Generated via MiniBank StatementFormatter\n");
        sb.append("========================================\n");
        return sb.toString();
    }
}
