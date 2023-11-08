package lotto.domain;

public enum Rank {
    FIRST(6, 2_000_000_000),
    SECOND(5, 30_000_000),
    THIRD(5, 1_500_000),
    FOURTH(4, 50_000),
    FIFTH(3, 5_000),
    LOSING_TICKET(0, 0);

    int countOfMatch;
    int winningMoney;
    Rank(int countOfMatch, int winningMoney) {
        this.countOfMatch = countOfMatch;
        this.winningMoney = winningMoney;
    }

    public int getWinningMoney() {
        return winningMoney;
    }

    public static Rank valueOf(int countOfMatch, boolean hasBonus) {
        if (countOfMatch < 3) {
            return LOSING_TICKET;
        }

        if (SECOND.matchCorrectingCount(countOfMatch) && hasBonus) {
            return SECOND;
        }

        for (Rank rank : Rank.values()) {
            if (rank.matchCorrectingCount(countOfMatch) && rank != SECOND) {
                return rank;
            }
        }
        return null;
    }

    private boolean matchCorrectingCount(int countOfMatch) {
        return this.countOfMatch == countOfMatch;
    }
}
