package lotto.config;

public enum LottoConfig {
	MIN_LOTTO_NUMBER(1),
	MAX_LOTTO_NUMBER(45),
	LOTTO_SIZE(6),
	LOTTO_PRICE(1000);

	private final int value;

	LottoConfig(int value) {
		this.value = value;
	}

	public int getValue() {
		return value;
	}
}