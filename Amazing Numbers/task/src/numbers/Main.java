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

        String oddOrEven = number % 2 == 0 ? "Even" : "Odd";
        boolean divBy7 = number % 7 == 0;
        boolean endsWith7 = number % 10 == 7;
        boolean isBuzz = divBy7 || endsWith7;

        System.out.printf("This number is %s.\n", oddOrEven);
        System.out.printf("It is%sa Buzz number.\n", isBuzz ? " " : " not ");

        System.out.println("Explanation:");
        if (divBy7 && endsWith7) System.out.printf("%d is divisible by 7 and ends with 7.\n", number);
        else if (divBy7) System.out.printf("%d is divisible by 7.\n", number);
        else if (endsWith7) System.out.printf("%d is ends with 7.\n", number);
        else System.out.printf("%d is neither divisible by 7 nor does it end with 7.\n", number);
    }
}
