package lotto.model.domain.constants;

public enum LottoGameConstants {

    MIN_NUMBER(1),
    MAX_NUMBER(45),
    NUMBERS_PER_LOTTO(6),
    PRICE_PER_LOTTO(1000),
    BONUS_COUNT(1) // 보너스를 포함하는 당첨의 개수
    ;

    private final int value;

    LottoGameConstants(int value) {
        this.value = value;
    }

    public int getValue() {
        return value;
    }
}
