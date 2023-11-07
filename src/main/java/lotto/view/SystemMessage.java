package lotto.view;

public enum SystemMessage {
    ASK_MONEY("구입금액을 입력해 주세요."),
    ASK_WINNING_NUMBERS("\n당첨 번호를 입력해 주세요."),
    ASK_BONUS("\n보너스 번호를 입력해 주세요."),
    WINNING_RESULT("\n당첨 통계\n---");

    private final String message;

    SystemMessage(String message) {
        this.message = message;
    }

    public String getMessage() {
        return message;
    }
}
