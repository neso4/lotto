package lotto.view;

public enum GuideMessage {
    PURCHASE_GUIDE_MESSAGE("구입금액을 입력해 주세요."),
    WINNING_NUMBERS_GUIDE_MESSAGE("당첨 번호를 입력해 주세요."),
    BONUS_NUMBER_GUIDE_MESSAGE("\n보너스 번호를 입력해 주세요."),
    LOTTO_COUNT_GUIDE_MESSAGE("\n%d개를 구매했습니다.\n"),
    RESULT_GUIDE_MESSAGE("\n당첨 통계"),
    SPLIT_GUIDE_MESSAGE("---");

    private final String text;

    GuideMessage(String text) {
        this.text = text;
    }

    public String getText() {
        return text;
    }
}
