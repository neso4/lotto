package lotto.view;

import camp.nextstep.edu.missionutils.Console;
import lotto.utils.Utils;

public class InputView {
    private final static String GET_MONEY_MESSAGE = "구입금액을 입력해 주세요.";
    private final static String GET_NUMBERS_MESSAGE = "당첨 번호를 입력해 주세요.";
    private final static String GET_BONUS_NUMBER_MESSAGE = "보너스 번호를 입력해 주세요.";

    private static void showMessage(String message) {
        System.out.println(message);
    }

    public int inputAmount() {
        showMessage(GET_MONEY_MESSAGE);
        return Utils.convertStringToInteger(Console.readLine());
    }

    public String inputWinningNumbers() {
        showMessage(GET_NUMBERS_MESSAGE);
        return Console.readLine();
    }

    public int bonusNumberMessage() {
        showMessage(GET_BONUS_NUMBER_MESSAGE);
        return Utils.convertStringToInteger(Console.readLine());
    }
}
