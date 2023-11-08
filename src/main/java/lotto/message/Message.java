package lotto.message;

public enum Message {
    ASK_PURCHASING_PRICE("구입금액을 입력해 주세요."),
    QUANTITY_OF_LOTTO("개를 구매했습니다. "),
    ASK_WINNING_LOTTO_NUMBERS("당첨 번호를 입력해 주세요."),
    ASK_BONUS_NUMBER("보너스 번호를 입력해 주세요."),
    RESULT_TITLE("당첨 통계"),
    RESULT_TITLE_DECORATION("---"),
    PROFIT_MARGIN_MESSAGE_FRONT("총 수익률은 "),
    PROFIT_MARGIN_MESSAGE_BACK("%입니다.");

    private String message;

    Message(String s) {
        this.message = s;
    }

    public String getMessage() {
        return message;
    }
}