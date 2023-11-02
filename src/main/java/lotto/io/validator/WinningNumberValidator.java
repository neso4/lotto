package lotto.io.validator;

import java.util.Arrays;
import java.util.List;

import static lotto.exception.ExceptionMessage.InputException.WINNING_NUMBER_MUST_BE_SPLIT_BY_COMMA;

public class WinningNumberValidator extends InputValidator {
    private static final String COMMA = ",";

    @Override
    void validate(final String userInput) {
        validateInputHasSpace(userInput);
        inputSplitByComma(userInput)
                .forEach(this::validateInputElementIsNumeric);
    }

    private List<String> inputSplitByComma(final String userInput) {
        return Arrays.stream(userInput.split(COMMA))
                .toList();
    }

    private void validateInputElementIsNumeric(final String userInput) {
        try {
            Integer.parseInt(userInput);
        } catch (final NumberFormatException exception) {
            throw new IllegalArgumentException(WINNING_NUMBER_MUST_BE_SPLIT_BY_COMMA.message);
        }
    }
}
