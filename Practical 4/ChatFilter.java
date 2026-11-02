import java.util.Scanner;

public class ChatFilter {

    public static String[] splitLine(String line) {
        if (line == null) return null;
        String[] parts = line.split(" ", 3);
        if (parts.length < 3) {
            return null;
        }
        return parts;
    }

    public static boolean containsKeyword(String message, String keyword) {
        if (message == null || keyword == null) return false;
        return message.toLowerCase().contains(keyword.toLowerCase());
    }

    public static void filterLogs(String[] logs, String keyword) {
        int matches = 0;
        StringBuilder report = new StringBuilder();

        for (String line : logs) {
            String[] parts = splitLine(line);
            if (parts == null) {

                continue;
            }

            String time = parts[0];
            String user = parts[1];
            String message = parts[2];

            if (containsKeyword(message, keyword)) {
                matches++;
                report.append(time).append(" ").append(user).append(": ").append(message).append("\n");
            }
        }

        System.out.println("Matches: " + matches);
        if (matches > 0) {
            System.out.print(report);
        }
    }

    public static void main(String[] args) {
        System.out.println("=== Chat-Log Filter ===");

        String[] logs = {
            "10:05 alice Hello there",
            "10:08 bob Good morning everyone",
            "malformed log line without proper parts",
            "10:12 charlie hello world, how are you?",
            "10:15 diana See you later"
        };

        String keyword = "hello";
        if (args.length > 0) {
            keyword = args[0];
        } else {
            System.out.println("(Using search keyword: \"" + keyword + "\")");
        }

        filterLogs(logs, keyword);
    }
}
