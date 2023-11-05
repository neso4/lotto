package lotto.exception;

public enum ExceptionCode {
    INVALID_INPUT_INTEGER("INPUT_001", "[ERROR] 입력 값은 0이상의 정수여야 합니다."),
    INVALID_INPUT_DIVIDED("INPUT_002", "[ERROR] 구입 금액은 1,000원 단위로 입력해야 합니다."),
    INVALID_INPUT_RANGE("INPUT_003", "[ERROR] 당첨 숫자는 1~45까지 폐구간 정수로 입력해야 합니다.");

    private final String code;
    private final String message;

    private ExceptionCode(String code, String message) {
        this.code = code;
        this.message = message;
    }

    public String getCode() {
        return this.code;
    }

    public String getMessage() {
        return message;
    }
}
