/*
java 15

this programme reads and integer from STDIN, multiplies it with integers from 1 to 10 and prints calculation and result
 */

import java.util.Scanner;

public class loopIntegers {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int N = scanner.nextInt();
        for (int i = 1; i < 11; i++) {
            System.out.println(N + " x " + i + " = " + N * i);
        }
    }
}
