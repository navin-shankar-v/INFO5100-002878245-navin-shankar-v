import java.util.regex.Pattern;
import java.util.regex.Matcher;

import static java.lang.System.out;

public class Exercise4 {
    public static void main(String[] args) {        // Define regular expression patterns
        String pattern1 = "\\b\\d{3}\\b"; // Matches 3 digits
        String pattern2 = "\\b[A-Z][a-z]+\\b"; // Matches a capitalized word
        String pattern3 = "\\b\\w+@\\w+\\.\\w+\\b"; // Matches an email address
        String pattern4 = "\\b\\d{4}-\\d{2}-\\d{2}\\b"; // Matches a date in yyyy-mm-dd format
        String pattern5 = "\\b(https?|ftp)://\\S+\\b"; // Matches URLs starting with http, https, or ftp

        // Test subject strings
        String subject1 = "123"; // Positive case for pattern1
        String subject2 = "Test"; // Negative case for pattern1

        String subject3 = "Navin"; // Positive case for pattern2
        String subject4 = "navin"; // Negative case for pattern2

        String subject5 = "navin@gmail.com"; // Positive case for pattern3
        String subject6 = "navin.gmail.com"; // Negative case for pattern3

        String subject7 = "2015-12-18"; // Positive case for pattern4
        String subject8 = "12-18-2026"; // Positive case for pattern4

        String subject9 = "http://portfolio.com"; // Positive case for pattern5
        String subject10 = "invalid-url"; // Negative case for pattern5

        // Test the regular expression patterns
        testPattern(pattern1, subject1);
        testPattern(pattern1, subject2);

        testPattern(pattern2, subject3);
        testPattern(pattern2, subject4);

        testPattern(pattern3, subject5);
        testPattern(pattern3, subject6);

        testPattern(pattern4, subject7);
        testPattern(pattern4, subject8);

        testPattern(pattern5, subject9);
        testPattern(pattern5, subject10);
    }

    public static void testPattern(String pattern, String subject) {
        out.println("Testing pattern: " + pattern);
        out.println("Subject string: " + subject);

        Pattern regexPattern = Pattern.compile(pattern);
        Matcher matcher = regexPattern.matcher(subject);

        if (matcher.find()) {
            out.println("Match found: " + matcher.group());
            out.println("Test Case Passed");
        } else {
            out.println("No match found.");
            out.println("Test Case Failed");
        }

        out.println();
    }
}
