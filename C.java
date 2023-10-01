import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        String pattern = reader.readLine();
        String string = reader.readLine();
        kmp(pattern, string);
    }

    public static void prefix(String pattern, int[] prefix) {
        int len = 0;
        int i = 1;
        prefix[0] = 0;

        while (i < pattern.length()) {
            if (pattern.charAt(i) == pattern.charAt(len)) {
                len++;
                prefix[i] = len;
                i++;
            } else {
                if (len != 0) {
                    len = prefix[len - 1];
                } else {
                    prefix[i] = len;
                    i++;
                }
            }
        }
    }

    public static void kmp(String pattern, String string) {
        int p = pattern.length();
        int n = string.length();
        int[] prefix = new int[p];
        prefix(pattern, prefix);

        int n1 = 0;  // Index for pattern
        int n2 = 0;  // Index for string
        StringBuilder result = new StringBuilder();

        while (n2 < n) {
            if (pattern.charAt(n1) == string.charAt(n2)) {
                n1++;
                n2++;
            }

            if (n1 == p) {
                // Pattern found at index n2 - n1
                result.append((n2 - n1)).append(" ");
                // Update n1 using the prefix table
                n1 = prefix[n1 - 1];
            } else if (n2 < n && pattern.charAt(n1) != string.charAt(n2)) {
                if (n1 != 0) {
                    n1 = prefix[n1 - 1];
                } else {
                    n2++;
                }
            }
        }

        // Print the result
        System.out.println(result.toString().trim());
    }
}
