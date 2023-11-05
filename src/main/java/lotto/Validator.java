package lotto;

import static lotto.constants.Value.FIRST_CHARACTER;
import static lotto.constants.Value.MAX_LOTTO_NUMBER;
import static lotto.constants.Value.MIN_LOTTO_NUMBER;
import static lotto.constants.Value.THOUSAND;
import static lotto.constants.Error.NOT_NUMBER_ERROR;
import static lotto.constants.Error.NOT_POSITIVE_NUMBER_ERROR;
import static lotto.constants.Error.RANGE_ERROR;
import static lotto.constants.Error.REMAINDER_ERROR;
import static lotto.constants.Error.ZERO_INCLUSION_ERROR;

public class Validator {

    public static void checkNumber(String userInput) {
        try {
            Integer.parseInt(userInput);
        } catch (NumberFormatException ex) {
            throw new IllegalArgumentException(NOT_NUMBER_ERROR.getMessage());
        }
    }

    public static void checkThousands(Integer userInput) {
        if ((userInput % THOUSAND.get()) != 0) {
            throw new IllegalArgumentException(REMAINDER_ERROR.getMessage());
        }
    }

    public static void checkZero(String userInput) {
        if (userInput.charAt(FIRST_CHARACTER.get()) == '0') {
            throw new IllegalArgumentException(ZERO_INCLUSION_ERROR.getMessage());
        }
    }

    public static void checkPositiveNumber(Integer number) {
        if (number <= 0) {
            throw new IllegalArgumentException(NOT_POSITIVE_NUMBER_ERROR.getMessage());
        }
    }

    public static void checkRange(Integer userInput) {
        if ((userInput < MIN_LOTTO_NUMBER.get()) || (userInput > MAX_LOTTO_NUMBER.get())) {
            throw new IllegalArgumentException(RANGE_ERROR.getMessage());
        }
    }
}
