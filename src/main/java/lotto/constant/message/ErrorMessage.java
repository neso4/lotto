package lotto.constant.message;

import static lotto.constant.LottoNumber.MAXIMUM_WINNING_NUMBER;
import static lotto.constant.LottoNumber.MINIMUM_WINNING_NUMBER;
import static lotto.constant.LottoNumber.WINNING_NUMBER_LENGTH;

public enum ErrorMessage {
    NON_INTEGER_MONEY("올바른 숫자의 금액을 입력해 주세요."),
    NON_INTEGER_WINNING_NUMBER("올바른 숫자의 당첨 번호를 입력해 주세요."),
    INVALID_LENGTH_WINNING_NUMBER(WINNING_NUMBER_LENGTH + "자리의 당첨 번호를 입력해 주세요."),
    INVALID_RANGE_WINNING_NUMBER(MINIMUM_WINNING_NUMBER + "에서 " + MAXIMUM_WINNING_NUMBER + "사이의 당첨 번호를 입력해 주세요."),
    NON_INTEGER_BONUS_NUMBER("올바른 숫자의 보너스 번호를 입력해 주세요.");

    public static final String ERROR = "[ERROR] %s";
    private final String message;

    ErrorMessage(String message) {
        this.message = String.format(ERROR, message);
    }

    public String getMessage() {
        return message;
    }
}
