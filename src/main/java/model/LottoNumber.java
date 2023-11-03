package model;

import java.util.Objects;

public class LottoNumber {

    private final int number;

    public LottoNumber(final int number) {
        this.number = number;
    }

    public int getNumber() {
        return number;
    }

    @Override
    public boolean equals(Object value) {
        if (value == this) {
            return true;
        }

        if (!(value instanceof LottoNumber lottoNumber)) {
            return false;
        }

        return lottoNumber.number == number;
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(number);
    }
}
