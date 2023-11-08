package lotto.view;

import camp.nextstep.edu.missionutils.Console;

import static lotto.constant.LottoMessage.*;

public class LottoInputView {
    private final LottoOutputView outputView;

    public LottoInputView(LottoOutputView outputView) {
        this.outputView = outputView;
    }

    public String readPurchaseAmount() {
        outputView.printMessage(INPUT_LOTTO_AMOUNT.getMessage());
        return readLine();
    }

    public String readWinningNumber() {
        outputView.printNewLine();
        outputView.printMessage(INPUT_WINNING_NUMBER.getMessage());
        return readLine();
    }

    public String readBonusNumber() {
        outputView.printNewLine();
        outputView.printMessage(INPUT_BONUS_NUMBER.getMessage());
        return readLine();
    }

    public String readLine() {
        return Console.readLine();
    }
}
