/*
Java 15
This program calculates the area of a parallelogram using a static initialization block for part of the code.
If the breadth or height are negative an error-message is printed.
 */

import java.util.Scanner;

public class AreaParallelogram {

    static public boolean flag = true;
    static int breadthOfParallelogram, heightOfParallelogram;

    static {
        Scanner scanner = new Scanner(System.in);
        breadthOfParallelogram = scanner.nextInt();
        heightOfParallelogram = scanner.nextInt();
        if (breadthOfParallelogram <= 0 || heightOfParallelogram <= 0) {
            flag = false;
            System.out.println("java.lang.Exception: Breadth and height must be positive");;
        }
    }

     public static void main (String[] args) {
        if (flag) {
            System.out.println(breadthOfParallelogram * heightOfParallelogram);
        }
     }
}
