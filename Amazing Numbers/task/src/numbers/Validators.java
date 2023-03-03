package numbers;

import java.util.List;
import java.util.regex.Pattern;

public final class Validators {

    private static final List<String> PROPERTIES = List.of("BUZZ", "DUCK", "PALINDROMIC", "GAPFUL", "SPY", "EVEN", "ODD", "SQUARE", "SUNNY");
    private static final String WRONG_PROPERTY_MESSAGE = "The property [%s] is wrong.\n";
    private static final String WRONG_PROPERTIES_MESSAGE = "The properties [%s, %s] are wrong.\n";
    private static final String AVAILABLE_PROPERTIES_MESSAGE;
    static {
        StringBuilder sb = new StringBuilder("Available properties: [");
        for (String str : PROPERTIES) {
            sb.append(str).append(", ");
        }
        sb.replace(sb.length() - 2, sb.length() - 1, "]");
        AVAILABLE_PROPERTIES_MESSAGE = sb.toString();
    }
    private static final String EXCLUSIVE_PROPERTIES_MESSAGE = """
            The request contains mutually exclusive properties: [%s, %s]
            There are no numbers with these properties.
            """;

    static boolean checkInput(List<String> parameters) {
        boolean result;
        switch (parameters.size()) {
            case 1 -> result = checkOneParameter(parameters.get(0));
            case 2 -> result = checkTwoParameters(parameters.get(0), parameters.get(1));
            case 3 -> result = checkThreeParameters(parameters.get(0), parameters.get(1), parameters.get(2));
            case 4 -> result = checkFourParameters(parameters.get(0), parameters.get(1), parameters.get(2), parameters.get(3));
            default -> {
                System.out.println("Too many parameters");
                result = false;
            }
        }
        return result;
    }

    static boolean checkOneParameter(String parameter) {
        Pattern pattern = Pattern.compile("-?\\d+(\\.\\d+)?");
        if (!pattern.matcher(parameter).matches() || Long.parseLong(parameter) < 0) {
            System.out.println("The first parameter should be a natural number or zero.");
            return false;
        }
        return true;
    }

    static boolean checkTwoParameters(String parameter1, String parameter2) {
        if (!checkOneParameter(parameter1))
            return false;

        Pattern pattern = Pattern.compile("-?\\d+(\\.\\d+)?");
        if (!pattern.matcher(parameter2).matches() || Long.parseLong(parameter2) <= 0) {
            System.out.println("The second parameter should be a natural number.");
            return false;
        }
        return true;
    }

    static boolean checkThreeParameters(String parameter1, String parameter2, String parameter3) {
        if (!checkTwoParameters(parameter1, parameter2))
            return false;
        if (isNonExistingProperty(parameter3)) {
            System.out.printf(WRONG_PROPERTY_MESSAGE, parameter3.toUpperCase());
            System.out.println(AVAILABLE_PROPERTIES_MESSAGE);
            return false;
        }
        return true;
    }

    static boolean checkFourParameters(String parameter1, String parameter2, String parameter3, String parameter4) {
        if (!checkTwoParameters(parameter1, parameter2))
            return false;

        if (isNonExistingProperty(parameter3) && isNonExistingProperty(parameter4)) {
            System.out.printf(WRONG_PROPERTIES_MESSAGE, parameter3.toUpperCase(), parameter4.toUpperCase());
            System.out.println(AVAILABLE_PROPERTIES_MESSAGE);
            return false;
        } else if (isNonExistingProperty(parameter3)) {
            printNonExistingPropertyMessage(parameter3);
            return false;
        }
        else if (isNonExistingProperty(parameter4)) {
            printNonExistingPropertyMessage(parameter4);
            return false;
        }
        else if (isExclusive(parameter3, parameter4)) {
            System.out.printf(EXCLUSIVE_PROPERTIES_MESSAGE, parameter3.toUpperCase(), parameter4.toUpperCase());
            return false;
        }
        return true;
    }

    private static boolean isNonExistingProperty(String property) {
        String prop = property.toUpperCase();
        return !PROPERTIES.contains(prop);
    }

    private static void printNonExistingPropertyMessage(String prop) {
        System.out.printf(WRONG_PROPERTY_MESSAGE, prop.toUpperCase());
        System.out.println(AVAILABLE_PROPERTIES_MESSAGE);
    }
    private static boolean isExclusive(String p1, String p2) {
        switch (p1) {
            case "even" -> {
                if (p2.equals("odd")) return true;
            }
            case "odd" -> {
                if (p2.equals("even")) return true;
            }
            case "duck" -> {
                if (p2.equals("spy")) return true;
            }
            case "spy" -> {
                if (p2.equals("duck")) return true;
            }
            case "sunny" -> {
                if (p2.equals("square")) return true;
            }
            case "square" -> {
                if (p2.equals("sunny")) return true;
            }
        }
        return false;
    }
}
