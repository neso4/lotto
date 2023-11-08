package lotto.constant;

public enum OutputText {
    ENTER_PURCHASE_AMOUNT("구입금액을 입력해 주세요."),
    ENTER_WINNING_NUMBERS("당첨 번호를 입력해 주세요."),
    ENTER_BONUS_NUMBER("보너스 번호를 입력해 주세요."),
    WINNING_STATISTICS("당첨 통계\n---");

    private final String message;

    OutputText(String message) {
        this.message = message;
    }

    @Override
    public String toString() {
        return message;
    }
}
