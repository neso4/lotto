package lotto.domain;

public class LottoResult {
    private boolean isWinner;
    private int matchingCount;
    private WinningResult winningResult;

    public LottoResult(boolean isWinner, int matchingCount, WinningResult winningResult) {
        this.isWinner = isWinner;
        this.matchingCount = matchingCount;
        this.winningResult = winningResult;
    }

    public boolean isWinner() {
        return isWinner;
    }

    public int getMatchingCount() {
        return matchingCount;
    }

    public WinningResult getWinningResult() {
        return winningResult;
    }
}
