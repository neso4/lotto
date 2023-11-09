package lotto.domain;

public enum LottoRank {
    FIFTH(3, 5_000),
    FOURTH(4, 50_000),
    THIRD(5, 1_500_000),   // 5개 번호가 일치하지만 보너스 번호는 일치하지 않을 경우,
    SECOND(5, 30_000_000), // 5개 번호가 일치할 경우,
    FIRST(6, 2_000_000_000),
    NONE(0, 0);            // 일치하는 번호가 없을 경우

    private final int matchCount;
    private final int reward;

    LottoRank(int matchCount, int reward) {
        this.matchCount = matchCount;
        this.reward = reward;
    }

    public static LottoRank determineRank(int matchCount, boolean matchBonus) {
        if (isNotInRank(matchCount)) {
            return NONE;
        }
        if (compareToSecondRank(matchCount, matchBonus)) {
            return SECOND;
        }
        if (compareToThirdRank(matchCount, matchBonus)) {
            return THIRD;
        }
        return getRankWithoutBonus(matchCount);
    }

    private static boolean isNotInRank(int matchCount) {
        return matchCount < FIFTH.matchCount;
    }

    private static boolean compareToSecondRank(int matchCount, boolean matchBonus) {
        return matchCount == SECOND.matchCount && matchBonus;
    }

    private static boolean compareToThirdRank(int matchCount, boolean matchBonus) {
        return matchCount == THIRD.matchCount && !matchBonus;
    }

    private static LottoRank getRankWithoutBonus(int matchCount) {
        for (LottoRank lottoRank : values()) {
            if (lottoRank.matchCount == matchCount) {
                return lottoRank;
            }
        }
        return NONE;
    }

    public int getMatchCount() {
        return matchCount;
    }

    public int getReward() {
        return reward;
    }
}
