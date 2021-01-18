/*
Java 15
This program counts and prints an arbitrary number of lines of input.
 */

import java.util.Scanner;


public class endOfFile {

    public static class Solution {

        public static void main(String[] args) {
            Scanner scanner = new Scanner(System.in);
            int i = 1;
            while (scanner.hasNext()) {
                System.out.println(i + " " + scanner.nextLine());
                i += 1;
            }
        }
    }


}
