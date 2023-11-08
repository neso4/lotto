package lotto.domain;

import java.util.List;

public class WinningNumbers {
    public static final int WINNING_NUMBER_COUNT = 6;
    private final Lotto lotto;
    private final int bonusNumber;

    public WinningNumbers(List<Integer> numbers, int bonusNumber) {
        this.lotto = new Lotto(numbers);
        this.bonusNumber = bonusNumber;
    }

    public boolean isMatchBonus(Lotto lotto) {
        return lotto.getNumbers().contains(bonusNumber);
    }

    public int calculateMatchCount(Lotto lotto) {
        return this.lotto.matchCount(lotto);
    }
}
