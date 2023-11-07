package validate;

import static constants.LottoErrorMessageConstants.*;

public class InputValidation {
    public static void validateNumericInput(String input) {
        if (!input.matches("^[0-9]+$")){
            throw new IllegalArgumentException(ERROR_MESSAGE_NON_NUMERIC_VALUE);
        }
    }

    public static void validateNonZeroInput(String input) {
        if(input.equals("0")) {
            throw new IllegalArgumentException(ERROR_MESSAGE_ZERO_NOT_ALLOWED);
        }
    }

    public static void validateLottoNumberCount(String[] list) {
        if (list.length != 6) {
            throw new IllegalArgumentException(ERROR_MESSAGE_INVALID_LOTTO_NUMBER_COUNT);
        }
    }
}
