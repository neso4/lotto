package lotto;

import lotto.controller.LottoController;

public class Application {
    public static void main(String[] args) {
        // TODO: 프로그램 구현
        LottoController controller = new LottoController();

        controller.purchaseLotteries();
        controller.provideLotteriesDetails();
        controller.setUpWinning();
        controller.provideWinningDetails();
        controller.provideRateOfReturn();
    }
}
