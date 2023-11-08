package lotto.constant;

public enum ErrorMessage {
    EXCEEDING_MAX_RETRY("[ERROR] 최대 시도 횟수를 초과했습니다."),
    CONTAINING_OTHER_THAN_NUMBER("[ERROR] 숫자만 입력할 수 있습니다."),
    EXCEEDING_MAXIMUM_PURCHASE("[ERROR] 구매금액은 100,000원을 초과할 수 없습니다."),
    NOT_MULTIPLES_OF_ONE_THOUSAND("[ERROR] 1,000원 단위로만 입력 가능합니다."),
    DUPLICATED_LOTTO_NUMBER("[ERROR] 로또 번호가 중복되었습니다."),
    NOT_IN_LOTTO_NUMBER_RANGE("[ERROR] 로또 번호는 1부터 45 사이의 숫자여야 합니다.");

    private final String message;

    ErrorMessage(String message) {
        this.message = message;
    }

    public String getMessage() {
        return message;
    }
}
