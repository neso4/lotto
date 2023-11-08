package lotto.vo;

import lotto.constant.LottoConstants;
import lotto.exception.LottoException;

import java.util.Objects;

public final class LottoWinningBonusNumber {
    private final LottoNumber value;

    public LottoWinningBonusNumber(final Integer value) throws LottoException {
        validate(value);
        this.value = new LottoNumber(value);
    }

    private void validate(final Integer value) throws LottoException {
        if (value < LottoConstants.LOTTO_MIN_NUMBER.getValue()) {
            throw new LottoException(LottoException.ErrorMessage.RANGE_BONUS_NUMBER.getMessage());
        }

        if (value > LottoConstants.LOTTO_MAX_NUMBER.getValue()) {
            throw new LottoException(LottoException.ErrorMessage.RANGE_BONUS_NUMBER.getMessage());
        }
    }

    public LottoNumber value() {
        return value;
    }

    public Integer getValue() {
        return value.value();
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (obj == null || obj.getClass() != this.getClass()) {
            return false;
        }
        LottoWinningBonusNumber other = (LottoWinningBonusNumber) obj;
        return value.value().equals(other.getValue());
    }

    @Override
    public int hashCode() {
        return Objects.hash(value);
    }
}
