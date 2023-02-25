package numbers;

import java.util.*;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

public class Main {
    private static final Scanner in = new Scanner(System.in);
    public static void main(String[] args) {
        greet();

        System.out.print("Enter a request: ");
        List<String> input = getInput().stream().filter(s -> !s.isEmpty()).collect(Collectors.toList());
        while (input.isEmpty() || !input.get(0).equals("0")) {
            switch (input.size()) {
                case 0 -> System.out.println(supportedRequest());
                case 1 -> {
                    String number = input.get(0);
                    if (checkParameters(number, String.valueOf(Integer.MIN_VALUE)))
                        showProperties(number);
                }
                default -> {
                    if (checkParameters(input.get(0), input.get(1)))
                        showMultipleProperties(Long.parseLong(input.get(0)), Long.parseLong(input.get(1)));
                }
            }

            System.out.print("\nEnter a request: ");
            input = getInput();
        }
        System.out.println("Goodbye!");
    }

    private static void greet() {
        System.out.printf("""
                Welcome to Amazing Numbers!
                               
                %s
                """, supportedRequest());
    }

    private static String supportedRequest() {
        return """
                Supported requests:
                - enter a natural number to know its properties;
                - enter two natural numbers to obtain the properties of the list:
                  * the first parameter represents a starting number;
                  * the second parameter shows how many consecutive numbers are to be processed;
                - separate the parameters with one space;
                - enter 0 to exit.
                """;
    }

    private static List<String> getInput() {
        return Arrays.stream(in.nextLine().split(" ")).collect(Collectors.toList());
    }

    private static boolean checkParameters(String first, String second) {
        Pattern pattern = Pattern.compile("-?\\d+(\\.\\d+)?");
        boolean firstMatches = pattern.matcher(first).matches();
        boolean secondMatches = pattern.matcher(second).matches();
        if (!firstMatches || Long.parseLong(first) < 0) {
            System.out.println("The first parameter should be a natural number or zero.");
            return false;
        }
        if (!secondMatches || Long.parseLong(second) > Integer.MIN_VALUE && Long.parseLong(second) <= 0) {
            System.out.println("The second parameter should be a natural number.");
        }
        return true;
    }

    private static void showProperties(String number) {
        long num = Long.parseLong(number);
        System.out.printf("""
                Properties of %s
                        even: %b
                         odd: %b
                        buzz: %b
                        duck: %b
                 palindromic: %b
                      gapful: %b
                """, number, isEven(num), !isEven(num), isBuzz(num), isDuck(num), isPalindromic(num), isGapful(num));
    }

    private static void showMultipleProperties(long number, long count) {
        for (long i = 0; i < count; i++, number++) {
            System.out.println(getProperties(number));
        }
    }

    private static String getProperties(long number) {
        Map<String, Boolean> properties = fillProperties(number);
        List<String> trueProperties = filterProperties(properties);
        StringBuilder sb = new StringBuilder(String.format("%d is", number));
        for (int i = 0; i < trueProperties.size(); i++) {
            sb.append(" ").append(trueProperties.get(i));
            if (i < trueProperties.size() - 1) sb.append(",");
        }
        return sb.toString();
    }

    private static Map<String, Boolean> fillProperties(long number) {
        Map<String, Boolean> properties = new HashMap<>();
        properties.put("even", isEven(number));
        properties.put("odd", !isEven(number));
        properties.put("buzz", isBuzz(number));
        properties.put("duck", isDuck(number));
        properties.put("palindromic", isPalindromic(number));
        properties.put("gapful", isGapful(number));
        return properties;
    }

    private static List<String> filterProperties(Map<String, Boolean> properties) {
        List<String> trueProperties = new ArrayList<>();
        for (Map.Entry<String, Boolean> entry : properties.entrySet()) {
            if (entry.getValue()) trueProperties.add(entry.getKey());
        }
        return trueProperties;
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

    private static boolean isGapful(long number) {
        String str = String.valueOf(number);
        if (str.length() < 3) return false;

        int gap = Integer.parseInt(str.charAt(0) + str.substring(str.length() - 1));
        return number % gap == 0;
    }
 }
