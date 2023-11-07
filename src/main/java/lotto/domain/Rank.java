package lotto.domain;

import java.util.Arrays;

public enum Rank {
    FIRST(6, 2000000000),
    SECOND(5, 30000000),
    THIRD(5, 1500000),
    FOURTH(4, 50000),
    FIFTH(3, 5000),
    BLANK(0, 0);

    private final int matchCount;
    private final int prizeMoney;

    Rank(int matchCount, int prizeMoney) {
        this.matchCount = matchCount;
        this.prizeMoney = prizeMoney;
    }

    public static Rank of(int matchCount, boolean isBonus) {
        if (isBonus && matchCount == SECOND.matchCount) {
            return SECOND;
        }
        return Arrays.stream(values())
                .filter(value -> !value.equals(SECOND) && value.matchCount == matchCount)
                .findFirst()
                .orElse(BLANK);
    }

    public int getMatchCount() {
        return matchCount;
    }

    public int getPrizeMoney() {
        return prizeMoney;
    }

}
