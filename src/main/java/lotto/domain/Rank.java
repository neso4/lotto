package lotto.domain;

import java.util.Arrays;
import java.util.function.BiFunction;
import java.util.function.BiPredicate;

public enum Rank {
    NONE(0, 0, false, (matchCount, bonus) -> matchCount <= 2),
    FIFTH(3, 5_000, false, (matchCount, bonus) -> matchCount == 3),
    FOURTH(4, 50_000, false, (matchCount, bonus) -> matchCount == 4),
    THIRD(5, 1_500_000, false, (matchCount, bonus) -> matchCount == 5 && !bonus),
    SECOND(5, 30_000_000, true, (matchCount, bonus) -> matchCount == 5 && bonus),
    FIRST(6, 2_000_000_000, false, (matchCount, bonus) -> matchCount == 6);

    private final int matchCount;
    private final int prize;
    private final boolean bonus;
    private final BiPredicate<Integer, Boolean> matchResult;

    Rank(final int matchCount, final int prize, final boolean bonus, final BiPredicate<Integer, Boolean> matchResult) {
        this.matchCount = matchCount;
        this.prize = prize;
        this.bonus = bonus;
        this.matchResult = matchResult;
    }

    public static Rank rankResult(final int matchCount, final boolean bonus){
        return Arrays.stream(Rank.values())
                .filter(rank -> rank.matchResult.test(matchCount, bonus))
                .findAny()
                .orElse(NONE);
    }

    public int getPrize(){
        return prize;
    }

    public int getMatchCount(){
        return matchCount;
    }
}
