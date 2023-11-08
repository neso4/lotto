package lotto.domain;

import java.util.Objects;
import lotto.exception.OverValueException;

public class Number {

    private static final int LOTTO_MAX_NUM = 45;
    private static final String ERROR_HEAD = "[ERROR] ";

    private final int value;

    private Number(final int value) {
        this.value = value;
    }

    public static Number from(final int value) {
        validRange(value);
        return new Number(value);
    }

    private static void validRange(final int value) {
        boolean overRange = value > LOTTO_MAX_NUM;
        if (overRange) {
            throw new OverValueException();
        }
    }

    @Override
    public String toString() {
        return String.valueOf(value);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        Number number = (Number) o;
        return value == number.value;
    }

    @Override
    public int hashCode() {
        return Objects.hash(value);
    }
}
