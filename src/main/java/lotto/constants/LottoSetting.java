package lotto.constants;

public enum LottoSetting {

    MIN_LOTTO_NUMBER(1),
    MAX_LOTTO_NUMBER(45),
    LOTTO_SIZE(6);

    private final int value;

    LottoSetting(int value) {
        this.value = value;
    }

    public int value() {
        return value;
    }
}
