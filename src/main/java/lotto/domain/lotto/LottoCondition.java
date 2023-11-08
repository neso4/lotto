package lotto.domain.lotto;

public enum LottoCondition {

    MIN_NUMBER(1),
    MAX_NUMBER(45),
    COUNT(6),
    PRICE(1000);

    private final int value;

    LottoCondition(int value) {
        this.value = value;
    }

    public int getValue() {
        return value;
    }
}
