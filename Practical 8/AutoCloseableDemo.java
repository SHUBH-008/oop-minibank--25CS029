class DatabaseConnection implements AutoCloseable {
    private final String connectionString;

    public DatabaseConnection(String connectionString) {
        this.connectionString = connectionString;
        System.out.println("[DB] Connection opened to: " + connectionString);
    }

    public void executeQuery(String query) throws Exception {
        System.out.println("[DB] Executing query: \"" + query + "\"");
        if (query.contains("ERROR")) {
            throw new RuntimeException("SQL execution failure: Syntax error in query");
        }
    }

    @Override
    public void close() {
        System.out.println("[DB] Connection closed gracefully via AutoCloseable.close()!");
    }
}

public class AutoCloseableDemo {
    public static void main(String[] args) {
        System.out.println("=== Try-With-Resources AutoCloseable Verification ===\n");

        System.out.println("--- Test 1: Successful execution with auto-close ---");
        try (DatabaseConnection db = new DatabaseConnection("jdbc:mysql://localhost:3306/bankdb")) {
            db.executeQuery("SELECT * FROM accounts WHERE active = 1");
        } catch (Exception e) {
            System.out.println("Caught exception: " + e.getMessage());
        }

        System.out.println("\n--- Test 2: Exception thrown inside try-with-resources block ---");
        try (DatabaseConnection db = new DatabaseConnection("jdbc:mysql://localhost:3306/bankdb")) {
            db.executeQuery("SELECT * FROM non_existing_table WITH ERROR");
        } catch (Exception e) {
            System.out.println("Caught reported exception in catch block: " + e.getMessage());
        }

        System.out.println("\nNotice: In Test 2, close() was executed BEFORE the catch block handled the error.");
    }
}
