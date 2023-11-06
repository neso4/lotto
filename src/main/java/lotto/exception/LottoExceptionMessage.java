package lotto.exception;

public enum LottoExceptionMessage {
    PURCHASE_PRICE_MUST_BE_NUMBER("[ERROR] 로또 구입 금액은 숫자로 입력해야 합니다."),
    PURCHASE_PRICE_MUST_BE_POSITIVE_MULTIPLE("[ERROR] 로또 구입 금액은 양의 1000의 배수로 입력해야 합니다."),
    LOTTO_NUMBERS_MUST_BETWEEN_START_AND_END_INCLUSIVE("[ERROR] 로또 번호는 1부터 45 사이의 숫자여야 합니다."),
    WINNING_NUMBERS_MUST_BE_NUMBERS("[ERROR] 당첨 번호는 숫자로 입력해야 합니다."),
    WINNING_NUMBERS_MUST_BE_SIX_DIGITS("[ERROR] 당첨 번호는 중복되지 않는 6자리여야 합니다."),
    WINNING_NUMBERS_MUST_BETWEEN_START_AND_END_INCLUSIVE("[ERROR] 당첨 번호는 1부터 45 사이의 숫자여야 합니다.");

    private String message;

    LottoExceptionMessage(String message) {
        this.message = message;
    }

    public String getMessage() {
        return message;
    }
}
