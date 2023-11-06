package lotto.domain;

public enum WinningNumber {
    FIRST(6, 2_000_000_000), // 1등
    SECOND(7, 30_000_000), // 2등
    THIRD(5, 1_500_000), // 3등
    FOURTH(4, 50_000), // 4등
    FIFTH(3, 5_000), // 5등
    MISS(0, 0);

    private static final int WINNING_MIN_COUNT = 3;
    private int countOfMatch;
    private int winningAmount;

    WinningNumber(int countOfMatch, int winningAmount) {
        this.countOfMatch = countOfMatch;
        this.winningAmount = winningAmount;
    }

    public static WinningNumber valueOf(int countOfMatch) {
        if (countOfMatch < WINNING_MIN_COUNT) {
            return MISS;
        }
        for (WinningNumber number : values()) {
            if (number.matchCount(countOfMatch)) {
                return number;
            }
        }
        throw new IllegalArgumentException();
    }

    public int getWinningAmount() {
        return winningAmount;
    }

    public int getCountOfMatch() {
        return countOfMatch;
    }

    private boolean matchCount(int countOfMatch) {
        return this.countOfMatch == countOfMatch;
    }
}
