interface Switchable {
    void on();
    void off();

    default void toggle() {
        if (isOn()) {
            off();
        } else {
            on();
        }
    }

    boolean isOn();
}

class Fan implements Switchable {
    private boolean active = false;

    @Override
    public void on() {
        active = true;
        System.out.println("Fan is now spinning ON.");
    }

    @Override
    public void off() {
        active = false;
        System.out.println("Fan is now turned OFF.");
    }

    @Override
    public boolean isOn() {
        return active;
    }

    @Override
    public String toString() {
        return "Ceiling Fan [State=" + (active ? "ON" : "OFF") + "]";
    }
}

class Light implements Switchable {
    private boolean active = false;

    @Override
    public void on() {
        active = true;
        System.out.println("Light is now shining ON.");
    }

    @Override
    public void off() {
        active = false;
        System.out.println("Light is now switched OFF.");
    }

    @Override
    public boolean isOn() {
        return active;
    }

    @Override
    public String toString() {
        return "Living Room Light [State=" + (active ? "ON" : "OFF") + "]";
    }
}

@FunctionalInterface
interface SwitchPermission {
    boolean maySwitchOn(Switchable device, int hour);
}

public class RemoteControlDemo {
    public static void main(String[] args) {
        System.out.println("=== Interface & Default Method Demo: Switchable ===");

        Switchable[] appliances = { new Fan(), new Light() };

        System.out.println("--- Toggling all appliances ---");
        for (Switchable s : appliances) {
            System.out.println("Toggling: " + s);
            s.toggle();
        }

        System.out.println("\n--- Toggling all appliances again ---");
        for (Switchable s : appliances) {
            System.out.println("Toggling: " + s);
            s.toggle();
        }

        System.out.println("\n=== Functional Interface: Anonymous Class vs Lambda ===");
        int currentHour = 23;

        SwitchPermission fanNightRule = new SwitchPermission() {
            @Override
            public boolean maySwitchOn(Switchable device, int hour) {
                return hour < 22;
            }
        };

        SwitchPermission lightRule = (device, hour) -> (hour >= 6 && hour <= 23);

        Fan testFan = new Fan();
        Light testLight = new Light();

        System.out.println("Hour = " + currentHour + ":00");
        System.out.println("Anonymous class check (Fan may turn on?): " +
                fanNightRule.maySwitchOn(testFan, currentHour));
        System.out.println("Lambda expression check (Light may turn on?): " +
                lightRule.maySwitchOn(testLight, currentHour));
    }
}
