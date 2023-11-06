package lotto.domain;

import lotto.exception.LottoPurchaseAmountException;

public class LottoPurchaseAmount {
    private final String input;

    public LottoPurchaseAmount(String input) {
        validate(input);
        this.input = input;

    }

    private void validate(String input) {
        LottoPurchaseAmountException lottoPurchaseAmountException = new LottoPurchaseAmountException(input);
    }
}
