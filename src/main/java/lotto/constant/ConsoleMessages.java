package lotto.constant;

public enum ConsoleMessages {
    PURCHASE_AMOUNT_MESSAGE("구입금액을 입력해 주세요."),
    LOTTO_PURCHASED_MESSAGE("%d개를 구매했습니다.\n"),
    WINNING_NUMBER_MESSAGE("당첨 번호를 입력해 주세요."),
    BONUS_NUMBER_MESSAGE("보너스 번호를 입력해 주세요."),
    WINNING_STATS_MESSAGE("당첨 통계\n---"),
    WINNING_REWARD("%d개 일치 (%s원) - %d개\n"),
    WINNING_BONUS_REWARD("%d개 일치, 보너스 볼 일치 (%s원) - %d개\n"),
    RETURN_RATE("총 수익률은 %.1f%%입니다.");

    private final String value;

    private ConsoleMessages(String value) {
        this.value = value;
    }

    @Override
    public String toString() {
        return value;
    }
}
