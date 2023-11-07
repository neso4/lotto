package lotto.configure;

/**
 * 예외 메시지를 정의한 enum.
 */
public enum ErrorMessages {
    ERROR_MESSAGE_COMMON_PREFIX("[ERROR]"),
    NOT_AN_INTEGER(ERROR_MESSAGE_COMMON_PREFIX.get() + " 정수를 입력하셔야 합니다."),
    PURCHASE_NUMBER_IS_NEGATIVE(ERROR_MESSAGE_COMMON_PREFIX.get() + " 구입 금액은 0 이상이어야 합니다."),
    PURCHASE_NUMBER_IS_NOT_MULTIPLE_OF_1000(ERROR_MESSAGE_COMMON_PREFIX.get() + " 구입 금액은 1,000으로 나누어 떨어져야 합니다."),
    WINNING_NUMBERS_ARE_NOT_PROPER(ERROR_MESSAGE_COMMON_PREFIX.get() + " 당첨 번호는 정확히 6개의 숫자로 이루어져야 합니다."),
    WINNING_NUMBERS_HAS_DUPLICATE(ERROR_MESSAGE_COMMON_PREFIX.get() + " 중복된 당첨 번호가 있습니다."),
    WINNING_NUMBERS_CONTAINS_NON_NUMBER(ERROR_MESSAGE_COMMON_PREFIX.get() + " 당첨 번호는 공백 없는 정수로 이루어져야 합니다."),
    WINNING_NUMBERS_NOT_IN_PROPER_RANGE(ERROR_MESSAGE_COMMON_PREFIX.get() + " 당첨 번호의 범위는 1~45입니다."),
    BONUS_NUMBER_IS_NOT_AN_INTEGER(ERROR_MESSAGE_COMMON_PREFIX.get() + " 보너스 번호는 정수여야 합니다."),
    BONUS_NUMBER_NOT_IN_PROPER_RANGE(ERROR_MESSAGE_COMMON_PREFIX.get() + " 보너스 번호의 범위는 1~45입니다.");

    private String message;

    ErrorMessages(String message) {
        this.message = message;
    }

    public String get() {
        return this.message;
    }
}
