package lotto.domain.common;

import java.util.Objects;

public class Money {

    private static final int NEGATIVE_STANDARD = 0;
    public static final String CURRENCY = "원";
    private static final String NEGATIVE_MONEY_MESSAGE = "돈은 음수가 될 수 없습니다.";
    private static final String UNKNOWN_MONEY_MESSAGE = "알 수 없는 돈과 해당 연산을 수행할 수 없습니다.";
    private static final String DIVIDE_WITH_ZERO_MESSAGE = "0" + CURRENCY + "으로 나눌 수 없습니다.";

    private final long value;

    private Money(long value) {
        this.value = value;
    }

    public static Money from(long value) {
        checkPositiveMoney(value);

        return new Money(value);
    }

    private static void checkPositiveMoney(long value) {
        if (isNegative(value)) {
            throw new IllegalArgumentException(NEGATIVE_MONEY_MESSAGE);
        }
    }

    private static boolean isNegative(long value) {
        return value < NEGATIVE_STANDARD;
    }

    public long divide(Money target) {
        validate(target);

        return this.value / target.value;
    }

    private void validate(Money target) {
        checkMoneyNonNull(target);
        checkMoneyZero(target);
    }

    private void checkMoneyZero(Money target) {
        if (isZero(target)) {
            throw new IllegalArgumentException(DIVIDE_WITH_ZERO_MESSAGE);
        }
    }

    private boolean isZero(Money target) {
        return target.value == 0;
    }

    public boolean hasRemainderWith(Money target) {
        checkMoneyNonNull(target);

        return (this.value % target.value) != 0;
    }

    public boolean isLessThan(Money target) {
        checkMoneyNonNull(target);

        return this.value < target.value;
    }

    private void checkMoneyNonNull(Money target) {
        if (Objects.isNull(target)) {
            throw new IllegalArgumentException(UNKNOWN_MONEY_MESSAGE);
        }
    }

    public long getValue() {
        return value;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Money money = (Money) o;
        return value == money.value;
    }

    @Override
    public int hashCode() {
        return Objects.hash(value);
    }
}
