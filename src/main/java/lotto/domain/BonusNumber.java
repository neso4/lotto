package lotto.domain;

import java.util.HashSet;
import java.util.List;
import lotto.exception.LottoExceptionMessage;

public class BonusNumber {
    private static final int START_INCLUSIVE = 1;
    private static final int END_INCLUSIVE = 45;

    private final int bonusNumber;

    private BonusNumber(int bonusNumber, WinningNumbers winningNumbers) {
        validate(bonusNumber);
        isDuplicate(bonusNumber, winningNumbers);
        this.bonusNumber = bonusNumber;
    }

    public static BonusNumber of(int bonusNumber, WinningNumbers winningNumbers) {
        try {
            return new BonusNumber(bonusNumber, winningNumbers);
        } catch (IllegalArgumentException e) {
            return null;
        }
    }

    public int getBonusNumber() {
        return bonusNumber;
    }

    private void validate(int bonusNumber) {
        if (bonusNumber < START_INCLUSIVE || bonusNumber > END_INCLUSIVE) {
            System.out.println(LottoExceptionMessage.BONUS_NUMBER_MUST_BETWEEN_START_AND_END_INCLUSIVE.getMessage());
            throw new IllegalArgumentException(
                    LottoExceptionMessage.BONUS_NUMBER_MUST_BETWEEN_START_AND_END_INCLUSIVE.getMessage());
        }
    }

    private void isDuplicate(int bonusNumber, WinningNumbers winningNumbers) {
        List<Integer> winning = winningNumbers.getWinningNumbers();
        HashSet<Integer> set = new HashSet<>(winning);
        set.add(bonusNumber);
        if (set.size() != 7) {
            System.out.println(LottoExceptionMessage.BONUS_NUMBER_MUST_NOT_DUPLICATE.getMessage());
            throw new IllegalArgumentException(LottoExceptionMessage.BONUS_NUMBER_MUST_NOT_DUPLICATE.getMessage());
        }
    }

}
