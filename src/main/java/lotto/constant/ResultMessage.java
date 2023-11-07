package lotto.constant;

public enum ResultMessage {
    THREE_MATCH("3개 일치 (5,000원) - %s개"),
    FOUR_MATCH("4개 일치 (50,000원) - %s개"),
    FIVE_MATCH("5개 일치 (1,500,000) - %s개"),
    FIVE_MATCH_WITH_BONUS("5개 일치 (30,000,000원) - %s개"),
    SIX_MATCH("6개 일치 (2,000,000,000원) - %s개");

    private String message;

    ResultMessage(final String message) {
        this.message = message;
    }

    public String toMessage() {
        return message;
    }
}
