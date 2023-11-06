package lotto.view;

import static lotto.view.constants.ConstantMessage.REQUEST_BONUS_NUMBER;
import static lotto.view.constants.ConstantMessage.REQUEST_PURCHASE_AMOUNT;
import static lotto.view.constants.ConstantMessage.REQUEST_WINNING_NUMBERS;

import camp.nextstep.edu.missionutils.Console;
import lotto.view.constants.ConstantMessage;

public class InputView {
    public static String requestPurchaseAmount() {
        ConstantMessage.printNotice(REQUEST_PURCHASE_AMOUNT);
        return Console.readLine();
    }

    public static String requestWinningNumbers() {
        ConstantMessage.printNotice(REQUEST_WINNING_NUMBERS);
        return Console.readLine();
    }

    public static String requestBonusNumber() {
        ConstantMessage.printNotice(REQUEST_BONUS_NUMBER);
        return Console.readLine();
    }
}
