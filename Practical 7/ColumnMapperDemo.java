import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;
import java.lang.reflect.Field;
import java.util.HashMap;
import java.util.Map;

@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.FIELD)
@interface Column {
    String name();
}

class UserRecord {
    @Column(name = "user_id")
    private int id;

    @Column(name = "full_name")
    private String name;

    @Column(name = "email_address")
    private String email;

    @Column(name = "bonus_points")
    private int bonus;

    public UserRecord() {}

    @Override
    public String toString() {
        return "UserRecord [id=" + id + ", name=" + name + ", email=" + email + ", bonus=" + bonus + "]";
    }
}

public class ColumnMapperDemo {

    public static <T> T mapRow(Class<T> clazz, String[] headers, String[] data) throws Exception {
        T instance = clazz.getDeclaredConstructor().newInstance();

        Map<String, String> dataMap = new HashMap<>();
        for (int i = 0; i < headers.length; i++) {
            if (i < data.length) {
                dataMap.put(headers[i].trim().toLowerCase(), data[i].trim());
            }
        }

        Field[] fields = clazz.getDeclaredFields();
        for (Field field : fields) {
            if (field.isAnnotationPresent(Column.class)) {
                Column col = field.getAnnotation(Column.class);
                String colName = col.name().trim().toLowerCase();

                if (dataMap.containsKey(colName)) {
                    String rawValue = dataMap.get(colName);
                    field.setAccessible(true);

                    if (field.getType() == int.class || field.getType() == Integer.class) {
                        field.set(instance, Integer.parseInt(rawValue));
                    } else if (field.getType() == long.class || field.getType() == Long.class) {
                        field.set(instance, Long.parseLong(rawValue));
                    } else if (field.getType() == double.class || field.getType() == Double.class) {
                        field.set(instance, Double.parseDouble(rawValue));
                    } else {
                        field.set(instance, rawValue);
                    }
                } else {
                    System.out.println("Notice: Column '" + col.name() + "' missing in data row. Kept default value.");
                }
            }
        }
        return instance;
    }

    public static void main(String[] args) throws Exception {
        System.out.println("=== Reflective Column Mapper Demo (@Column Annotation) ===\n");

        String[] headers = { "user_id", "full_name", "email_address" };
        String[] row1 = { "101", "Shubh", "shubh@example.com" };
        String[] row2 = { "102", "Priya Patel", "priya@example.com" };

        System.out.println("Headers: " + String.join(", ", headers));

        UserRecord u1 = mapRow(UserRecord.class, headers, row1);
        System.out.println("Mapped Row 1: " + u1);

        UserRecord u2 = mapRow(UserRecord.class, headers, row2);
        System.out.println("Mapped Row 2: " + u2);
    }
}
