package lotto;

import lotto.controller.LottoGameController;

public class Application {
    public static void main(String[] args) {
        // TODO: 프로그램 구현
        final LottoGameController lottoGameController = new LottoGameController();
        lottoGameController.startGame();
    }
}
