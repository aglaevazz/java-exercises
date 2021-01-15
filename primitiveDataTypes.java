/*
Java 15
This program arbitrarily large/small numbers and determines whether it can be stored as a byte, short, int or long.
 */

import java.util.Scanner;

public class primitiveDataTypes {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int n = scanner.nextInt();
        for (int i = 0; i < n; i++) {
             try {
                 long number = scanner.nextLong();
                 System.out.println(number + " can be fitted in:");
                 if (number >= -128 && number <= 127) {
                     System.out.println("* byte");
                 }
                 if (number >= -32768 && number <= 32767) {
                     System.out.println("* short");
                 }
                 if (number >= Math.pow(-2, 31) && number <= Math.pow(2, 31) - 1) {
                     System.out.println("* int");
                 }
                 System.out.println("* long");
            } catch (Exception e) {
                 System.out.println(scanner.next() + " can't be fitted anywhere.");
             }
        }

    }

}
