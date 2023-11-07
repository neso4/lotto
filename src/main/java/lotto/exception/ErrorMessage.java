package lotto.exception;

import static lotto.domain.constants.LottoConfig.LOTTO_UNIT_PRICE;

public enum ErrorMessage {
    INVALID_PURCHASE_AMOUNT(String.format(
            "구매 단위는 %d원 단위여야 합니다.", LOTTO_UNIT_PRICE.getValue())
    ),
    DUPLICATE_LOTTO_NUMBERS("하나의 로또에 중복된 숫자가 있습니다."),
    OUT_OF_RANGE_LOTTO_NUMBERS("하나 이상의 로또 내 숫자가 유효한 범위 밖입니다."),
    NOT_NUMERIC_PURCHASE_AMOUNT("입력된 구매 금액이 정수가 아닙니다."),
    NOT_NUMERIC_LOTTO_NUMBER("입력된 로또 숫자가 정수가 아닙니다."),
    INVALID_LOTTO_FORMAT("올바르지 않은 로또 형식입니다.(사이즈 초과 혹은 잘못된 형식)");
    private final String message;
    private static final String ERROR_MESSAGE_PREFIX = "[ERROR] ";

    ErrorMessage(final String message) {
        this.message = message;
    }

    public String getMessage() {
        return ERROR_MESSAGE_PREFIX + message;
    }
}
