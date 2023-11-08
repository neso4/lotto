package lotto.exception;

import static lotto.exception.message.ErrorMessage.NEGATIVE_OR_ZERO_INPUT_ERROR_MESSAGE;

public class NegativeOrZeroInputException extends IllegalArgumentException {

    public NegativeOrZeroInputException() {
        super(NEGATIVE_OR_ZERO_INPUT_ERROR_MESSAGE.getMessage());
    }
}
