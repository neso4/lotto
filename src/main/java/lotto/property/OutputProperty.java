package lotto.property;

public enum OutputProperty {

    PURCHASE("구입금액을 입력해 주세요."),
    PURCHASE_COUNT("%s개를 구매했습니다."),
    INPUT_WINNING("당첨 번호를 입력해 주세요."),
    INPUT_BONUS("보너스 번호를 입력해 주세요."),
    LOTTO_RESULT("당첨 통계\n---"),
    LOTTO_RESULT_SCORE(" - "),
    LOTTO_RATE_PREFIX("총 수익률은 "),
    LOTTO_RATE_SUFFIX("입니다.");


    private String console;

    OutputProperty(String console) {
        this.console = console;
    }

    @Override
    public String toString() {
        return console;
    }
}
