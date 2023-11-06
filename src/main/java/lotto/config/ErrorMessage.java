package lotto.config;

public enum ErrorMessage {
    SPACE_OR_EMPTY("[ERROR] 공백 혹은 빈 문자열을 입력하였습니다."),
    NULL("[ERROR] Null을 입력하였습니다."),
    STRING_IS_NOT_INT("[ERROR] 숫자를 입력해주세요")
    ;

    private String message;

    ErrorMessage(String message) {
        this.message = message;
    }

    public String getMessage() {
        return message;
    }
}
