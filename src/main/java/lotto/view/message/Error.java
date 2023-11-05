package lotto.view.message;

public enum Error {
    PURCHASE_AMOUNT_VALIDATION("로또 구입 금액은 1000단위의 양의 정수여야만 합니다"),
    LOTTO_NUMBER_OVERSIZE("로또 번호는 6자리 여야만 합니다."),
    LOTTO_NUMBER_DUPLICATE("로또 번호는 중복될 수 없습니다."),
    LOTTO_NUMBER_OUT_OF_BOUND("로또 번호는 1에서 45 사이의 숫자여아만 합니다."),
    WINNING_NUMBER_OVERSIZE("로또 당첨 번호는 6개입니다.");

    private final String CODE = "[ERROR] ";
    private String message;

    Error(String message) {
        this.message = CODE + message;
    }

    public String getMessage() {
        return message;
    }
}
