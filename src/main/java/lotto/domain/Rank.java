package lotto.domain;

import java.util.Arrays;

public enum Rank {
    FIRST(6, 2_000_000_000L),
    SECOND(5, 30_000_000L),
    THIRD(5, 1_500_000L),
    FOURTH(4, 50_000L),
    FIFTH(3, 5_000L),
    NONE(0, 0L);

    private final int matchedCount;
    private final long prizeMoney;

    Rank(int matchedCount, long prizeMoney) {
        this.matchedCount = matchedCount;
        this.prizeMoney = prizeMoney;
    }

    public long calculateTotalPrizeMoney(int count) {
        return prizeMoney * count;
    }

    public static Rank from(MatchDetail matchDetail) {
        return Rank.of(matchDetail.matchedCount(), matchDetail.matchedBonus());
    }

    public static Rank of(int matchedCount, boolean isBonusNumberMatched) {
        if (matchedCount == THIRD.matchedCount && !isBonusNumberMatched) {
            return THIRD;
        }

        return Arrays.stream(values())
                .filter(rank -> rank.matchedCount == matchedCount)
                .findFirst()
                .orElse(NONE);
    }

    public String getResultFormat() {
        String formattedMoney = String.format("%,d", prizeMoney);
        StringBuilder stringBuilder = new StringBuilder(matchedCount + "개 일치");

        if (this == Rank.SECOND) {
            stringBuilder.append(", 보너스 볼 일치");
        }

        stringBuilder
                .append(" (")
                .append(formattedMoney)
                .append("원) ");

        return stringBuilder.toString();
    }

}
