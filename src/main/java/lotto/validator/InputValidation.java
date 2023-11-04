package lotto.validator;

import java.util.List;
import lotto.constants.Messages;

public class InputValidation {

    public static void validateNumeric(String value) {
        if (isNotNumeric(value)) {
            throw new IllegalArgumentException(Messages.ERROR_INVALID_NUMERIC_INPUT);
        }
    }

    public static void validateAllNumeric(List<String> values) {
        if (containsNonNumericValue(values)) {
            throw new IllegalArgumentException(Messages.ERROR_INVALID_NUMERIC_INPUT);
        }
    }

    private static boolean isNotNumeric(String value) {
        try {
            Integer.parseInt(value);
            return false;
        } catch (NumberFormatException e) {
            return true;
        }
    }

    private static boolean containsNonNumericValue(List<String> values) {
        return values.stream().anyMatch(InputValidation::isNotNumeric);
    }
}
