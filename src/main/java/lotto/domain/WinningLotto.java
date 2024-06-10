package lotto.domain;

import lotto.constants.Value;
import java.util.List;

import static lotto.constants.ErrorMessage.*;

public class WinningLotto extends Lotto {

    private int bonusNumber;

    public WinningLotto(List<Integer> numbers) {
        super(numbers);
    }

    public int getBonusNumber() {
        return bonusNumber;
    }

    public void setBonusNumber(int bonusNumber) {
        validateBonusNumber(bonusNumber);
        this.bonusNumber = bonusNumber;
    }

    private void validateBonusNumber(int bonusNumber) {
        validationDuplicateLottoNumber(bonusNumber);
        validateBonusNumberRange(bonusNumber);
    }

    private void validationDuplicateLottoNumber(int bonusNumber) {
        List<Integer> numbers = this.getNumbers();

        if (numbers.contains(bonusNumber)) {
            throw new IllegalArgumentException(BONUS_NUMBER_DUPLICATE_ERROR.getMessage());
        }
    }

    private void validateBonusNumberRange(int bonusNumber) {
        if (bonusNumber < Value.LOTTO_START_NUMBER || bonusNumber > Value.LOTTO_END_NUMBER) {
            throw new IllegalArgumentException(BONUS_NUMBER_RANGE_ERROR.getMessage());
        }
    }
}
