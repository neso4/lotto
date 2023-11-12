package lotto.domain.lotto_prize;

public enum FixedLottoPrizeStandard {
    THREE(3L, 5_000),
    FOUR(4L, 50_000),
    FIVE(5L, 1_500_000),
    FIVE_NUMBER_WITH_BONUS(5L, 30_000_000),
    TOTAL_MATCH(6L, 2_000_000_000);

    private final Long matchCount;
    private final Integer prize;

    FixedLottoPrizeStandard(Long matchCount, Integer prize) {
        this.matchCount = matchCount;
        this.prize = prize;
    }

    public Long getMatchCount() {
        return matchCount;
    }

    public Integer getPrize() {
        return prize;
    }
}
