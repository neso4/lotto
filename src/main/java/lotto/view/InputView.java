package lotto.view;

import camp.nextstep.edu.missionutils.Console;

public class InputView {
    private final OutputView outputView;

    public InputView(OutputView outputView) {
        this.outputView = outputView;
    }

    public String receivePurchaseAmountInput() {
        outputView.printPurchaseAmountInputMessage();
        return Console.readLine();
    }

    public String receiveWinningLotteryNumbersInput() {
        outputView.printWinningLotteryNumbersMessage();
        return Console.readLine();
    }

   public String receiveBonusNumberInput() {
        outputView.printInputBonusNumberMessage();
        return Console.readLine();
   }
}
