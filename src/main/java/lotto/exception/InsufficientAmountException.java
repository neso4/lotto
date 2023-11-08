package lotto.exception;

import static lotto.exception.message.ErrorMessage.LOTTO_MIN_PRICE_ERROR_MESSAGE;

public class InsufficientAmountException extends IllegalArgumentException {

    public InsufficientAmountException() {
        super(LOTTO_MIN_PRICE_ERROR_MESSAGE.getMessage());
    }
}
