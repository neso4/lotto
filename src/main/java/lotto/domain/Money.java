package lotto.domain;

import lotto.enums.ErrorMessages;
import lotto.exception.MoneyFormatException;

public class Money {
    private static final int UNIT = 1_000;
    private final int amount;

    public Money(Integer amount) {
        validate(amount);
        this.amount = amount;
    }

    private void validate(Integer amount) {
        if (isUnderAtLeast(amount)) {
            System.out.println(ErrorMessages.PURCHASE_LEAST.getErrorMessage());
            throw new MoneyFormatException(ErrorMessages.PURCHASE_LEAST.getErrorMessage());
        }
        if (isCorrectUnit(amount)) {
            System.out.println(ErrorMessages.PURCHASE_UNIT.getErrorMessage());
            throw new MoneyFormatException(ErrorMessages.PURCHASE_UNIT.getErrorMessage());
        }
    }

    private boolean isUnderAtLeast(Integer amount) {
        return amount < UNIT;
    }

    private boolean isCorrectUnit(Integer amount) {
        return amount % UNIT != 0;
    }

    public int calculateCount() {
        return amount / UNIT;
    }

    public int getAmount() {
        return amount;
    }
}
