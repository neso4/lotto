package lotto;

import lotto.controller.LottoController;
import lotto.view.InputCoin;

public class Application {
    public static void main(String[] args) {
        LottoController lottoController = new LottoController();
        lottoController.startLottoRoulette();
    }
}
