package lotto.controller.util.validator;

import java.util.HashSet;
import java.util.List;
import java.util.Set;
import lotto.configuration.ErrorMessage;
import lotto.configuration.LottoConstants;

public class InputValidator {

    public void validateMoney(Integer moneyAmount) {
        if (moneyAmount < LottoConstants.LOTTO_PRICE) {
            throw new IllegalArgumentException(ErrorMessage.NOT_ENOUGH_MONEY);
        }

        if (moneyAmount % LottoConstants.LOTTO_PRICE != 0) {
            throw new IllegalArgumentException(ErrorMessage.UNIT_NOT_VALID);
        }
    }

    public void validateDrawNumbers(List<Integer> drawNumbers) {
        isDrawNumberLengthCorrect(drawNumbers);

        isDuplicated(drawNumbers);

        drawNumbers.forEach(this::isNumberInRange);
    }

    private void isDrawNumberLengthCorrect(List<Integer> numbers) {
        if (numbers.size() != LottoConstants.LOTTO_NUMBER_LENGTH) {
            throw new IllegalArgumentException(ErrorMessage.WRONG_LOTTO_NUMBER_LENGTH);
        }
    }

    private void isDuplicated(List<Integer> numbers) {
        Set<Integer> numberSet = new HashSet<>(numbers);

        if (numbers.size() != numberSet.size()) {
            throw new IllegalArgumentException(ErrorMessage.NUMBER_DUPLICATED);
        }
    }

    public void validateBonusNumber(Integer bonusNumber, List<Integer> winningNumbers) {
        isNumberInRange(bonusNumber);

        isBonusNumberDuplicated(bonusNumber, winningNumbers);
    }

    private void isBonusNumberDuplicated(Integer bonusNumber, List<Integer> winningNumbers) {
        if (winningNumbers.contains(bonusNumber)) {
            throw new IllegalArgumentException(ErrorMessage.BONUS_NUMBER_DUPLICATED);
        }
    }

    private void isNumberInRange(Integer number) {
        if (number < LottoConstants.MIN_LOTTO_NUM || number > LottoConstants.MAX_LOTTO_NUM) {
            throw new IllegalArgumentException(ErrorMessage.LOTTO_NUMBER_NOT_IN_RANGE);
        }
    }
}
