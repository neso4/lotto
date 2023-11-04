package lotto.property;

public enum ErrorProperty {
    VALUE_IS_EMPTY("입력 값이 공백입니다"),
    VALUE_CONTATIN_SPACE("입력 값에 공백이 포함되어있습니다."),
    VALUE_IS_NOT_NUMERIC("입력 값은 숫자외의 값을 허용하지 않습니다."),
    WINNING_IS_NOT_CORRECTLY_RANGE("당첨번호는 1 ~ 45 사이의 숫자여야합니다.");

    private String errorMessage;

    ErrorProperty(String errorMessage) {
        this.errorMessage = errorPrefix+errorMessage;
    }

    static final String errorPrefix="[ERROR] ";

    @Override
    public String toString() {
        return errorMessage;
    }
}
