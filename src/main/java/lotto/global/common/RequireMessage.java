package lotto.global.common;

public enum RequireMessage {
    PRICE("구매금액을 입력해 주세요."),
    WINNING_NUMBERS("당첨 번호를 입력해 주세요."),
    BONUS_NUMBER("보너스 번호를 입력해 주세요.");

    private String message;

    private RequireMessage() {}

    private RequireMessage(String message) {
        this.message = message;
    }

    public String getMessage() {
        return message;
    }
}
