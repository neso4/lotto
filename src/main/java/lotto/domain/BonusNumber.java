package lotto.domain;

import java.util.List;
import lotto.utils.Constants;
import lotto.utils.ErrorMessage;

public class BonusNumber {
    private int bonusNumber;

    public BonusNumber(int inputBonusNumber, List<Integer> winningNumbers) {
        validateBonusNumber(inputBonusNumber, winningNumbers);
        this.bonusNumber = inputBonusNumber;
    }

    public void validateBonusNumber(int inputBonusNumber, List<Integer> winningNumbers) {
        validateBonusNumberRange(inputBonusNumber);
        validateBonusNumberDuplicationWithWinningNumbers(inputBonusNumber, winningNumbers);
    }

    private void validateBonusNumberRange(int inputBonusNumber) {
        if (inputBonusNumber < Constants.LOTTO_MIN_NUMBER || inputBonusNumber > Constants.LOTTO_MAX_NUMBER) {
            throw new IllegalArgumentException(ErrorMessage.BONUS_NUMBER_RANGE_INVALID_ERROR.getMessage());
        }
    }

    private void validateBonusNumberDuplicationWithWinningNumbers(int inputBonusNumber, List<Integer> winningNumbers) {
        if (winningNumbers.contains(inputBonusNumber)) {
            throw new IllegalArgumentException(
                    ErrorMessage.BONUS_NUMBER_DUPLICATE_WITH_WINNING_NUMBER_ERROR.getMessage());
        }
    }

    public int getBonusNumber() {
        return bonusNumber;
    }
}
