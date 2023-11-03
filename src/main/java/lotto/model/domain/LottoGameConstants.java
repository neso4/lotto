package lotto.model.domain;

public enum LottoGameConstants {

    MIN_NUMBER(1),
    MAX_NUMBER(45),
    NUMBERS_PER_LOTTO(6);

    private final int value;

    LottoGameConstants(int value) {
        this.value = value;
    }

    public int getValue() {
        return value;
    }
}
