package lotto.domain.constants;

public enum ErrorType {

    CONTAIN_DUPLICATED_NUMBER("[ERROR] 중복된 숫자가 존재합니다."),
    LOTTO_NUMBERS_SIZE_IS_NOT_RIGHT("[ERROR] 로또는 6자만 가능합니다."),

    NUMBER_OVER_MAX_OR_MIN_RANGE("[ERROR] 당첨 번호는 0보다 크고 45보다 작아야 합니다.");

    private final String errorMessage;

    ErrorType(String errorMessage) {
        this.errorMessage = errorMessage;
    }

    public String getErrorMessage() {
        return errorMessage;
    }
}
