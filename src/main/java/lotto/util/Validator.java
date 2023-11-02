package lotto.util;

import java.util.regex.Pattern;

public class Validator {
    public static void isEmpty(String input) {
        if (input == null || input.trim().isEmpty()) {
            throw new IllegalArgumentException(ExceptionMessage.INPUT_VALUE);
        }
    }

    public static void isNumeric(String input) {
        if (!Pattern.matches(Constants.NUMERIC_REGEX, input)) {
            throw new IllegalArgumentException(ExceptionMessage.INPUT_NUMERIC);
        }
    }

    public static void isThousandUnit(String input) {
        if (Integer.parseInt(input) % 1000 != 0) {
            throw new IllegalArgumentException(ExceptionMessage.INPUT_THOUSAND_UNIT);
        }
    }
}
