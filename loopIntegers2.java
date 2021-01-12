/*
java 15

this programme reads an integer from STDIN, multiplies it with integers from 1 to 10 and prints calculation and result

example:
input       2
output      2 x 1 = 2
            2 x 2 = 4
            2 x 3 = 6
            2 x 4 = 8
            2 x 5 = 10
            2 x 6 = 12
            2 x 7 = 14
            2 x 8 = 16
            2 x 9 = 18
            2 x 10 = 20
 */

import java.util.Scanner;
import java.util.Arrays;

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
