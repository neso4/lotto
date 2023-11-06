package lotto;

public enum LottoRank {
    FIRST(2000000000),
    SECOND(30000000),
    THIRD(1500000),
    FOURTH(50000),
    FIFTH(5000),
    NONE(0);

    private final int value;

    LottoRank(int value) {
        this.value = value;
    }

    public int getValue() {
        return value;
    }
}
