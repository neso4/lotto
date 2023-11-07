package lotto.validator.domain.exception;

public enum DomainExceptionMessage {

    INVALID_MONEY_VALUE("[ERROR] 금액은 천원 이상 백만원 이하의 1000원 단위의 금액이어야 합니다."),
    INVALID_LOTTO_LENGTH("[ERROR] 로또 번호의 길이는 6개여야 합니다."),
    DUPLICATES_LOTTO_NUMBERS("[ERROR] 로또 번호는 중복을 허용하지 않습니다."),
    OUT_OF_RANGE_NUMBER("[ERROR] 로또 번호는 1~45 사이의 숫자여야 합니다."),
    DUPLICATES_BONUS_NUMBER("[ERROR] 당첨 번호와 중복된 보너스 번호를 입력할 수 없습니다."),
    NOT_FOUND_LOTTO("[ERROR] 로또 번호가 존재하지 않습니다.");

    private final String value;

    DomainExceptionMessage(final String value) {
        this.value = value;
    }

    public DomainIllegalArgumentException create() {
        return new DomainIllegalArgumentException(value);
    }

    public String value() {
        return value;
    }
}
