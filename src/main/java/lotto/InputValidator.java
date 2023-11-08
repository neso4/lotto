package lotto;

import static lotto.Constants.*;

public class InputValidator {

    public static void validateIsNotBlank(String input) {
        if (input.isBlank()) {
            throw new IllegalArgumentException(ERROR_BLANK);
        }
    }

    public static void validateIsNumber(String input) {
        if (!input.matches("^[1-9]\\d*$")) {
            throw new IllegalArgumentException(ERROR_NOT_NUMBER);
        }
    }

    public static void validateIsNumber(String[] input) {
        for (String s : input) {
            if (!s.matches("^[1-9]\\d*$")) {
                throw new IllegalArgumentException(ERROR_NOT_NUMBER);
            }
        }
    }

    public static void validateIsMultipleOf(String input, int target) {
        if (Integer.parseInt(input) % target != 0) {
            throw new IllegalArgumentException(ERROR_NOT_MULTIPLE_1000);
        }
    }
}
