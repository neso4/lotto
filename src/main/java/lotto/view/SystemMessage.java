package lotto.view;

public enum SystemMessage {
    INPUT_PURCHASE_AMOUNT("구입금액을 입력해 주세요."),
    INPUT_WINNING_LOTTO("\n당첨 번호를 입력해 주세요."),
    INPUT_BONUS("\n보너스 번호를 입력해 주세요."),
    OUTPUT_PURCHASE_AMOUNT("\n%d개를 구매했습니다.");

    private final String message;

    SystemMessage(String message) {
        this.message = message;
    }

    public String getMessage() {
        return message;
    }
}
