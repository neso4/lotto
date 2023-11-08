package lotto.validation.validators;


import lotto.domain.constant.LottoValue;
import lotto.exception.ErrorMessage;
import lotto.exception.LottoException;

public class InvalidInputAmount extends LottoValue implements LottoGameValidator<Integer> {
	@Override
	public void validate(Integer value) {
		if (value < MINIMUM_MONEY_UNIT) {
			throw new LottoException(ErrorMessage.UNAFFORDABLE_STATUS);
		}
		if (value % MINIMUM_MONEY_UNIT != 0) {
			throw new LottoException(ErrorMessage.INVALID_AMOUNT);
		}
	}
}
