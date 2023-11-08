package lotto.view.constant;

public enum LottoMessage {
    REQUEST_PURCHASE_AMOUNT("구입금액을 입력해 주세요."),
    RESPONSE_PURCHASE_AMOUNT("개를 구매했습니다."),
    REQUEST_WINNING_NUMBER("당첨 번호를 입력해 주세요."),
    REQUEST_BONUS_NUMBER("보너스 볼을 입력해 주세요."),
    SHOW_STATISTICS("당첨 통계"),
    SHOW_STATISTICS_DELIMITER("---"),
    COMMENT_SUFFIX("개"),
    SHOW_STATISTICS_YIELD("총 수익률은 "),
    SHOW_STATISTICS_YIELD_SUFFIX("%입니다.");
    private final String message;
    LottoMessage(String message) {
        this.message = message;
    }
    public String getMessage() {
        return message;
    }
}
