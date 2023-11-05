package lotto.model.constans;

public enum LottoConstants {
    LOTTO_PRICE(1000),
    LOTTO_SIZE(6),
    MIN_LOTTO_NUMBER(1),
    MAX_LOTTO_NUMBER(45);

    private final int value;

    private LottoConstants(int value) {
        this.value = value;
    }

    public int value() {
        return value;
    }
}
