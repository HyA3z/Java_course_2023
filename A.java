import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        long a, b;
        a = scanner.nextLong();
        b = scanner.nextLong();
        System.out.print(GCD(a, b));
        scanner.close();
    }
    public static long GCD(long a, long b){
        while(b != 0){
            long temp = b;
            b = a % b;
            a = temp;
        }
        return a;
    }
}
