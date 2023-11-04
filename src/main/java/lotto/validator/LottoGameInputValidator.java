package lotto.validator;

import lotto.message.ErrorMessage;

public class LottoGameInputValidator {
    public long parseLong(String requestNumber) {
        long number;
        try {
            number = Long.parseLong(requestNumber);
        } catch (NumberFormatException e) {
            throw new IllegalArgumentException(ErrorMessage.INVALID_AMOUNT_FORMAT.getMessage());
        }
        return number;
    }

    public void validatePositiveNumber(long number) {
        if (number <= 0) {
            throw new IllegalArgumentException(ErrorMessage.AMOUNT_LESS_THAN_ZERO.getMessage());
        }
    }
}
