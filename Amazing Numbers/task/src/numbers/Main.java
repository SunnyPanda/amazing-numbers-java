package numbers;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);

        System.out.println("Enter a natural number:");
        int number = in.nextInt();
        if (number <= 0) {
            System.out.println("This number is not natural!");
            return;
        }
        System.out.printf("""
                Properties of %d
                        even: %b
                         odd: %b
                        buzz: %b
                        duck: %b""", number, isEven(number), !isEven(number), isBuzz(number), isDuck(number));
    }

    private static boolean isEven(int number) {
        return number % 2 == 0;
    }

    private static boolean isBuzz(int number) {
        boolean divBy7 = number % 7 == 0;
        boolean endsWith7 = number % 10 == 7;
        return divBy7 || endsWith7;
    }

    private static boolean isDuck(int number) {
        return String.valueOf(number).lastIndexOf("0") > 0;
    }
 }
