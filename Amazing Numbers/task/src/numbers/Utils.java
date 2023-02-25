package numbers;

public final class Utils {

    static boolean isEven(String number) {
        return Long.parseLong(number) % 2 == 0;
    }

    static boolean isOdd(String number) {
        return !isEven(number);
    }

    static boolean isBuzz(String number) {
        long num = Long.parseLong(number);
        boolean divBy7 = num % 7 == 0;
        boolean endsWith7 = num % 10 == 7;
        return divBy7 || endsWith7;
    }

    static boolean isDuck(String number) {
        return number.lastIndexOf("0") > 0;
    }

    static boolean isPalindromic(String number) {
        for (int i = 0, j = number.length() - 1; i <= j; i++, j--) {
            if (number.charAt(i) != number.charAt(j))
                return false;
        }
        return true;
    }

    static boolean isGapful(String number) {
        if (number.length() < 3) return false;

        int gap = Integer.parseInt(number.charAt(0) + number.substring(number.length() - 1));
        return Long.parseLong(number) % gap == 0;
    }

    static boolean isSpy(String number) {
        int sum = 0;
        int prod = 1;
        for (char ch : number.toCharArray()) {
            int num = Character.getNumericValue(ch);
            sum += num;
            prod *= num;
        }
        return sum == prod;
    }
}
