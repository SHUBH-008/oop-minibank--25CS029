import java.util.HashMap;
import java.util.Map;

class OutOfStockException extends Exception {
    private final int shortfall;

    public OutOfStockException(String message, int shortfall) {
        super(message);
        this.shortfall = shortfall;
    }

    public int getShortfall() {
        return shortfall;
    }
}

class InvalidQuantityException extends Exception {
    public InvalidQuantityException(String message) {
        super(message);
    }
}

class Warehouse {
    private final Map<String, Integer> inventory = new HashMap<>();

    public void stock(String item, int qty) {
        inventory.put(item, inventory.getOrDefault(item, 0) + qty);
    }

    public void issue(String item, int qty) throws OutOfStockException, InvalidQuantityException {
        if (qty <= 0) {
            throw new InvalidQuantityException("Requested quantity " + qty + " must be strictly positive!");
        }

        int available = inventory.getOrDefault(item, 0);
        if (qty > available) {
            int shortfall = qty - available;
            throw new OutOfStockException("Item '" + item + "' is out of stock! Available: " +
                    available + ", Requested: " + qty, shortfall);
        }

        inventory.put(item, available - qty);
        System.out.println("Issued " + qty + " units of '" + item + "'. Remaining in stock: " + (available - qty));
    }
}

public class WarehouseDemo {
    public static void main(String[] args) {
        System.out.println("=== Warehouse Inventory Exception Handling Demo ===\n");

        Warehouse warehouse = new Warehouse();
        warehouse.stock("Laptops", 10);
        warehouse.stock("Monitors", 5);

        Object[][] requests = {
            {"Laptops", 4},
            {"Monitors", -2},
            {"Monitors", 8},
            {"Laptops", 3},
            {"Keyboards", 2}
        };

        for (Object[] req : requests) {
            String item = (String) req[0];
            int qty = (int) req[1];

            System.out.println("Processing request: " + qty + " of '" + item + "'...");
            try {
                warehouse.issue(item, qty);
            } catch (InvalidQuantityException e) {
                System.out.println("  [REJECTED] " + e.getMessage());
            } catch (OutOfStockException e) {
                System.out.println("  [REJECTED] " + e.getMessage() + " (Shortfall: " + e.getShortfall() + " units)");
            }
            System.out.println();
        }

        System.out.println("All requests processed without crashing the inventory system.");
    }
}
