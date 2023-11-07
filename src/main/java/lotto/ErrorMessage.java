package lotto;

public enum ErrorMessage {

    ERROR_NOT_NUMBER("정수가 아닌 입력입니다."),
    ERROR_NOT_NATURAL_NUM("자연수가 아닌 입력입니다."),
    ERROR_NOT_THOUSANDS_UNIT("1000원 단위가 아닌 입력입니다.");

    private final String errorMessage;

    ErrorMessage(String errorMessage) {
        this.errorMessage = errorMessage;
    }

    @Override
    public String toString() {
        return this.errorMessage;
    }


}
