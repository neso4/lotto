package lotto;

import lotto.controller.LottoController;
import lotto.domain.LottoShop;
import lotto.domain.RandomNumberGenerator;

public class Application {
    public static void main(String[] args) {
        LottoController lottoController = new LottoController(new LottoShop(new RandomNumberGenerator()));
        lottoController.run();
    }
}
