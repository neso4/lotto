package message;

public enum ErrorMessage {

    PREFIX("[ERROR] "),
    PURCHASE_AMOUNT_INPUT_ERROR_MESSAGE("로또 구입은 1000 단위의 숫자가 입력되어야 합니다.");

    String message;

    ErrorMessage(String message) {
        this.message = message;
    }

    public String getMessage() {
        return PREFIX.message + this.message;
    }
}
