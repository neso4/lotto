package lotto.util;

public enum ExceptionMessage {
    NOT_POSITIVE_INTEGER("양의 정수만 입력 가능합니다."),
    NOT_PROPER_DENOMINATION("구입 금액은 1000원 단위만 가능합니다."),
    NOT_PROPER_LOTTO_SIZE("당첨번호는 6개의 숫자로 이루어져 있어야 합니다."),
    INVALID_RANGE("로또의 숫자 범위는 1부터 45까지 입니다."),
    DUPLICATED("로또에 중복값이 존재합니다."),
    DUPLICATED_BONUS("보너스 값은 담청번호와 중복될 수 없습니다");

    public static final String BASE_MESSAGE = "[ERROR] %s";
    private String message;

    ExceptionMessage(String message) {
        this.message = String.format(BASE_MESSAGE, message);
    }

    public String getMessage() {
        return message;
    }
}
