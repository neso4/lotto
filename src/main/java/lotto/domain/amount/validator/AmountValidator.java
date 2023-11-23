package lotto.domain.amount.validator;

import lotto.domain.constant.Constant;
import lotto.exception.CustomIllegalStateAmountException;
import lotto.exception.amount.AmountExceptionStatus;

public class AmountValidator {

    private static final AmountValidator AMOUNT_VALIDATOR = new AmountValidator();

    private AmountValidator() {
    }

    public static void validateAmount(final int amount) {
        AMOUNT_VALIDATOR.validateAmountIsPositive(amount);
        AMOUNT_VALIDATOR.validateAmountIsOutOfRange(amount);
    }

    private void validateAmountIsPositive(final int amount) {
        if (isPositive(amount)) {
            throw new CustomIllegalStateAmountException(AmountExceptionStatus.AMOUNT_IS_NOT_POSITIVE);
        }
    }

    private boolean isPositive(final int amount) {
        return Constant.ZERO.getConstant() < amount;
    }

    private void validateAmountIsOutOfRange(final int amount) {
        if (isOutOfRange(amount)) {
            throw new CustomIllegalStateAmountException(AmountExceptionStatus.AMOUNT_IS_OUT_OF_RANGE);
        }
    }

    private boolean isOutOfRange(final int amount) {
        return Constant.ALLOWED_MAXIMUM_AMOUNT.getConstant() < amount;
    }
}
