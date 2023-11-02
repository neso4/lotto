package lotto.message;

public enum ErrorMessage {
    PREFIX("[ERROR] "),
    INVALID_LOTTO_NUMBER_RANGE("로또 번호는 1에서 45 사이어야 합니다."),
    INVALID_LOTTO_NUMBER_COUNT("로또 번호는 정확히 6개여야 합니다."),
    INVALID_LOTTO_BONUS_NUMBER_COUNT("보너스 번호는 정확히 1개여야 합니다."),
    INVALID_LOTTO_AMOUNT("로또 금액은 1000원 단위여야 합니다."),
    AMOUNT_LESS_THAN_ZERO("로또 금액은 0보다 커야 합니다."),
    INVALID_AMOUNT_FORMAT("구입금액은 숫자 형식이어야 합니다.");

    private final String message;

    ErrorMessage(String message) {
        this.message = message;
    }

    public String getMessage() {
        return PREFIX.message + message;
    }
}
