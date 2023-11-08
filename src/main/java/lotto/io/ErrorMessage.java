package lotto.io;


public enum ErrorMessage {

    PRICE_OUT_OF_RANGE(ErrorConstants.PREFIX + "로또 구매 가능 금액은 1000₩ 이상이며 한번에 구매 가능한 금액은 100000₩ 이하입니다."),
    PRICE_CANNOT_DIVIDE(ErrorConstants.PREFIX + "1000₩으로 나누어 떨어지지 않아 로또를 구매할 수 없습니다."),
    VALUE_NOT_INTEGER(ErrorConstants.PREFIX + "입력한 수는 정수가 아닙니다."),
    NUMBER_OUT_OF_RANGE(ErrorConstants.PREFIX + "로또 번호는 1부터 45 사이의 숫자여야 합니다."),
    INVALID_NUMBER_SIZE(ErrorConstants.PREFIX + "로또 번호는 1개당 6개여야 합니다."),
    DUPLICATE_LOTTO_NUMBER(ErrorConstants.PREFIX + "로또 번호는 중복될 수 없습니다."),
    DUPLICATE_BONUS_NUMBER(ErrorConstants.PREFIX + "보너스 번호는 나머지 6개의 번호와 중복될 수 없습니다.");

    private final String message;

    ErrorMessage(String message) {
        this.message = message;
    }

    public String getMessage() {
        return message;
    }

    private static class ErrorConstants {
        private static final String PREFIX = "[ERROR] ";
    }
}

