package lotto.view;

import static lotto.view.constants.ConstantMessage.REQUEST_BONUS_NUMBER;
import static lotto.view.constants.ConstantMessage.REQUEST_PURCHASE_AMOUNT;
import static lotto.view.constants.ConstantMessage.REQUEST_WINNING_NUMBER;

import camp.nextstep.edu.missionutils.Console;
import lotto.view.constants.ConstantMessage;

public class InputView {
    public static String requestPurchaseAmount() {
        ConstantMessage.printConstantMessage(REQUEST_PURCHASE_AMOUNT);
        return Console.readLine();
    }

    public static String requestWinningNumber() {
        ConstantMessage.printConstantMessage(REQUEST_WINNING_NUMBER);
        return Console.readLine();
    }

    public static String requestBonusNumber() {
        ConstantMessage.printConstantMessage(REQUEST_BONUS_NUMBER);
        return Console.readLine();
    }
}
