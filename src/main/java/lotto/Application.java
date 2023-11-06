package lotto;

import lotto.controller.LottoController;
import lotto.model.LottoManager;
import lotto.view.InputView;

public class Application {
    public static void main(String[] args) {
        // TODO: 프로그램 구현
        InputView inputView = new InputView();
        inputView.printPurchaseMoneyMessage();

        LottoController lottoController = new LottoController();

        inputView.printWinningNumbersMessage();
        lottoController.createWinningLotto();
        lottoController.getPrizeList();
    }
}
