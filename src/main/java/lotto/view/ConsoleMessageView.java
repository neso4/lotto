package lotto.view;

public class ConsoleMessageView {

    private static final String MONEY_INPUT_MESSAGE = "구입금액을 입력해 주세요.";

    private static final String LOTTO_NUMBERS_INPUT_MESSAGE = "당첨 번호를 입력해 주세요.";

    private static final String BONUS_NUMBER_INPUT_MESSAGE = "보너스 번호를 입력해 주세요.";

    public static void printMoneyInputMessage() {
        System.out.println(MONEY_INPUT_MESSAGE);
    }

    public static void printWinningLottoNumbersInputMessage() {
        System.out.println(LOTTO_NUMBERS_INPUT_MESSAGE);
    }

    public static void printBonusNumberInputMessage() {
        System.out.println(BONUS_NUMBER_INPUT_MESSAGE);
    }
}

