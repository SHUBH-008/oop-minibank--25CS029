import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class TemplateFiller {

    public static String fillTemplate(String template, String[] keys, String[] values) {
        Pattern pattern = Pattern.compile("\\{(\\w+)\\}");
        Matcher matcher = pattern.matcher(template);
        StringBuilder sb = new StringBuilder();

        while (matcher.find()) {
            String placeholderKey = matcher.group(1);
            String replacement = "[?]";

            for (int i = 0; i < keys.length; i++) {
                if (keys[i].equals(placeholderKey)) {
                    replacement = (i < values.length) ? values[i] : "[?]";
                    break;
                }
            }
            matcher.appendReplacement(sb, Matcher.quoteReplacement(replacement));
        }
        matcher.appendTail(sb);
        return sb.toString();
    }

    public static void main(String[] args) {
        System.out.println("=== Template Filler Demo ===");

        String template = "Dear {name}, order {id} ships {date}.";
        String[] names = {"name", "id"};
        String[] values = {"Riya", "A07"};

        System.out.println("Template: " + template);
        System.out.println("Supplied parameters: {name -> Riya, id -> A07}");

        String result = fillTemplate(template, names, values);
        System.out.println("\nFilled Template Output:");
        System.out.println(result);
    }
}
