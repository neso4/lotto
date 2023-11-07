package lotto.model;

import java.util.Objects;
import lotto.exception.InvalidMoneyRangeException;
import lotto.exception.NonPositiveIntException;

public class Money {

    private static final int LOTTO_COST = 1000;
    private static final int ZERO_VALUE = 0;
    private static final int PERCENTAGE = 100;

    private final long amount;

    private Money(final long amount) {
        this.amount = amount;
    }

    public static Money from(final String amount) {
        validateNumericValue(amount);
        long value = Long.parseLong(amount);
        validateRange(value);
        return new Money(value);
    }

    private static void validateNumericValue(final String value) {
        if (!isNumeric(value)) {
            throw new NonPositiveIntException();
        }
    }

    private static boolean isNumeric(final String value) {
        return value.chars()
                .allMatch(Character::isDigit);
    }

    private static void validateRange(final long value) {
        if (isInValidRange(value)) {
            throw new InvalidMoneyRangeException();
        }
    }

    private static boolean isInValidRange(final long value) {
        return value % LOTTO_COST != ZERO_VALUE || value < LOTTO_COST;
    }

    public long buyLotto() {
        return amount / LOTTO_COST;
    }

    public double calculateReturnRate(final long sumOfPrice) {
        return ((double) sumOfPrice / amount) * PERCENTAGE;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        Money money = (Money) o;
        return amount == money.amount;
    }

    @Override
    public int hashCode() {
        return Objects.hash(amount);
    }
}
