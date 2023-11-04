package lotto.util;

import static lotto.exception.ExceptionMessage.BLANK_EXCEPTION;
import static lotto.exception.ExceptionMessage.NO_INTEGER_EXCEPTION;

import java.util.ArrayList;
import java.util.List;

public class StringUtil {

    public static int parseInt(String value) {
        try {
            validateBlank(value);
            return Integer.parseInt(value);
        } catch (NumberFormatException e) {
            throw new IllegalArgumentException(NO_INTEGER_EXCEPTION.getMessage());
        }
    }

    public static List<Integer> parseComma(String comma) {
        List<Integer> result = new ArrayList<>();
        String[] split = comma.split(",", -1);

        for (String splitValue : split) {
            validateBlank(splitValue);
            result.add(parseInt(splitValue));
        }

        return result;
    }

    private static void validateBlank(String blank) {
        if (blank.isBlank()) {
            throw new IllegalArgumentException(BLANK_EXCEPTION.getMessage());
        }
    }
}
