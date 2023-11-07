package lotto.message;

public enum PurchaseErrorMessage {
    NOT_NUMBER_ERROR("[ERROR] 숫자 이외의 문자를 입력할 수 없습니다."),
    NOT_POSITIVE_ERROR("[ERROR] 양의 정수만 입력할 수 있습니다."),
    OUT_UNIT_ERROR("[ERROR] 1000원 단위로만 입력할 수 있습니다."),
    OUT_RANGE_ERROR("[ERROR] 최대 2147483장까지 구입할 수 있습니다.");

    private final String message;

    PurchaseErrorMessage(String message) {
        this.message = message;
    }

    public String getMessage() {
        return message;
    }
}
