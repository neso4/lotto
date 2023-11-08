package lotto.exception;

import static lotto.exception.message.ErrorMessage.LARGER_THAN_MAX_LOTTO_NUMBER_ERROR_MESSAGE;

public class ExceedsMaxLottoNumberException extends IllegalArgumentException {

    public ExceedsMaxLottoNumberException() {
        super(LARGER_THAN_MAX_LOTTO_NUMBER_ERROR_MESSAGE.getMessage());
    }
}
