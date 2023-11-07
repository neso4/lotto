package lotto.constants;

public enum ErrorMessage {

    PURCHASE_AMOUNT_WRONG_UNIT_INPUT("[ERROR] 1,000원 단위로 입력해 주세요."),
    PURCHASE_AMOUNT_NOT_DIGIT("[ERROR] 숫자만 입력 가능합니다."),
    PURCHASE_AMOUNT_ZERO("[ERROR] 0보다 큰 금액을 입력해 주세요."),
    PURCHASE_AMOUNT_NULL_INPUT("[ERROR] 값을 입력해 주세요.");

    private String message;

    ErrorMessage(String message) {
        this.message = message;
    }

    public String getMessage() {
        return message;
    }
}
