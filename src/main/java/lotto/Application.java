package lotto;

import lotto.controller.LottoController;
import lotto.view.InputView;
import lotto.view.OutputView;

public class Application {
    public static void main(String[] args) {
        LottoController lottoController = new LottoController(
                InputView.createConsoleInputView(),
                OutputView.createConsoleOutputView()
        );
        lottoController.run();
    }
}
