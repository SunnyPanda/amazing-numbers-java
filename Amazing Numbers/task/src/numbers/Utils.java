package numbers;

public final class Utils {

    public static boolean isEven(long number) {
        return number % 2 == 0;
    }

    public static boolean isOdd(long number) {
        return !isEven(number);
    }

    public static boolean isBuzz(long number) {
        boolean divBy7 = number % 7 == 0;
        boolean endsWith7 = number % 10 == 7;
        return divBy7 || endsWith7;
    }

    public static boolean isDuck(long number) {
        return String.valueOf(number).lastIndexOf("0") > 0;
    }

    public static boolean isPalindromic(long number) {
        String str = String.valueOf(number);
        for (int i = 0, j = str.length() - 1; i <= j; i++, j--) {
            if (str.charAt(i) != str.charAt(j)) return false;
        }
        return true;
    }

    public static boolean isGapful(long number) {
        String str = String.valueOf(number);
        if (str.length() < 3) return false;

        int gap = Integer.parseInt(str.charAt(0) + str.substring(str.length() - 1));
        return number % gap == 0;
    }

    public static boolean isSpy(long number) {
        String str = String.valueOf(number);
        int sum = 0;
        int prod = 1;
        for (char ch : str.toCharArray()) {
            int num = Character.getNumericValue(ch);
            sum += num;
            prod *= num;
        }
        return sum == prod;
    }
}
