package lotto.domain;

import java.util.List;

public class WinningLotto extends Lotto {

    private final Integer bonusNumber;

    private WinningLotto(final List<Integer> numbers, final Integer bonusNumber) {
        super(numbers);
        this.bonusNumber = bonusNumber;
    }

    public static WinningLotto of(List<Integer> numbers, Integer bonusNumber) {
        return new WinningLotto(numbers, bonusNumber);
    }

    public boolean isWinningNumber(final Integer number) {
        return this.getNumbers().contains(number);
    }

    public Integer getBonusNumber() {
        return this.bonusNumber;
    }
}
