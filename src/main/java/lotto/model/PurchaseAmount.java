package lotto.model;

import static lotto.exception.ExceptionMessage.INPUT_NOT_MULTIPLE_OF_THOUSAND_ERROR_MESSAGE;

public class PurchaseAmount {
    private final int purchaseAmount;

    private PurchaseAmount(int amount) {
        validate(amount);
        this.purchaseAmount = amount;
    }

    public static PurchaseAmount from(int amount) {
        return new PurchaseAmount(amount);
    }

    private void validate(int amount) {
        validateThousandMultiple(amount);
    }

    private void validateThousandMultiple(int amount) {
        if (!isMultipleOfThousand(amount)) {
            throw new IllegalArgumentException(INPUT_NOT_MULTIPLE_OF_THOUSAND_ERROR_MESSAGE);
        }
    }

    private boolean isMultipleOfThousand(int amount) {
        return amount % 1000 == 0;
    }
}
