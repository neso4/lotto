package lotto;

import lotto.controller.LottoController;

public class Application {
    public static void main(String[] args) {
        LottoController game = new LottoController();
        game.startLottoGame();
    }
}