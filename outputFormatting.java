/*
java 15

This programme takes in a string (<= 15 characters) and an integer (<= 999)
and prints the string with additional whitespace to fill 15 characters and adds zeros in front of the integer
so that the integer always contains 3 characters.

example: "java 10" will result in "java           010"
 */

import java.util.Scanner;

public class outputFormatting {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        String string = scanner.next();
        int number = scanner.nextInt();
        int lengthString = string.length();
        String zeros = "";
        if (number <= 9) {
            zeros = "00";}
        else if (number <= 99) {
            zeros = "0";}
        System.out.printf(string + " ".repeat(15 - lengthString) + zeros + "%d", number);
    }
}
