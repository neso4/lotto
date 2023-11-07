package lotto.domain;

import java.util.Arrays;

public enum Result {

    THREE_MATCH(5000, 3, false),
    FOUR_MATCH(50000, 4, false),
    FIVE_MATCH(1500000, 5, false),
    FIVE_AND_BONUS_MATCH(30000000, 5, true),
    SIX_MATCH(2000000000, 6, false),
    NOTHING(0, 0, false);

    private final int winnings;
    private final int matchCnt;
    private final boolean bonus;

    Result(int winnings, int matchCnt, boolean bonus) {
        this.winnings = winnings;
        this.matchCnt = matchCnt;
        this.bonus = bonus;
    }

    public static Result find(int matchCount, boolean isBonusMatched) {
        if (matchCount == 5 && isBonusMatched) {
            return Result.FIVE_AND_BONUS_MATCH;
        }
        if (matchCount == 5) {
            return Result.FIVE_MATCH;
        }
        return Arrays.stream(values())
                .filter(result -> result.matchCnt == matchCount)
                .findAny()
                .orElse(Result.NOTHING);
    }


    public int getWinnings() {
        return winnings;
    }

    public int getMatchCnt() {
        return matchCnt;
    }

    public boolean isBonus() {
        return bonus;
    }
}
