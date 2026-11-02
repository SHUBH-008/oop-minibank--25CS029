@FunctionalInterface
interface Notifier {
    void send(String message);
}

interface Urgent {}

class UrgentSmsNotifier implements Notifier, Urgent {
    @Override
    public void send(String message) {
        System.out.println("[SMS ALERT] " + message);
    }
}

public class NotifierDemo {
    public static void main(String[] args) {
        System.out.println("=== Functional & Marker Interface Demo: Notifiers ===");

        Notifier emailNotifier = msg -> System.out.println("[EMAIL NOTIFICATION] " + msg);

        Notifier pushNotifier = msg -> System.out.println("[PUSH NOTIFICATION] " + msg);

        Notifier urgentSms = new UrgentSmsNotifier();

        Notifier[] notifiers = { emailNotifier, pushNotifier, urgentSms };

        String testMessage = "Server maintenance scheduled tonight at 11:00 PM.";
        System.out.println("Broadcasting message: \"" + testMessage + "\"\n");

        for (Notifier n : notifiers) {
            n.send(testMessage);

            if (n instanceof Urgent) {
                System.out.println("  *(Urgent marker detected! Resending immediate duplicate alert)*");
                n.send(testMessage);
            }
        }
    }
}
