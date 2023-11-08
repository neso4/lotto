package lotto.util;

public enum GameMessage {

    INPUT_AMOUNT_MESSAGE("구입금액을 입력해 주세요."),
    INPUT_LUCKY_NUMBER_MESSAGE("당첨 번호를 입력해 주세요."),
    INPUT_BONUS_NUMBER_MESSAGE("보너스 번호를 입력해 주세요."),
    PURCHASE_MESSAGE("개를 구매했습니다."),
    WINNING_STATISTICS_MESSAGE("당첨 통계"),
    GROSS_PROFIT_RATE_MESSAGE("총 수익률은 %.1f%%입니다.");

    private final String message;

    GameMessage(String message) {
        this.message = message;
    }

    public String getMessage() {
        return message;
    }

    public String formatMessage(double value) {
        return String.format(message, value);
    }
}
