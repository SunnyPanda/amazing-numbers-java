package numbers;

import java.util.List;
import java.util.regex.Pattern;

public final class Validators {

    private static final List<String> PROPERTIES = List.of("BUZZ", "DUCK", "PALINDROMIC", "GAPFUL", "SPY", "EVEN", "ODD");
    static boolean checkParameters(String first, String second) {
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

    static boolean isExistingProperty(String prop) {
        String property = prop.toUpperCase();
        if (!PROPERTIES.contains(property)) {
            System.out.printf("""
                    The property [%s] is wrong.
                    Available properties: [BUZZ, DUCK, PALINDROMIC, GAPFUL, SPY, EVEN, ODD]
                    """, property);
            return false;
        }
        return true;
    }
}
