package lotto.domain;

import static lotto.constants.Constants.Integers.MAX_LOTTO_NUMBER;
import static lotto.constants.Constants.Integers.MIN_LOTTO_NUMBER;
import static lotto.constants.message.ErrorMessage.LOTTO_NUMBER_NOT_IN_RANGE;

public class LottoNumber {

    private final int number;

    public LottoNumber(int number) {
        validate(number);

        this.number = number;
    }

    private void validate(int number) {
        if (!isInRange(number)) {
            throw new IllegalArgumentException(LOTTO_NUMBER_NOT_IN_RANGE.getMessage());
        }
    }

    private boolean isInRange(int number) {
        return MIN_LOTTO_NUMBER.getValue() <= number && number <= MAX_LOTTO_NUMBER.getValue();
    }

    public int getNumber() {
        return number;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof LottoNumber that)) return false;

        return getNumber() == that.getNumber();
    }

    @Override
    public int hashCode() {
        return getNumber();
    }

    @Override
    public String toString() {
        return String.valueOf(number);
    }
}
