package lotto.view;

import static lotto.view.ViewConstant.OutputViewConstant.BONUS_NUMBER_MESSAGE;
import static lotto.view.ViewConstant.OutputViewConstant.EARNING_RATE_MESSAGE;
import static lotto.view.ViewConstant.OutputViewConstant.HUNDRED;
import static lotto.view.ViewConstant.OutputViewConstant.NEW_LINE;
import static lotto.view.ViewConstant.OutputViewConstant.PURCHASE_AMOUNT_MESSAGE;
import static lotto.view.ViewConstant.OutputViewConstant.PURCHASE_MESSAGE;
import static lotto.view.ViewConstant.OutputViewConstant.STATUS;
import static lotto.view.ViewConstant.OutputViewConstant.THREE_DASH;
import static lotto.view.ViewConstant.OutputViewConstant.WINNING_NUMBER_MESSAGE;
public class OutputView {

    public void printMessage(String message) {
        System.out.println(message);
    }

    public void printNewLine() {
        System.out.println();
    }

    public void requestAmount() {
        System.out.println(PURCHASE_AMOUNT_MESSAGE);
    }

    public void requestWinningNumber() {
        System.out.println(WINNING_NUMBER_MESSAGE);
    }

    public void requestBonusNumber() {
        System.out.println(BONUS_NUMBER_MESSAGE);
    }

    public void printUserLotto(long lottoCount, String lotto) {
        System.out.printf(PURCHASE_MESSAGE + NEW_LINE, lottoCount);
        System.out.println(lotto);
        System.out.println();
    }

    public void printStatistics(String statistics) {
        System.out.println(STATUS);
        System.out.println(THREE_DASH);
        System.out.println(statistics);
    }

    public void printEarningRate(long purchaseAmount, long revenue) {
        double earningRate = ((double)revenue / purchaseAmount) * HUNDRED;

        System.out.printf(EARNING_RATE_MESSAGE + NEW_LINE, earningRate);
    }
}
