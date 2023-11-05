package lotto.domain;

public class Cash {
    private static final int LOTTO_PRICE = 1000;

    private final Integer amount;

    public Cash(Integer amount) {
        validate(amount);
        this.amount = amount;
    }

    private void validate(Integer amount) {
        if (isAmountNotInMultipleOfLottoPrice(amount)) {
            throw new IllegalArgumentException();
        }
    }

    private boolean isAmountNotInMultipleOfLottoPrice(Integer amount) {
        return amount % LOTTO_PRICE != 0;
    }

    public Integer getAmount() {
        return amount;
    }
}
