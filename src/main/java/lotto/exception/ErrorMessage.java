package lotto.exception;

public enum ErrorMessage {

    // InputView
    INPUT_NOT_COMPOSED_OF_NUMBER("[ERROR] 구입 금액은 숫자로 이루어져야 합니다."),
    PURCHASE_AMOUNT_UNDER_THOUSAND("[ERROR] 구입 금액은 1000원 이상이어야 합니다."),
    INDIVISIBLE_PURCHASE_AMOUNT("[ERROR] 구입 금액은 1000원으로 나누어떨어져야 합니다."),

    // Lotto
    INVALID_LOTTO_NUMBER_COUNT("[ERROR] 로또 번호의 갯수는 6개여야 합니다."),
    INVALID_LOTTO_NUMBER_RANGE("[ERROR] 로또 번호는 1부터 45 사이의 숫자여야 합니다."),
    DUPLICATE_LOTTO_NUMBER("[ERROR] 로또 번호는 중복되지 않아야 합니다.");

    private final String message;

    private ErrorMessage(String message) {
        this.message = message;
    }

    public String getMessage() {
        return message;
    }
}
