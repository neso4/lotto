package lotto.validator;

import static lotto.exception.ErrorType.INVALID_NUMBER_FORMAT;

import java.util.regex.Pattern;

public class NumericValidator {

    private static final Pattern NUMERIC_PATTERN = Pattern.compile("\\d+");

    public static void validate(final String stringNumber) {
        if (isNullOrEmpty(stringNumber) || !isNumeric(stringNumber)) {
            throw new IllegalArgumentException(INVALID_NUMBER_FORMAT.getMessage());
        }
    }

    private static boolean isNullOrEmpty(final String stringNumber) {
        return stringNumber == null || stringNumber.isEmpty();
    }

    private static boolean isNumeric(final String stringNumber) {
        return NUMERIC_PATTERN.matcher(stringNumber).matches();
    }
}

