package lotto.common;

public enum ErrorMessage {

    LOTTO_NUM_RANGE_ERROR("[ERROR] 로또 번호의 숫자 범위는 1 ~ 45 까지입니다."),
    LOTTO_NUM_SIZE_ERROR("[ERROR] 로또 번호 개수는 6개입니다."),
    LOTTO_NUM_DUPLICATION_ERROR("[ERROR] 로또 번호는 중복되지 않는 6자리 숫자입니다."),
    LOTTO_PURCHASE_AMOUNT_DIGIT_ERROR("[ERROR] 로또 구입 금액 단위는 1,000원입니다."),
    LOTTO_PURCHASE_AMOUNT_INVALID_ERROR("[ERROR] 로또 구입 금액 자료형은 숫자입니다."),
    MATCH_WINNING_NUMBERS_WITH_BONUS_ERROR("ERROR 당첨 번호와 보너스 번호가 일치합니다.");

    private String message;

    ErrorMessage(String s) {
        this.message = s;
    }

    public String getMessage() {
        return this.message;
    }
}