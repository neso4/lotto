package lotto.variables;

public enum ErrorMessages {
    LOTTO_NUM_ERROR("[ERROR] 당첨 번호는 1과 45 사이 정수여야 합니다."),
    MONEY_VAL_ERROR("[ERROR] 구매 금액은 1,000원 단위의 정수여야 합니다."),
    LOTTO_DUP_ERROR("[ERROR] 당첨 번호는 중복되지 않는 정수여야 합니다."),;

    private ErrorMessages(String errorMessage) {
        ErrorMessage = errorMessage;
    }

    private String ErrorMessage;

    public String getErrorMessage() {
        return ErrorMessage;
    }
}
