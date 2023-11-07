package lotto.error;

public final class ErrorMessage {

    private ErrorMessage() {
        throw new UnsupportedOperationException();
    }

    public static final String INVALID_PURCHASE_AMOUNT_FORMAT = "구입 금액이 정수 형태가 아닙니다";
    public static final String INVALID_WINNING_NUMBER_FORMAT = "당첨 번호가 정수 형태가 아닙니다";
    public static final String INVALID_BONUS_NUMBER_FORMAT = "보너스 번호가 정수 형태가 아닙니다";
}
