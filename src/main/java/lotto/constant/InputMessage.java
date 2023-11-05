package lotto.constant;

public enum InputMessage {
    AMOUNT("구입금액을 입력해 주세요."),
    WINNING_NUMBER("\n당첨 번호를 입력해 주세요."),
    BONUS("\n보너스 번호를 입력해 주세요.");

    private final String message;

    InputMessage(String message) {
        this.message = message;
    }

    public String getMessage() {
        return message;
    }
}
