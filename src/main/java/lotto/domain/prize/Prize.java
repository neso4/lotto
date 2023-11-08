package lotto.domain.prize;

import lotto.domain.lottery.Lotto;
import lotto.domain.lottery.NumberChecker;
import lotto.exception.LottoException;

import static lotto.exception.ErrorMessage.BONUS_NUMBER_DUPLICATED;
import static lotto.exception.ErrorMessage.NUMBER_OUT_OF_RANGE;

public final class Prize extends NumberChecker {
    private final Lotto prizeNumbers;
    private final int bonusNumber;

    // Constructor
    private Prize(
            Lotto prizeNumbers,
            final int bonusNumber
    ) {
        this.prizeNumbers = prizeNumbers;

        validateNumberRange(bonusNumber);
        validateDuplicatedBonusNumber(bonusNumber);

        this.bonusNumber = bonusNumber;
    }

    // Static Factory Method
    public static Prize of(
            Lotto prizeNumbers,
            final int bonusNumber
    ) {
        return new Prize(prizeNumbers, bonusNumber);
    }

    // Utility Method
    public boolean isPrizeNumber(final int comparableNumber) {
        return prizeNumbers.hasNumber(comparableNumber);
    }

    public boolean isBonusNumber(final int comparableNumber) {
        return bonusNumber == comparableNumber;
    }

    // Exception Handling Method
    private void validateDuplicatedBonusNumber(final int number) {
        if (prizeNumbers.hasNumber(number)) {
            throw LottoException.from(BONUS_NUMBER_DUPLICATED);
        }
    }

    private void validateNumberRange(final Integer number) {
        if (isOutOfRange(number)) {
            throw LottoException.from(NUMBER_OUT_OF_RANGE);
        }
    }
}
