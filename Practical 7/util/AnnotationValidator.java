package util;

import model.annotation.MaxLength;
import model.annotation.Positive;
import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;

public class AnnotationValidator {

    public static String[] validate(Object obj) {
        if (obj == null) return new String[0];

        List<String> errors = new ArrayList<>();
        Field[] fields = obj.getClass().getDeclaredFields();

        for (Field field : fields) {
            field.setAccessible(true);
            try {
                Object value = field.get(obj);

                if (field.isAnnotationPresent(Positive.class)) {
                    Positive pos = field.getAnnotation(Positive.class);
                    if (value instanceof Number num) {
                        if (num.doubleValue() <= 0) {
                            errors.add("Field '" + field.getName() + "': " + pos.message());
                        }
                    }
                }

                if (field.isAnnotationPresent(MaxLength.class)) {
                    MaxLength ml = field.getAnnotation(MaxLength.class);
                    if (value instanceof String str) {
                        if (str != null && str.length() > ml.value()) {
                            errors.add("Field '" + field.getName() + "': " + ml.message() +
                                    " (allowed max: " + ml.value() + ", actual length: " + str.length() + ")");
                        }
                    }
                }

            } catch (IllegalAccessException e) {
                errors.add("Field '" + field.getName() + "': access error - " + e.getMessage());
            }
        }

        return errors.toArray(new String[0]);
    }
}
