package lotto.domain;

import java.util.List;

import static lotto.constant.ExceptionMessage.INVALID_BONUS_NUMBER_CONTAIN_EXCEPTION;
import static lotto.constant.ExceptionMessage.INVALID_BONUS_NUMBER_RANGE_EXCEPTION;
import static lotto.constant.LottoConstant.MAX_NUMBER;
import static lotto.constant.LottoConstant.MIN_NUMBER;

public class WinningNumber {
    private final Lotto lotto;
    private final int bonusNumber;

    public WinningNumber(Lotto lotto, int bonusNumber) {
        validateBonusNumber(lotto, bonusNumber);
        this.lotto = lotto;
        this.bonusNumber = bonusNumber;
    }

    private void validateBonusNumber(Lotto lotto, int bonusNumber) {
        List<Integer> winningNumber = lotto.getNumbers();
        if (isWrongRange(bonusNumber)) {
            throw new IllegalArgumentException(INVALID_BONUS_NUMBER_RANGE_EXCEPTION.getMessage());
        }

        if (isContainInLotto(winningNumber, bonusNumber)) {
            throw new IllegalArgumentException(INVALID_BONUS_NUMBER_CONTAIN_EXCEPTION.getMessage());
        }
    }

    private boolean isWrongRange(int bonusNumber) {
        return bonusNumber < MIN_NUMBER.getValue() || bonusNumber > MAX_NUMBER.getValue();
    }

    private boolean isContainInLotto(List<Integer> lotto, int bonusNumber) {
        return lotto.contains(bonusNumber);
    }

    public Lotto getWinningNumber() {
        return lotto;
    }

    public int getBonusNumber() {
        return bonusNumber;
    }
}
