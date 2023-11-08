package lotto.model;

import static lotto.constants.LottoConstants.MAX_RANGE;
import static lotto.constants.LottoConstants.MIN_RANGE;

import lotto.exceptionMessages.ExceptionMessages;

public class BonusNumber {
    private final int bonusNumber;

    public BonusNumber(int bonusNumber) {
        validate(bonusNumber);
        this.bonusNumber = bonusNumber;
    }

    private void validate(int bonusNumber) {
        if (isInvalidRange(bonusNumber)) {
            throw new IllegalArgumentException(ExceptionMessages.EXCEPTION_NUMBER_RANGE.getMessage());
        }
    }

    private boolean isInvalidRange(int bonusNumber) {
        return bonusNumber < MIN_RANGE || bonusNumber > MAX_RANGE;
    }

    public int getBonusNumber() {
        return bonusNumber;
    }
}
