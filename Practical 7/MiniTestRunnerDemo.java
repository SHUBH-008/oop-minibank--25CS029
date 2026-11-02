import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;
import java.lang.reflect.Method;

@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.METHOD)
@interface Run {}

class CalculatorService {
    @Run
    public void testAddition() {
        System.out.println("  -> testAddition PASSED (2 + 3 == 5)");
    }

    public void helperMethod() {
        System.out.println("  -> This is an internal helper, should NOT run");
    }

    @Run
    public void testSubtraction() {
        System.out.println("  -> testSubtraction PASSED (10 - 4 == 6)");
    }

    @Run
    public void testMultiplication() {
        System.out.println("  -> testMultiplication PASSED (5 * 5 == 25)");
    }

    public void cleanup() {
        System.out.println("  -> Cleanup routine, should NOT run as test");
    }
}

public class MiniTestRunnerDemo {
    public static void main(String[] args) {
        System.out.println("=== Mini Test Runner Demo (@Run Annotation) ===");

        CalculatorService testSuite = new CalculatorService();
        Class<?> clazz = testSuite.getClass();
        Method[] methods = clazz.getDeclaredMethods();

        int ranCount = 0;
        System.out.println("Scanning class: " + clazz.getSimpleName() + " for @Run methods...\n");

        for (Method method : methods) {
            if (method.isAnnotationPresent(Run.class)) {
                try {
                    method.setAccessible(true);
                    System.out.println("[RUNNING] Invoking test method: " + method.getName());
                    method.invoke(testSuite);
                    ranCount++;
                } catch (Exception e) {
                    System.out.println("[FAILED] Method " + method.getName() + " threw an exception: " + e.getMessage());
                }
            }
        }

        System.out.println("\n----------------------------------------");
        System.out.println("Total test methods executed: " + ranCount);
    }
}
