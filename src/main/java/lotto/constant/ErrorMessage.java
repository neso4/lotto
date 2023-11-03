package lotto.constant;

public enum ErrorMessage {
    COUNT("[ERROR] 숫자 갯수가 맞지 않습니다."),
    OVERLAP("[ERROR] 중복된 번호가 있습니다."),
    NUMBER("[ERROR] 숫자가 아닙니다.");

    private final String message;

    ErrorMessage(String message) {
        this.message = message;
    }

    public String getMessage() {
        return message;
    }
}
