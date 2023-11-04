package lotto.validator.input;

import java.util.List;
import lotto.validator.input.exception.InputExceptionMessage;

public class InputCommonValidator {

    private InputCommonValidator() {
    }

    public static void validateMultiple(final List<String> inputs) {
        inputs.forEach(InputCommonValidator::validateNumber);
    }

    public static void validateSingle(final String input) {
        validateNumber(input);
    }

    private static void validateNumber(final String input) {
        try {
            Integer.parseInt(input);
        } catch (NumberFormatException e) {
            throw InputExceptionMessage.INVALID_NUMBER_FORMAT.create();
        }
    }
}
