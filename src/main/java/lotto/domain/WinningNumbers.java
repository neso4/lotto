package lotto.domain;

import static lotto.constant.ExceptionMessage.NO_DUPLICATE_WINNING_NUMBER_ERROR_MESSAGE;
import static lotto.utils.validator.LottoNumberValidator.validateDuplicateNumbers;
import static lotto.utils.validator.LottoNumberValidator.validateOutOfRange;

import java.util.List;

public class WinningNumbers {

    private final WinningNumber winningNumber;
    private final BonusNumber bonusNumber;

    public WinningNumbers(List<Integer> winningNumber, int bonusNumber) {
        validateOutOfRange(winningNumber);
        validateOutOfRange(bonusNumber);
        validateDuplicateNumbers(winningNumber);
        validateDuplicateBonusNumber(winningNumber, bonusNumber);
        this.winningNumber = new WinningNumber(winningNumber);
        this.bonusNumber = new BonusNumber(bonusNumber);
    }

    public LottoResults calculateLottoResult(Lotto lotto) {
        int correctCount = winningNumber.calculateCorrectNumberCount(lotto);
        boolean hasBonus = bonusNumber.hasBonus(lotto);
        return LottoResults.getLottoResult(correctCount, hasBonus);
    }

    private void validateDuplicateBonusNumber(List<Integer> winningNumber, int bonusNumber) {
        if (winningNumber.contains(bonusNumber)) {
            throw new IllegalArgumentException(NO_DUPLICATE_WINNING_NUMBER_ERROR_MESSAGE);
        }
    }
}
