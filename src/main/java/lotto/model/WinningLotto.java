package lotto.model;


public class WinningLotto {
    private final Lotto winningNumber;
    private final int bonusNumber;

    private WinningLotto(Lotto winningNumber, int bonusNumber) {
        this.winningNumber = winningNumber;
        this.bonusNumber = bonusNumber;
    }

    public static WinningLotto of(Lotto winningNumber, int bonusNumber) {
        return new WinningLotto(winningNumber, bonusNumber);
    }

    public WinningInfo compare(Lotto lotto) {
        long matchedCount = lotto.countMatchedNumbers(winningNumber);
        boolean isBonusMatched = lotto.isBonusMatch(bonusNumber);

        return WinningInfo.of((int) matchedCount, isBonusMatched);
    }


}
