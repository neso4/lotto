package lotto.domain;

import java.util.Arrays;
import java.util.function.Predicate;

public enum Rankings {

    FIFTH_PLACE(3, false, 5_000, "3개 일치 (5,000원) - "),
    FOURTH_PLACE(4, false, 50_000, "4개 일치 (50,000원) - "),
    THIRD_PLACE(5, false, 1_500_000, "5개 일치 (1,500,000원) - "),
    SECOND_PLACE(5, true, 30_000_000, "5개 일치, 보너스 볼 일치 (30,000,000원) - "),
    FIRST_PLACE(6, false, 2_000_000_000, "6개 일치 (2,000,000,000원) - "),
    NONE(0, false, 0, "");

    private static final int FIVE_MATCHES = 5;
    private static final String POSTFIX = "개";
    private final int matchCount;
    private final boolean hasBonusNumber;
    private final int prizeMoney;
    private final String message;

    Rankings(int matchCount, boolean hasBonusNumber, int prizeMoney, String message) {
        this.matchCount = matchCount;
        this.hasBonusNumber = hasBonusNumber;
        this.prizeMoney = prizeMoney;
        this.message = message;
    }

    public static Rankings setRankings(int matchCount, boolean hasBonusNumber) {
        return Arrays.stream(Rankings.values())
                .filter(rankings -> rankings.matches(matchCount, hasBonusNumber))
                .findAny()
                .orElse(NONE);
    }

    private boolean matches(int matchCount, boolean hasBonusNumber) {
        return this.matchCount == matchCount && this.hasBonusNumber == hasBonusNumber;
    }

    public int getPrizeMoney(int count) {
        return prizeMoney * count;
    }

    public String getMessage(int count) {
        return message + count + POSTFIX;
    }
}
