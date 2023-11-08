package lotto.domain;

public enum Rank {
    FIFTH(3, false, 5000),
    FORTH(4, false, 50000),
    THIRD(5, false, 1500000),
    SECOND(5, true, 30000000),
    FIRST(6, false, 2000000000),
    FAIL(-1, false, 0);

    public int matchedCount;
    public boolean bonus;
    public long reward;

    Rank(int matchedCount, boolean bonus, long reward) {
        this.matchedCount = matchedCount;
        this.bonus = bonus;
        this.reward = reward;
    }
}
