package lotto.model;

import java.util.Arrays;
import java.util.function.BiPredicate;

public enum Prize {

    NONE(0, 0, (matchCount, isBonus) -> matchCount < 3),

    FIFTH(5000, 3, (matchCount, isBonus) -> matchCount == 3),

    FOURTH(50000, 4, (matchCount, isBonus) -> matchCount == 4),

    THIRD(1500000, 5, (matchCount, isBonus) -> matchCount == 5 && !isBonus),
    SECOND(30000000, 5, (matchCount, isBonus) -> matchCount == 5 && isBonus),

    FIRST(2000000000, 6, (matchCount, isBonus) -> matchCount == 6);


    private final int prize;
    private final int match;
    private final BiPredicate<Integer, Boolean> isMatch;

    Prize(int prize, int match, BiPredicate<Integer, Boolean> isMatch) {
        this.prize = prize;
        this.match = match;
        this.isMatch = isMatch;
    }

    public static Prize findPrize(int matchCount, boolean isBonus) {
        return Arrays.stream(Prize.values())
                .filter(prize -> prize.isMatch.test(matchCount, isBonus))
                .findAny()
                .orElse(Prize.NONE);
    }

    public int getPrize() {
        return prize;
    }

    public int getMatch() {
        return match;
    }
}
