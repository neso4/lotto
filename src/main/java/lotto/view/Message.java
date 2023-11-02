package lotto.view;

public enum Message {
    MONEY("구입금액을 입력해 주세요."),
    WINNING_NUM("당첨 번호를 입력해 주세요."),
    BONUS_NUM("보너스 번호를 입력해 주세요.");

    private final String message;

    Message(String message) {
        this.message = message;
    }

    public String getMessage(){
        return message;
    }
}
