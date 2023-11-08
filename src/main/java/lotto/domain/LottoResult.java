package lotto.domain;

import java.util.Objects;

public class LottoResult {
    private final int winningCount;
    private final boolean bonusNumberMatching;

    private LottoResult(int winningCount, boolean bonusNumberMatching) {
        this.winningCount = winningCount;
        this.bonusNumberMatching = bonusNumberMatching;
    }

    public static LottoResult of(int winningCount, boolean bonusNumberMatching) {
        return new LottoResult(winningCount, bonusNumberMatching);
    }

    public void recordMatchingCaseResults() {
        for (MatchingCase matchingCase : MatchingCase.values()) {
            matchingCase.recordResult(winningCount, bonusNumberMatching);
        }
    }

    public int getWinningCount() {
        return winningCount;
    }

    public boolean isBonusNumberMatching() {
        return bonusNumberMatching;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        LottoResult that = (LottoResult) o;
        return winningCount == that.winningCount && bonusNumberMatching == that.bonusNumberMatching;
    }

    @Override
    public int hashCode() {
        return Objects.hash(winningCount, bonusNumberMatching);
    }
}
