package lotto.domain.lotto;

import lotto.constants.ErrorConsts;
import lotto.constants.LottoConsts;

public record LottoNumber(
        int number
) {
    public LottoNumber {
        validate(number);
    }

    private void validate(final int number) {
        validateRange(number);
    }

    private void validateRange(final int number) {
        if (number < LottoConsts.LOTTO_NUMBER_MIN.getValue() || number > LottoConsts.LOTTO_NUMBER_MAX.getValue()) {
            throw new IllegalArgumentException(ErrorConsts.LOTTO_NUMBER_OUT_OF_RANGE.getMessage());
        }
    }
}
