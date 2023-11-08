package lotto.constant;

public enum LottoConstant {
    LOTTO_SIZE(6),
    MIN_NUMBER(1),
    MAX_NUMBER(45),
    MIN_MATCH_COUNT_FOR_BONUS(5);

    private final int value;

    LottoConstant(int value) {
        this.value = value;
    }

    public int getValue() {
        return value;
    }
}
