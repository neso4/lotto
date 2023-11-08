package lotto.controller;

import lotto.model.LottoGame;
import lotto.model.PurchasePrice;
import lotto.view.InputView;
import lotto.view.OutputView;

public class LottoController {
    private final InputView inputView;
    private final OutputView outputView;

    public LottoController(InputView inputView, OutputView outputView) {
        this.inputView = inputView;
        this.outputView = outputView;
    }

    public void runRacingGame() {
        LottoGame lottoGame = setupRacingGame();
        playLottoGame(lottoGame);
    }

    private LottoGame setupRacingGame() {
        PurchasePrice purchasePrice = inputView.readPurchasePrice();
        int numberOfLottos = purchasePrice.calculateNumberOfLottos();
        outputView.printNumberOfLottos(numberOfLottos);
        return new LottoGame();
    }

    private void playLottoGame(LottoGame lottoGame) {

    }
}
