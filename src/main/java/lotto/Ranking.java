package lotto;

import java.util.Arrays;

public enum Ranking {
    FIRST(2000000000, 6, false),
    SECOND(30000000, 5,true),
    THIRD(1500000, 5,false),
    FOURTH(50000, 4,false),
    FIFTH(5000, 3,false);

    private final int prize;
    private final int count;
    private final boolean hasBonusNumber;

    Ranking(int prize, int count,boolean hasBonusNumber) {
        this.prize = prize;
        this.count = count;
        this.hasBonusNumber = hasBonusNumber;
    }

    public static Ranking findRanking(int count, boolean hasBonusNumber) {
        return Arrays.stream(Ranking.values())
                .filter(ranking -> ranking.count == count)
                .filter(ranking -> ranking.hasBonusNumber == hasBonusNumber)
                .findFirst()
                .orElseThrow(() -> new IllegalArgumentException("No matching ranking found."));
    }
}
