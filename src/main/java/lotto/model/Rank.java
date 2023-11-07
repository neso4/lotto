package lotto.model;

import java.util.Arrays;

public enum Rank {

    FIFTH("3개 일치 (5,000원)", 5_000, 3),
    FOURTH("4개 일치 (50,000원)", 50_000, 4),
    THIRD("5개 일치 (1,500,000원)", 1_500_000, 5),
    SECOND("5개 일치, 보너스 볼 일치 (30,000,000원)", 30_000_000, 5),
    FIRST("6개 일치 (2,000,000,000원)", 2_000_000_000, 6),
    MISS("", 0, 0);

    private final String message;

    private final int prize;
    private final int correctCount;

    Rank(String message, int prize, int correctCount) {
        this.message = message;
        this.prize = prize;
        this.correctCount = correctCount;
    }

    public static Rank valueOf(int correctCount, boolean containBonusNumber) {
        if (SECOND.isSameCorrect(correctCount) && containBonusNumber) {
            return SECOND;
        }

        return Arrays.stream(Rank.values())
                .filter(result -> result.isSameCorrect(correctCount) && result != SECOND)
                .findFirst()
                .orElse(MISS);
    }

    private boolean isSameCorrect(int correctCount) {
        return this.correctCount == correctCount;
    }

    public String getMessage() {
        return message;
    }

    public int getPrize() {
        return prize;
    }
}
