import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
    public static void main(String[] args) throws IOException {
        run();
    }

    public static void run() throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(br.readLine());
        int[] arr = new int[n];

        StringTokenizer st = new StringTokenizer(br.readLine());
        for (int i = 0; i < n; i++) {
            arr[i] = Integer.parseInt(st.nextToken());
        }

        int[] result = solve(arr);
        printResult(result);
    }

    public static int[] solve(int[] arr) {
        int n = arr.length;
        int[] dp = new int[n];
        dp[0] = 0;
        int[] arr2 = new int[n];
        int len = 1;

        for (int i = 1; i < n; i++) {
            int index = binSearch(dp, arr, len, arr[i]);
            arr2[i] = index == -1 ? -1 : dp[index];

            if (index + 1 == len) {
                dp[len++] = i;
            } else if (arr[i] >= arr[dp[index + 1]]) {
                dp[index + 1] = i;
            }
        }

        int[] res = new int[len];
        int idx = dp[len - 1];
        for (int i = len - 1; i >= 0; i--) {
            res[i] = idx + 1;
            idx = arr2[idx];
        }

        return res;
    }

    public static int binSearch(int[] dp, int[] arr, int len, int val) {
        int left = 0;
        int right = len - 1;

        while (left <= right) {
            int mid = (left + right) >>> 1;
            if (arr[dp[mid]] >= val) {
                left = mid + 1;
            } else {
                right = mid - 1;
            }
        }

        return right;
    }

    public static void printResult(int[] result) {
        System.out.println(result.length);
        for (int i = 0; i < result.length; i++) {
            System.out.print(result[i] + " ");
        }
    }
}
