import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;
import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;

@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.FIELD)
@interface NotBlank {
    String message() default "must not be blank";
}

@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.FIELD)
@interface MaxLength {
    int value();
    String message() default "exceeds maximum length";
}

class SignupForm {
    @NotBlank
    @MaxLength(value = 10, message = "username cannot exceed 10 characters")
    private String username;

    @NotBlank(message = "email is required")
    private String email;

    @MaxLength(value = 15, message = "phone number cannot exceed 15 digits")
    private String phone;

    public SignupForm(String username, String email, String phone) {
        this.username = username;
        this.email = email;
        this.phone = phone;
    }
}

public class FormValidatorDemo {

    public static List<String> validate(Object form) {
        List<String> errors = new ArrayList<>();
        Field[] fields = form.getClass().getDeclaredFields();

        for (Field field : fields) {
            field.setAccessible(true);
            try {
                Object val = field.get(form);
                String strVal = (val == null) ? null : val.toString();

                if (field.isAnnotationPresent(NotBlank.class)) {
                    NotBlank nb = field.getAnnotation(NotBlank.class);
                    if (strVal == null || strVal.trim().isEmpty()) {
                        errors.add(field.getName() + ": " + nb.message());
                    }
                }

                if (field.isAnnotationPresent(MaxLength.class)) {
                    MaxLength ml = field.getAnnotation(MaxLength.class);
                    if (strVal != null && strVal.length() > ml.value()) {
                        errors.add(field.getName() + ": " + ml.message() + " (max: " + ml.value() + ")");
                    }
                }
            } catch (IllegalAccessException e) {
                errors.add("Failed to inspect field: " + field.getName());
            }
        }
        return errors;
    }

    public static void main(String[] args) {
        System.out.println("=== Reflection Form Validator Demo ===");

        SignupForm invalidForm = new SignupForm("superlongusername123", "", "1234567890");
        System.out.println("Validating invalidForm...");
        List<String> errors = validate(invalidForm);
        for (String err : errors) {
            System.out.println("  [ERROR] " + err);
        }

        SignupForm validForm = new SignupForm("shubh", "shubh@gmail.com", "9876543210");
        System.out.println("\nValidating validForm...");
        List<String> validErrors = validate(validForm);
        System.out.println("Errors found: " + validErrors.size() + " (Form is completely valid!)");
    }
}
