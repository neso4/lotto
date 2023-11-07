package lotto.constants;

public enum ErrorConsts {
    LOTTO_NUMBER_OUT_OF_RANGE("로또 번호는 1 ~ 45 사이의 숫자만 가능합니다."),
    LOTTO_NUMBERS_SIZE_NOT_MATCH("로또 번호는 6개만 가능합니다. 입력한 숫자의 개수와 중복 여부를 확인해주세요."),
    MONEY_NOT_DIVIDED_BY_LOTTO_PRICE("로또 구입 금액은 로또 가격의 배수여야 합니다."),
    BONUS_NUMBER_DUPLICATED("보너스 번호는 로또 번호와 중복될 수 없습니다."),
    INPUT_VALUE_NOT_INTEGER("입력한 값이 정수가 아니거나 Integer 범위를 벗어납니다."),

    ;

    private final String message;

    ErrorConsts(String message) {
        this.message = message;
    }

    public String getMessage() {
        return message;
    }
}
