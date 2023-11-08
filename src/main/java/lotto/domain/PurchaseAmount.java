package lotto.domain;

import lotto.constant.ErrorMessage;
import lotto.constant.LottoConstant;

public class PurchaseAmount {
    private final int amount;
    private final int lottoCount;

    public PurchaseAmount(String amount) {
        validate(amount);
        this.amount = toInt(amount);
        this.lottoCount = this.amount / LottoConstant.AMOUNT_UNIT;
    }

    public int getAmount() {
        return this.amount;
    }

    public int getLottoCount() {
        return lottoCount;
    }

    private void validate(String amount) {
        if (isEmpty(amount)) {
            ErrorMessage.INPUT_IS_EMPTY.throwIllegalArgumentException();
        }
        if (!isNumeric(amount)) {
            ErrorMessage.PURCHASE_AMOUNT_IS_NOT_A_NUMBER.throwNumberFormatException();
        }
        if (!canDivideByThousand(amount)) {
            ErrorMessage.PURCHASE_AMOUNT_IS_NOT_THOUSAND_UNITS.throwIllegalArgumentException();
        }
        if (isZero(amount)) {
            ErrorMessage.PURCHASE_AMOUNT_IS_ZERO.throwIllegalArgumentException();
        }
    }

    private int toInt(String amount) {
        return Integer.parseInt(amount);
    }

    private boolean isEmpty(String amount) {
        return amount == null || amount.isBlank();
    }

    private boolean isNumeric(String amount) {
        return amount.chars().allMatch(Character::isDigit);
    }

    private boolean canDivideByThousand(String amount) {
        return toInt(amount) % LottoConstant.AMOUNT_UNIT == LottoConstant.ZERO;
    }

    private boolean isZero(String amount) {
        return toInt(amount) == LottoConstant.ZERO;
    }
}
