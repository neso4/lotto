package lotto;

public enum Rank {
    // 등수별 당첨 조건과 상금 정의
    FIFTH(3, 5_000, false),
    FOURTH(4, 50_000, false),
    THIRD(5, 1_500_000, false),
    SECOND(5, 30_000_000, true),
    FIRST(6, 2_000_000_000, false),
    MISS(0, 0, false);

    private final int countOfMatch;
    private final int winningMoney;
    private final boolean bonusMatch;

    Rank(int countOfMatch, int winningMoney, boolean bonusMatch) {
        this.countOfMatch = countOfMatch;
        this.winningMoney = winningMoney;
        this.bonusMatch = bonusMatch;
    }

    public int getCountOfMatch() {
        return countOfMatch;
    }

    public int getWinningMoney() {
        return winningMoney;
    }

    public boolean isBonusMatch() {
        return bonusMatch;
    }

    public static Rank valueOf(int countOfMatch, boolean matchBonus) {
        if (countOfMatch == SECOND.countOfMatch && matchBonus) {
            return SECOND;
        }
        for (Rank rank : values()) {
            if (rank.countOfMatch == countOfMatch && rank != SECOND) {
                return rank;
            }
        }
        return MISS;
    }
}
