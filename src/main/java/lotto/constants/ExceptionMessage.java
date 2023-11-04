package lotto.constants;

public enum ExceptionMessage {
    BE_OUT_OF_RANGE("[ERROR] 로또 번호는 1부터 45 사이의 숫자여야 합니다."),
    HAS_LETTER("[ERROR] 문자를 포함할 수 없습니다."),
    IS_DUPLICATED("[ERROR] 중복된 숫자를 포함할 수 없습니다."),
    IS_INSUFFICIENT("[ERROR] 로또 번호는 6개의 숫자로 구성되어야 합니다.");

    private final String message;

    ExceptionMessage(String message) {
        this.message = message;
    }

    public String getMessage() {
        return message;
    }
}
