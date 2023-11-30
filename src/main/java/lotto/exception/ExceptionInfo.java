package lotto.exception;

public enum ExceptionInfo {
	PURCHASE_PRICE_IS_NOT_INTEGER("[ERROR] 구입 금액은 정수여야 합니다."),
	PURCHASE_PRICE_IS_NOT_RIGHT_PRICE("[ERROR] 구입 금액은 1000원 단위여야 합니다."),
	LOTTO_NUMBERS_SIZE_IS_NOT_SIX("[ERROR] 로또 번호가 6개여야 합니다."),
	WINNING_NUMBERS_ARE_NOT_INTEGER("[ERROR] 당첨 번호는 정수여야 합니다."),
	BONUS_NUMBER_IS_NOT_INTEGER("[ERROR] 보너스 번호는 정수여야 합니다."),
	DUPLICATED_LOTTO_NUMBER("[ERROR] 로또 번호는 중복될 수 없습니다."),
	OUT_OF_RANGE_LOTTO_NUMBER("[ERROR] 로또 번호는 1~45 사이의 숫자여야 합니다."),
	OUT_OF_RANGE_BONUS_NUMBER("[ERROR] 보너스 번호는 1~45 사이의 숫자여야 합니다."),
	DUPLICATED_BONUS_NUMBER("[ERROR] 보너스 번호는 로또 번호와 중복될 수 없습니다.");

	private final String message;

	ExceptionInfo(String message) {
		this.message = message;
	}

	public String getMessage() {
		return message;
	}
}
