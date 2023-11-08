package lotto.exception;

public enum ErrorCode {
    INVALID_BONUS_NUMBER_TYPE("보너스 번호는 정수값이어야 합니다."),
    INVALID_BONUS_NUMBER_RANGE("보너스 번호는 1부터 45 사이의 숫자여야 합니다."),
    NULL_OR_EMPTY_BONUS_NUMBER("보너스 번호는 null이나 공백이 들어갈 수 없습니다."),
    DUPLICATE_BONUS_NUMBER("보너스 번호는 당첨 번호와 중복될 수 없습니다."),

    INVALID_PURCHASE_AMOUNT("구매 금액은 자연수 값만이 들어갈 수 있습니다."),
    NULL_OR_EMPTY_PURCHASE_AMOUNT("구매 금액은 null이나 공백이 들어갈 수 없습니다."),
    INVALID_PURCHASE_AMOUNT_FORMAT("구입 금액은 1,000원 단위로 입력해주세요."),

    INVALID_WINNING_NUMBER_RANGE("당첨 번호는 1부터 45 사이의 숫자여야 합니다."),
    DUPLICATE_WINNING_NUMBERS("당첨 번호는 중복될 수 없습니다."),
    INVALID_WINNING_NUMBER_FORMAT("당첨 번호는 정수값이어야 합니다."),
    INVALID_WINNING_NUMBER_COUNT("6개의 당첨 번호를 입력해주세요."),
    NULL_OR_EMPTY_WINNING_NUMBERS("당첨 번호는 null이나 공백이 들어갈 수 없습니다."),

    INVALID_LOTTO_NUMBER_RANGE("로또 번호는 1부터 45 사이의 숫자여야 합니다."),
    DUPLICATE_LOTTO_NUMBERS("로또 번호는 중복될 수 없습니다."),
    INVALID_LOTTO_NUMBER_COUNT("6개의 로또 번호를 입력해주세요."),

    ILLEGAL_STATE_ERROR("ILLEGAL_STATE_ERROR 발생");

    private final String message;

    ErrorCode(String message) {
        this.message = message;
    }

    public String getMessage() {
        return "[ERROR] " + message;
    }
}