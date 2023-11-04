package lotto.view;

enum ViewConstraints {

    INPUT_MONEY("구입금액을 입력해 주세요."),
    INPUT_WINNING_NUMBERS("당첨 번호를 입력해 주세요.");

    final private String message;

    public String getMessage() {
        return message;
    }

    ViewConstraints(String message) {
        this.message = message;
    }
}
