package lotto.model;

import lotto.constant.ExceptionMessage;
import lotto.constant.LottoConstant;

public class LottoNumber {

    private final Integer number;

    public LottoNumber(Integer number) {
        validateRange(number);
        this.number = number;
    }


    private void validateRange(Integer number) {
        if (number < LottoConstant.LOTTO_NUMBER_START_RANGE || number > LottoConstant.LOTTO_NUMBER_END_RANGE) {
            throw new IllegalArgumentException(ExceptionMessage.INVALID_NUMBER_RANGE_EXCEPTION_MESSAGE);
        }
    }

    @Override
    public boolean equals(Object o) {
        if (o instanceof LottoNumber) {
            return o.equals(number);
        }
        return number.equals(o);
    }

    @Override
    public String toString() {
        return Integer.toString(number);
    }

}
