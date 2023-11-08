package lotto.service;

import lotto.exception.LottoException;

import java.util.Arrays;
import java.util.List;

import static lotto.exception.ErrorMessage.*;

public class Parser {
    private static final String DELIMITER = ",";

    // Default Constructor
    private Parser() {
    }

    // Utility Method
    public static int parseStringToInt(String input) {
        try {
            validateContainWhiteSpace(input);
            return Integer.parseInt(input);
        } catch (NumberFormatException exception) {
            throw LottoException.of(REQUEST_NOT_INTEGER, exception);
        }
    }

    public static List<Integer> splitByDelimiter(String input) {
        try {
            validateContainWhiteSpace(input);
            validateEndsWithDelimiter(input);
            return Arrays.stream(input.split(DELIMITER))
                    .map(Integer::parseInt)
                    .toList();
        } catch (NumberFormatException exception) {
            throw LottoException.of(REQUEST_NOT_INTEGER, exception);
        }
    }

    // Exception Handling Method
    private static void validateContainWhiteSpace(String input) {
        if (hasWhiteSpace(input)) {
            throw LottoException.from(CONTAIN_WHITESPACE);
        }
    }

    private static void validateEndsWithDelimiter(String input) {
        if (isEndsWithDelimiter(input)) {
            throw LottoException.from(ENDS_WITH_DELIMITER);
        }
    }

    // Validation Method
    private static boolean hasWhiteSpace(String input) {
        return input.chars().anyMatch(Character::isWhitespace);
    }

    private static boolean isEndsWithDelimiter(String input) {
        return input.endsWith(DELIMITER);
    }
}
