package enums;

public enum Message {

    REQUIRE_PURCHASE_MONEY("구입 금액을 입력해 주세요."),
    REQUIRE_WINNING_NUMBER("당첨 번호를 입력해 주세요."),
    REQUIRE_BONUS_NUMBER("보너스 번호를 입력해 주세요."),
    WINNING_STATISTICS("당첨 통계"),
    PUBLISHED_LOTTO("개를 구매했습니다."),
    CORRECT_NUMBER_3("3개 일치 (5,000원) - "),
    CORRECT_NUMBER_4("4개 일치 (50,000원) - "),
    CORRECT_NUMBER_5("5개 일치 (1,500,000원) - "),
    CORRECT_NUMBER_5_AND_BONUS("5개 일치, 보너스 볼 일치 (30,000,000원) - "),
    CORRECT_NUMBER_6("6개 일치 (2,000,000,000원) - "),
    TOTAL_PROFIT_RATE("총 수익률은 "),
    SPLIT_LINE("---"),
    NUMERIC_REGEX("^[1-9]\\d*$"),
    NEW_LINE("\n");
    private final String message;

    Message(String message) {
        this.message = message;
    }

    public String getMessage() {
        return message;
    }
}
