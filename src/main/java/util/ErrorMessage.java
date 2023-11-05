package util;

public enum ErrorMessage {

    LOTTO_COUNT_LIMIT("로또 번호의 개수는 6개여야 합니다."),
    CANT_DUPLICATE_NUMBER("로또 번호는 중복될 수 없습니다."),
    LOTTO_NUMBER_RANGE("로또 번호는 1부터 45 사이의 숫자여야 합니다."),
    PREFIX("[ERROR] ");

    private final String message;

    ErrorMessage(String message) {
        this.message = message;
    }

    public String getErrorMessage() {
        return PREFIX.message + message;
    }
}