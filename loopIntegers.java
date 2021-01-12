/*
java 15

this program reads an integer from STDIN, multiplies it with integers from 1 to 10 and prints calculation and result

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

public class loopIntegers {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int input_number = scanner.nextInt();
        for (int i = 1; i < 11; i++) {
            System.out.println(input_number + " x " + i + " = " + input_number * i);
        }
    }
}
