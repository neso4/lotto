package lotto.error;

public enum LottoErrorMessage {
    ERROR_PREFIX("[ERROR] "),
    NOT_FORMATTING_EXCEPTION("숫자만 입력 가능합니다."),
    NOT_INSERT_COINS_EXCEPTION("동전은 투입할 수 없습니다."),
    OVER_LOTTO_FIX_SIZE_EXCEPTION("입력 가능한 로또 번호의 개수는 6개 입니다."),
    LOTTO_NUMBER_RANGE_EXCEPTION("로또 번호는 1부터 45 사이의 숫자여야 합니다."),
    DUPLICATE_NUMBER_EXCEPTION("로또 번호는 중복될 수 없습니다."),
    DUPLICATE_BONUS_NUMBER_EXCEPTION("보너스 번호는 당첨 번호와 중복될 수 없습니다."),
    NOT_INPUT_NEGATIVE_MONEY_EXCEPTION("양수만 입력 가능 합니다.");

    private final String message;

    LottoErrorMessage(String message) {
        this.message = message;
    }

    public String getMessage() {
        return ERROR_PREFIX.message + message;
    }
}
