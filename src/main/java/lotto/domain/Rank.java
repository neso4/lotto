package lotto.domain;

import java.util.Arrays;
import java.util.List;
import lotto.utils.StringUtil;

public enum Rank {
    FIRST(6, 6, false, 2000000000L),
    SECOND(5, 5, true, 30000000L),
    THIRD(4, 5, false, 1500000L),
    FOURTH(3, 4, false, 50000L),
    FIFTH(2, 3, false, 5000L),
    LAST(1, 2, false, 0L);

    private int correctCount;
    private boolean needBonus;
    private Long reward;
    private int priority;

    Rank(int priority, int correctCount, boolean needBonus, Long reward) {
        this.priority = priority;
        this.correctCount = correctCount;
        this.needBonus = needBonus;
        this.reward = reward;
    }

    public static Rank calcRank(int correctCount, boolean doesLottoContainBonus) {
        return Arrays.stream(Rank.values())
                .filter(rank -> rank.isCorrectCountEqualTo(correctCount))
                .filter(rank -> rank.doesSatisfyBonusConditions(doesLottoContainBonus))
                .findAny()
                .orElse(LAST);
    }

    public static Long calcReward(List<Rank> ranks) {
        return  ranks.stream()
                .map(rank -> rank.reward)
                .mapToLong(i -> i)
                .sum();
    }

    public String toMessage() {
        return String.format("%d개 일치 (%s원)", correctCount, StringUtil.toKoreanWon(reward));
    }

    private boolean doesSatisfyBonusConditions(boolean doesLottoContainBonus) {
        return !needBonus || doesLottoContainBonus;
    }

    private boolean isCorrectCountEqualTo(int correctCount) {
        return this.correctCount == correctCount;
    }

    public int getPriority() {
        return priority;
    }
}
