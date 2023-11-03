package validator;

import static constant.ExceptionMessage.DUPLICATE_NUMBER;
import static constant.ExceptionMessage.ERROR_MESSAGE;
import static constant.ExceptionMessage.NUMERIC_REQUIRED;
import static constant.ExceptionMessage.OUT_OF_RANGE_NUMBER;

import lotto.Lotto;

public class BonusNumberValidator {
    private BonusNumberValidator() {
    }

    public static void isNumeric(String bonusNumber) {
        try {
            int integerBonusNumber = Integer.parseInt(bonusNumber);
        } catch (NumberFormatException e) {
            throw new IllegalArgumentException(
                    ERROR_MESSAGE.getMessage() + NUMERIC_REQUIRED.getMessage()
            );
        }
    }

    public static void inRange(String bonusNumber) {
        int integerBonusNumber = Integer.parseInt(bonusNumber);
        if (integerBonusNumber < 1 || integerBonusNumber > 45) {
            throw new IllegalArgumentException(
                    ERROR_MESSAGE.getMessage() + OUT_OF_RANGE_NUMBER.getMessage()
            );
        }
    }

    public static void duplicate(Lotto winningNumbers, String bonusNumber) {
        int integerBonusNumber = Integer.parseInt(bonusNumber);
        if (winningNumbers.getNumbers().contains(integerBonusNumber)) {
            throw new IllegalArgumentException(
                    ERROR_MESSAGE.getMessage() + DUPLICATE_NUMBER.getMessage()
            );
        }
    }
}
