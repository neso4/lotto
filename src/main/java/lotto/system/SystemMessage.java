package lotto.system;

public enum SystemMessage {
	INPUT_AMOUNT_MESSAGE("구입금액을 입력해 주세요."),
	OUTPUT_QUANTITY_MESSAGE("개를 구매했습니다."),
	INPUT_WINNING_MESSAGE("당첨 번호를 입력해 주세요."),
	
	NEWLINE("\n");
	private final String message;
	
	SystemMessage(String message) {
		this.message = message;
	}
	
	public String getMessage() {
		return message;
	}
	
	@Override
	public String toString() {
		return this.message;
	}
}