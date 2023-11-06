package lotto.domain;

import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public enum Rank {
    FIFTH_PLACE(5000, 3),
    FOURTH_PLACE(50000, 4),
    THIRD_PLACE(1500000, 5),
    SECOND_PLACE((int) 3E7, 5.5),
    FIRST_PLACE((int) 2E9, 6);

    private static final Map<Double, Rank> BY_CORRECTS =
            Stream.of(values()).collect(Collectors.toMap(Rank::getCorrects, e -> e));
    private final int prize;
    private final double corrects;

    Rank(int prize, double corrects) {
        this.prize = prize;
        this.corrects = corrects;
    }

    private int getPrize() {
        return prize;
    }

    public double getCorrects() {
        return corrects;
    }

    public long sumPrize(long result, int count) {
        return result + getPrize() * count;
    }

    public static Rank valueOfCorrects(Double corrects) {
        return BY_CORRECTS.get(corrects);
    }
}
