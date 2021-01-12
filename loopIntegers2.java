/*
java 15

this program takes three integers a, b and n and calculates the following list of numbers:
a + 2ˆ0 * b, a + 2ˆ0 * b + 2^1 * b, ... ,a + 2ˆ0 * b + 2^1 * b + ... + 1ˆn-1 * b
the result is printed as a line of space separated numbers
 */

import java.util.Scanner;

public class loopIntegers2 {

    public static void calculate(int a, int b, int n) {
        double[] results = new double[n];
        double previousResult = a;
        for (int i = 0; i < n; i++) {
            double currentResult = Math.pow(2, i) * b;
            results[i] = previousResult + currentResult;
            previousResult = results[i];
            }
        String[] results_str = new String[n];
        for (int i = 0; i < n; i++) {
            results_str[i] = Integer.toString((int) results[i]);
        }
        String str_result = String.join(" ", results_str);
        System.out.println(str_result);
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int nQueries = scanner.nextInt();
        for (int i = 0; i < nQueries; i++) {
            int a = scanner.nextInt();
            int b = scanner.nextInt();
            int n = scanner.nextInt();
            calculate(a, b, n);
        }
    }
}
