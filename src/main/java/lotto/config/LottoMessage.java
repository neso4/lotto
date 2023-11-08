package lotto.config;

public enum LottoMessage {
    PURCHASE_AMOUNT_REQUEST_MESSAGE("구입금액을 입력해 주세요."),
    PURCHASED_AMOUNT_RESULT_MESSAGE("\n%d개를 구매했습니다.\n"),
    WINNING_NUMBER_REQUEST_MESSAGE("\n당첨 번호를 입력해 주세요."),
    BONUS_NUMBER_REQUEST_MESSAGE("\n보너스 번호를 입력해 주세요."),
    WINNING_STATISTICS("\n당첨 통계\n"
            + "---\n"
            + "3개 일치 (5,000원) - %d개\n"
            + "4개 일치 (50,000원) - %d개\n"
            + "5개 일치 (1,500,000원) - %d개\n"
            + "5개 일치, 보너스 볼 일치 (30,000,000원) - %d개\n"
            + "6개 일치 (2,000,000,000원) - %d개\n"
            + "총 수익률은 %s%%입니다.");

    private final String message;

    LottoMessage(String message) {
        this.message = message;
    }

    public String getMessage() {
        return message;
    }

    public String getFormatMessage(Object... args) {
        return String.format(message, args);
    }
}
