package numbers;

import java.text.DecimalFormat;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);

        greet();

        System.out.print("Enter a request: ");
        long number = in.nextLong();
        while (number != 0) {
            if (number < 0)
                System.out.println("The first parameter should be a natural number or zero.");
            else showProperties(number);

            System.out.print("Enter a request: ");
            number = in.nextInt();
        }
        System.out.println("Goodbye!");
    }

    private static void greet() {
        System.out.println("""
                Welcome to Amazing Numbers!
                                
                Supported requests:
                - enter a natural number to know its properties;
                - enter 0 to exit.
                """);
    }

    private static void showProperties(long number) {
        DecimalFormat df = new DecimalFormat("###,###,###");
        System.out.printf("""
                \nProperties of %s
                        even: %b
                         odd: %b
                        buzz: %b
                        duck: %b
                 palindromic: %b
                 \n""", df.format(number), isEven(number), !isEven(number), isBuzz(number), isDuck(number), isPalindromic(number));
    }
    private static boolean isEven(long number) {
        return number % 2 == 0;
    }

    private static boolean isBuzz(long number) {
        boolean divBy7 = number % 7 == 0;
        boolean endsWith7 = number % 10 == 7;
        return divBy7 || endsWith7;
    }

    private static boolean isDuck(long number) {
        return String.valueOf(number).lastIndexOf("0") > 0;
    }

    private static boolean isPalindromic(long number) {
        String str = String.valueOf(number);
        for (int i = 0, j = str.length() - 1; i <= j; i++, j--) {
            if (str.charAt(i) != str.charAt(j)) return false;
        }
        return true;
    }
 }
