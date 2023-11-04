package lotto.domain;

import static lotto.error.ExceptionCode.NEGATIVE_MONEY_AMOUNT;

import lotto.error.LottoException;

public class Money {

    private static final Long THOUSAND = 1000L;

    private static final Long ZERO_AMOUNT = 0L;

    public static final Money ZERO = new Money(0L);

    private final Long amount;

    public Money(final Long amount) {
        validateNotNegative(amount);
        this.amount = amount;
    }

    private void validateNotNegative(final Long amount) {
        if (amount < ZERO_AMOUNT) {
            throw new LottoException(NEGATIVE_MONEY_AMOUNT);
        }
    }

    public Money add(final Money money) {
        return new Money(amount + money.amount);
    }

    public Money multiply(final long multiplier) {
        return new Money(amount * multiplier);
    }

    public Long divide(final Money divider) {
        return amount / divider.amount;
    }

    public boolean greaterThan(final Money money) {
        return amount > money.amount;
    }

    public boolean isThousandUnit() {
        return (amount % THOUSAND) == ZERO_AMOUNT;
    }

    public float getRate(final Money money) {
        return (float) money.amount / amount;
    }
}
