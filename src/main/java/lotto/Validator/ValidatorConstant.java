package lotto.Validator;

public enum ValidatorConstant {
    PARSE_INT_ERROR("[ERROR] 쉼표(,)로 구분했을 때 정수만 입력할 수 있습니다.\n"),
    Not_DIVISION_BY_1000_ERROR("[ERROR] 1000원 단위로만 구매할 수 있습니다.\n"),
    NOT_EQUALS_NUMBER("[ERROR] 중복된 숫자가 있습니다.\n"),
    OUT_OF_RANGE_ERROR("[ERROR] 1~45 사이의 정수만 입력할 수 있습니다.\n"),
    SIZE_ERROR("[Error] 쉼표(,)로 구분했을 때 6개의 정수로 이루어져야 합니다.\n");

    private final String message;

    ValidatorConstant(String message) {
        this.message = message;
    }

    public String getMessage() {
        return message;
    }
}
