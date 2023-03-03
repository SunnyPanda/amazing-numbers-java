package numbers;

import java.util.*;
import java.util.function.Function;
import java.util.stream.Collectors;

import static numbers.Utils.*;
import static numbers.Validators.*;

public class Main {
    private static final Scanner in = new Scanner(System.in);
    private static final Map<String, Function<String, Boolean>> functionMap = new HashMap<>();
    static {
        functionMap.put("even", Utils::isEven);
        functionMap.put("odd", Utils::isOdd);
        functionMap.put("buzz", Utils::isBuzz);
        functionMap.put("duck", Utils::isDuck);
        functionMap.put("palindromic", Utils::isPalindromic);
        functionMap.put("gapful", Utils::isGapful);
        functionMap.put("spy", Utils::isSpy);
        functionMap.put("square", Utils::isSquare);
        functionMap.put("sunny", Utils::isSunny);
    }
    public static void main(String[] args) {
        greet();

        System.out.print("Enter a request: ");
        List<String> input = getInput().stream().filter(s -> !s.isEmpty()).collect(Collectors.toList());
        while (input.isEmpty() || !input.get(0).equals("0")) {
            if (checkInput(input))
                switch (input.size()) {
                    case 0 -> System.out.println(supportedRequest());
                    case 1 -> showProperties(input.get(0));
                    case 2 -> showMultipleProperties(input.get(0), input.get(1));
                    case 3 -> showSpecificProperty(input.get(0), input.get(1), input.get(2), null);
                    case 4 -> showSpecificProperty(input.get(0), input.get(1), input.get(2), input.get(3));
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
                  * the second parameters show how many consecutive numbers are to be processed;
                - two natural numbers and two properties to search for;
                - separate the parameters with one space;
                - enter 0 to exit.
                """;
    }

    private static List<String> getInput() {
        return Arrays.stream(in.nextLine().split(" ")).collect(Collectors.toList());
    }

    private static void showProperties(String number) {
        System.out.printf("""
                Properties of %s
                        even: %b
                         odd: %b
                        buzz: %b
                        duck: %b
                 palindromic: %b
                      gapful: %b
                         spy: %b
                      square: %b
                       sunny: %b
                """, number, isEven(number), isOdd(number), isBuzz(number), isDuck(number), isPalindromic(number),
                isGapful(number), isSpy(number), isSquare(number), isSunny(number));
    }

    private static void showSpecificProperty(String number, String count, String prop1, String prop2) {
        long num = Long.parseLong(number);
        List<String> filteredNumbers = new ArrayList<>();
        for (long i = 0; i < Long.parseLong(count); num++) {
            String newNumber = String.valueOf(num);
            boolean oneCondition = functionMap.get(prop1.toLowerCase()).apply(newNumber);
            Function<Boolean, Boolean> twoConditions = cond -> cond && functionMap.get(prop2.toLowerCase()).apply(newNumber);
            boolean condition = Objects.isNull(prop2) ? oneCondition : twoConditions.apply(oneCondition);
            if (condition) {
                filteredNumbers.add(newNumber);
                i++;
            }
        }
        showMultipleProperties(filteredNumbers);
    }

    private static void showMultipleProperties(List<String> numbers) {
        numbers.forEach(n -> System.out.println(getProperties(Long.parseLong(n))));
    }

    private static void showMultipleProperties(String number, String count) {
        long num = Long.parseLong(number);
        for (long i = 0; i < Long.parseLong(count); i++, num++) {
            System.out.println(getProperties(num));
        }
    }

    private static String getProperties(long number) {
        List<String> trueProperties = filterProperties(String.valueOf(number));

        StringBuilder sb = new StringBuilder(String.format("%s is", number));
        for (int i = 0; i < trueProperties.size(); i++) {
            sb.append(" ").append(trueProperties.get(i));
            if (i < trueProperties.size() - 1) sb.append(",");
        }
        return sb.toString();
    }

    private static List<String> filterProperties(String number) {
        List<String> trueProperties = new ArrayList<>();
        for (Map.Entry<String, Function<String, Boolean>> entry : functionMap.entrySet()) {
            if (entry.getValue().apply(number)) trueProperties.add(entry.getKey());
        }
        return trueProperties;
    }




 }
